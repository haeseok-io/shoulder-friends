$(() => {
    // 메세지 레이어 토글
    $("#message .messageToggle").on("click", e => {
        let openStatus = $("#message").hasClass("active");

        if( openStatus )    messageLayerControl('close');
        else                messageLayerControl('open');
    });

    // 메세지 레이어 닫기
    $("#message .messageClose").on("click", e => {
        messageLayerControl('close');
    });

    // 메세지 상세보기
    $(document).on("click", "#message .messageList ul li", e => {
        let _this = $(e.currentTarget);
        let groupNo = _this.attr("data-groupNo");
        let messageNo = _this.attr("data-messageNo");
        let senderNo = _this.attr("data-senderNo");
        let messageSendTargetNo = _this.attr("data-messageSendTargetNo");

        // 선택한 그룹에 상대 메세지 전부 읽음처리
        _this.removeClass("new");
        updateMessageReadStatus(groupNo);

        // 컨텐츠 호출
        messageContentOpenEvent(groupNo, messageSendTargetNo);
    });

    // 메세지 보내기
    $("#messageInput").on("keypress", e => {
        let form = $("#message form[name='messageForm']");
        let appendTarget = $("#message .messageContent ul");

        if( e.keyCode==13 ) {
            // 메세지 전송
            if( !e.shiftKey ) {
                $.post("/message/", form.serialize(), data => {
                    appendTarget.append(createMessageContent(data));
                    moveToContentScrollBottom();
                    updateMessageList(data);

                    // 웹소켓 전송
                    socketMessageSend(data);

                    // 입력했던값 지우기
                    $(e.currentTarget).val("");
                }, 'json');
            }
        }
    });
});

// 메세지 레이어 컨트롤
function messageLayerControl(type) {
    if( type==='open' )     messageLayerOpenEvent();
    else                    messageLayerCloseEvent();
}

// 메세지 레이어가 열릴때 이벤트
function messageLayerOpenEvent(targetNo, targetNickname) {
    $("#message").addClass("active");
    messageBlockView("list", true);
    messageBlockView("content", false);
    messageBlockView("blank", false);
    getMessageListData(targetNo, targetNickname);
}

// 메세지 레이어가 닫힐때 이벤트
function messageLayerCloseEvent() {
    initMessageList()
    initMessageContent();
    messageBlockView("list", false);
    messageBlockView("content", false);
    messageBlockView("blank", false);
    $("#message").removeClass("active");
}

// 메세지 리스트 초기화
function initMessageList() {
    let initTarget = $("#message .messageList ul");
    initTarget.empty();
}

// 메세지 컨텐츠 초기화
function initMessageContent() {
    let initTarget = $("#message .messageContent ul");
    initTarget.empty();
}

// 메세지 리스트 데이터 호출
function getMessageListData(targetNo, targetNickname) {
    let appendTarget = $("#message .messageList ul");

    // Init
    initMessageList();
    messageBlockView("list", true);

    // Process
    $.get("/message/user/"+loggedInUser.userNo, data => {
        // 타겟유저번호가 없고 메세지 데이터도 없을경우
        if( !targetNo && data.length<1 ) {
            messageBlockView("list", false);
            messageBlockView("content", false);
            messageBlockView("blank", true);
            return false;
        }

        // 메세지 리스트 노출
        $.each(data, (idx, obj) => {
            appendTarget.append(createMessageList(obj));
        });

        // 넘겨받은 회원번호와 주고받은 메세지 자동선택
        if( targetNo ) {
            let senderTarget = appendTarget.find("li[data-senderNo='"+targetNo+"']");
            let receiverTarget = appendTarget.find("li[data-receiverNo='"+targetNo+"']");

            if( senderTarget.length>0 || receiverTarget.length>0 ) {
                let selectTarget = senderTarget.length>0 ? senderTarget : receiverTarget;
                let groupNo = selectTarget.attr("data-groupNo");
                let messageSendTargetNo = selectTarget.attr("data-messageSendTargetNo");

                messageContentOpenEvent(groupNo, messageSendTargetNo);
            } else {
                // 넘겨받은 회원번호와 주고받은 메세지가 없을경우 신규리스트 추가
                createNewMessage(targetNo, targetNickname);
            }


        }
    }, "json");

}

function messageContentOpenEvent(groupNo, messageSendTargetNo) {
    messageBlockView("content", true);

    // 리스트 효과 제어
    if( groupNo ) {
        // 기존에 선택되어있던 리스트 제어 ( 신규등록하던 메세지일경우 리스트 제거 )
        let beforeTargetList = $("#message .messageList ul li.active");
        if( beforeTargetList.length>0 ) {
            if( !beforeTargetList.attr("data-groupNo") ) {
                beforeTargetList.remove();
            }
        }

        // 새로 선택된 리스트 효과부여
        let targetList = $("#message .messageList ul li[data-groupNo='"+groupNo+"']");
        targetList.addClass("active").siblings().removeClass("active");
    }

    getMessageContentData(groupNo, messageSendTargetNo);
}

// 메세지 상세 데이터 호출
function getMessageContentData(groupNo, messageSendTargetNo) {
    let contentTarget = $("#message .messageContent");
    let formTarget = $("form[name='messageForm']", contentTarget);
    let appendTarget = $("ul", contentTarget);

    // Init
    initMessageContent();
    messageBlockView("content", true);

    // Data
    // - 메세지 전송 form 교정
    $("input[name='groupNo']", formTarget).val(groupNo);
    $("input[name='senderNo']", formTarget).val(loggedInUser.userNo);
    $("input[name='receiverNo']", formTarget).val(messageSendTargetNo);

    // Process
    if( groupNo ) {
        $.get(`/message/group/${groupNo}`, data => {
            $.each(data, (idx, obj) => {
                appendTarget.append(createMessageContent(obj));
            });

            moveToContentScrollBottom();
        }, "json");
    }
}

// 메세지 리스트 HTML 생성
function createMessageList(obj) {
    let template = $($("#messageListTemplate").html());
    let messageSendTargetNo = loggedInUser.userNo!=obj.sender.userNo ? obj.sender.userNo : obj.receiver.userNo;
    let profileImg = obj.sender.userDetail.profileImg ? obj.sender.userDetail.profileImg : "/images/profileBlank.png";

    // Data
    template.attr("data-groupNo", obj?.groupNo);
    template.attr("data-messageNo", obj?.messageNo);
    template.attr("data-senderNo", obj.sender.userNo);
    template.attr("data-receiverNo", obj.receiver.userNo);
    template.attr("data-messageSendTargetNo", messageSendTargetNo);

    template.find(".thumbnail img").attr("src", profileImg);
    template.find(".nickname").text(obj.sender.nickname+" -> "+obj.receiver.nickname);
    template.find(".content").text(obj?.contents);

    if( loggedInUser.userNo!=obj.sender.userNo && obj.status==1 ){
        template.addClass("new");
    }

    return template;
}

// 리스트 업데이트 ( 메세지그룹을 읽고있는지 아닌지 리턴 )
function updateMessageList(obj) {
    let updateTarget = $("#message .messageList ul li[data-groupNo='"+obj.groupNo+"']");
    let messageSendTargetNo = loggedInUser.userNo!==obj.sender.userNo ? obj.sender.userNo : obj.receiver.userNo;
    let profileImg = obj.sender.userDetail.profileImg ? obj.sender.userDetail.profileImg : "/images/profileBlank.png";

    // Init
    // - groupNo 와 일치하는 타겟이 없을경우 .active 클래스를 지정 ( 신규 메세지 발송시 )
    if( updateTarget.length<1 ) {
        updateTarget = $("#message .messageList ul li.active");
    }

    // Check
    // - 리스트를 확인하고 있지 않을경우 new 아이콘 추가
    if( !updateTarget.hasClass("active") ) {
        updateTarget.addClass("new");
        socketNewMessageCheck();
        return false;
    }

    // Data
    // - groupNo가 없을경우 넘어온 groupNo로 업데이트
    if( !updateTarget.attr("data-groupNo") ) {
        updateTarget.attr("data-groupNo", obj.groupNo);
    }

    // 리스트 업데이트
    updateTarget.attr("data-messageNo", obj.messageNo);
    updateTarget.attr("data-senderNo", obj.sender.userNo);
    updateTarget.attr("data-receiverNo", obj.receiver.userNo);
    updateTarget.attr("data-messageSendTargetNo", messageSendTargetNo);

    updateTarget.find(".thumbnail img").attr("src", profileImg);
    updateTarget.find(".nickname").text(obj.sender.nickname+" -> "+obj.receiver.nickname);
    updateTarget.find(".content").text(obj.contents);

    return true;
}

// 메세지 컨텐츠 HTML 생성
function createMessageContent(obj) {
    let template = $($("#messageContentTemplate").html());

    let date = new Date(obj.regdate);
    let sendDate = `${date.getFullYear()}년 ${date.getMonth()+1}월 ${date.getDate()}일`;
    let profileImg = obj.sender.userDetail.profileImg ? obj.sender.userDetail.profileImg : "/images/profileBlank.png";


    if( loggedInUser.userNo==obj.sender.userNo ) {
        template.addClass("send");
    }

    template.attr("data-messageNo", obj.messageNo);
    template.attr("data-senderNo", obj.sender.userNo);
    template.find(".thumbnail img").attr("src", profileImg);
    template.find(".nickname").text(obj.sender.nickname);
    template.find(".sendDate").text(sendDate);
    template.find(".content").text(obj.contents);

    // 기존 컨텐츠와 발신자가 같은경우 처리
    let beforeContentTarget = $("#message .messageContent ul li").eq(-1);
    if( beforeContentTarget.length>0 ) {
        let beforeSenderNo = beforeContentTarget.attr("data-senderNo");
        let beforeSendDate = beforeContentTarget.find(".sendDate").text();

        if( beforeSenderNo==obj.sender.userNo ) {
            template.find(".thumbnail img").hide();
            template.find(".data .info .nickname").hide();

            if( beforeSendDate==sendDate ) {
                template.find(".data .info .sendDate").hide();
            }
        }
    }

    return template;
}

// 메세지 리스트 노출관리
function messageBlockView(name, type) {
    let element;

    // Init
    if( name=='list' )          element = $("#message .messageList");
    else if( name=='content' )  element = $("#message .messageContent");
    else if( name=='blank' )    element = $("#message .messageBlank");

    if( type )  element.show();
    else        element.hide();
}

// 메세지 읽음처리
function updateMessageReadStatus(groupNo) {
    let receiverNo = loggedInUser.userNo;

    $.ajax({
        type: "patch",
        url: `/message/group/${groupNo}/receiver/${receiverNo}/status/1`,
        data: {status: 2},
        dataType: 'json',
        success: data => {
            socketNewMessageCheck();
        }
    });
}

// 컨텐츠 하단으로 스크롤 이동
function moveToContentScrollBottom() {
    let scrollTarget = $("#message .messageContent ul");
    scrollTarget.scrollTop(scrollTarget.prop("scrollHeight"));
}

// 특정회원과 주고받은 메세지 열기
function openMessageToUser(userNo, userNickname) {
    if( !loggedInUser ) {
        alert("로그인 후 이용 가능합니다.");
        $("header .userToggle").trigger("click");
        return false;
    }

    messageLayerOpenEvent(userNo, userNickname);
}

// 신규발송 메세지 영역 추가
function createNewMessage(receiverNo, receiverNickname) {
    let prependTarget = $("#message .messageList ul");
    let option = {
        sender: {
            userNo : loggedInUser.userNo,
            nickname: loggedInUser.nickname,
            userDetail: {profileImg: loggedInUser?.userDetail?.profileImg}
        },
        receiver: {
            userNo: receiverNo,
            nickname: receiverNickname
        }
    }

    // Result
    let prependElement = createMessageList(option);
    prependElement.addClass("active");
    prependTarget.prepend(prependElement);
    messageContentOpenEvent(null, receiverNo);
}