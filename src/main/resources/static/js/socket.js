let messageSocket;

$(() => {
    if( loggedInUser ) {
        messageSocket = new WebSocket(`ws://${location.host}/message`);
        messageSocket.onopen = messageSocketOpen;
        messageSocket.onmessage = messageSocketResponse;
    }
});

// 소켓 연결
function messageSocketOpen() {
    // 회원 초기화
    messageSocket.send(JSON.stringify({
        type: "initUser",
        userNo: loggedInUser.userNo
    }));
    socketNewMessageCheck();
}

// 소켓 상호작용
function messageSocketResponse(responseData) {
    let data = JSON.parse(responseData.data);
    let type = data.type;

    // 신규 메세지 체크
    if( type=="responseMessageCount" ) {
        let target = $("#message .messageToggle");

        if( data.count>0 )  target.addClass("new");
        else                target.removeClass("new");
    }

    // 메세지 받기
    if( type=="responseMessageSend" ) {
        // 메세지 레이어다 닫혀있다면 신규메세지 카운트만 체크
        if( !$("#message").hasClass("active") ) {
            socketNewMessageCheck();
            return false;
        } else {
            let appendTarget = $("#message .messageContent ul");

            $.get(`/message/${data.messageNo}`, result => {
                // 리스트 최신화
                let readStatus = updateMessageList(result);

                // 현재 리스트의 내용을 보고 있다면
                if( readStatus ) {
                    updateMessageReadStatus(result.groupNo);
                    appendTarget.append(createMessageContent(result));
                    moveToContentScrollBottom();
                }
            }, "json");
        }
    }
}

// 새로운 메세지 확인하기
function socketNewMessageCheck(userNo) {
    userNo = userNo ? userNo : loggedInUser.userNo;
    messageSocket.send(JSON.stringify({
        type: "requestMessageCount",
        userNo : userNo
    }));
}

// 웹소켓 전송
function socketMessageSend(data) {
    messageSocket.send(JSON.stringify({
        type: "requestMessageSend",
        senderNo: data.sender.userNo,
        receiverNo: data.receiver.userNo,
        groupNo: data.groupNo,
        messageNo: data.messageNo
    }));
}