const Editor = toastui.Editor;
const colorSyntax = toastui.Editor.plugin['colorSyntax'];
$.fn.select2.defaults.set("minimumResultsForSearch", -1);
$.fn.select2.defaults.set("width", '100%');

let moimImgFileInfo, headcountHtml, detailDescEditor;
let platformSelect, onlineSelect, positionSelect, positionDetailSelect, languageSelect, studyCategoryselect;

$(() => {
    $("input[name='type']").on("change", typeChangeEvent);
    $(document).on("click change", "input[name='moimImg']", moimImgUploadEvent)
    $(document).on("change", "select[name='positionNo']", positionChangeEvent);
    $(document).on("change", "select[name='onlineNo']", onlineChangeEvent);
    $(document).on("change", "input[name='everyone']", positionEveryoneEvent);
});

// 모집 유형 변경 이벤트 처리
function typeChangeEvent(e) {
    let type = $(e.currentTarget).val();
    getHtmlTemplate(type);
}

// 모임 이미지 업로드 이벤트
function moimImgUploadTrigger() {
    $("input[name='moimImg']").click();
}
function moimImgUploadEvent(e) {
    if( e.type=='click' ) {
        moimImgFileInfo = e.target.files;
    }

    if( e.type=='change' ) {
        let target = $("#moimImg-thumbnail-btn");

        if( e.target.files.length>0 ){
            let file = e.target.files[0];

            if( file!==moimImgFileInfo[0] ){
                let reader = new FileReader();

                reader.onload = e => {
                    let imgElement = $("<img />");
                    imgElement.attr('src', e.target.result);

                    target.html('');
                    target.append(imgElement);
                };

                reader.readAsDataURL(file);
            }
        } else {
            $(e.currentTarget).prop("files", moimImgFileInfo);
        }
    }
}
function moimImgLoader(path) {
    if( path ) {
        // 임시데이터 사용하는 동안
        if( path!="https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png" ) {
            path = "/display?fileName="+path;
        }

        $("#moimImg-thumbnail-btn").append("<img src='"+path+"' >")
    }
}

// 온/오프라인 변경 이벤트 처리
function onlineChangeEvent(e) {
    let _this = e ? $(e.currentTarget) : $("select[name='onlineNo']");
    let onlineNo = _this.val();
    let hideTarget = $(".fee");


    if( onlineNo==2 ) {
        $("input[name='offAddr']").val('').hide();
        hideTarget.find("input[name='fee']").val('');
        hideTarget.hide();
    } else {
        $("input[name='offAddr']").show();
        hideTarget.show();
    }
}

// 직무 대분류 변경 이벤트 처리
function positionChangeEvent(e) {
    let _this = e ? $(e.currentTarget) : $("select[name='positionNo']");
    let detailTarget = _this.siblings("select[name='positionDetailNo']");

    getPositionDetail(_this, detailTarget);
}

// 모집인원 select 동적 플러그인 적용
function initializePositionSelect(type) {
    if( type==='init' ) {
        positionSelect = $("select[name='positionNo']").select2({width: '49%'});
        positionDetailSelect = $("select[name='positionDetailNo']").select2({width: '49%'});
    } else {
        if( positionSelect )        positionSelect.select2("destroy");
        if( positionDetailSelect )  positionDetailSelect.select2("destroy");
    }
}

// 모집직무 추가/제거
function positionAddToDrop(type) {
    let cloneItem, cloneHtml;
    let appendSel = $(".headcount .content");

    if( type==='add' ) {
        if( appendSel.find(".colItem").length>0 ) {
            initializePositionSelect('destroy');

            cloneHtml = appendSel.find(".colItem").eq(0).html();
            cloneItem = $("<div class='colItem clone' />");
        } else {
            cloneHtml = headcountHtml;
            cloneItem = $("<div class='colItem' />");
        }

        cloneItem.append(cloneHtml);
        cloneItem.find("input[name='headcountNo']").val("");
        appendSel.append(cloneItem);

        initializePositionSelect('init');

        return cloneItem;
    } else if( type==='drop' ) {
        appendSel.find(".colItem.clone").eq(-1).remove();
    } else if( type==='dropPurge' ) {
        appendSel.empty();
    } else if( type==='dropPurgeCopy' ) {
        initializePositionSelect('destroy');

        headcountHtml = appendSel.find(".colItem").eq(0).html();
        appendSel.empty();
    }
}

// 모집인원 인원수 증감
function personnelControl(element, type) {
    let personnelElement = $(element).parent().find("input[name='personnel']");
    let personnel = parseInt(personnelElement.val());

    if( type==='plus' )     personnel = personnel>=9 ? personnel : personnel+1;
    else                    personnel = personnel<=1 ? personnel : personnel-1;

    personnelElement.val(personnel);
}

// 직무구분 없음 이벤트 처리
function positionEveryoneEvent(e) {
    let _this = e ? $(e.currentTarget) : $("input[name='everyone']");
    let parentElement = $(".headcount");
    let everyoneStatus = _this.is(":checked");

    if( everyoneStatus ) {
        parentElement.find(".mainTitle .etc").hide();

        positionAddToDrop("dropPurgeCopy");
        let colItem = positionAddToDrop("add");

        colItem.find(".position").empty();
        colItem.find(".position").append(`<p>누구나 참여 가능</p>`);
    } else {
        parentElement.find(".mainTitle .etc").show();

        positionAddToDrop("dropPurge");
        positionAddToDrop("add");
    }
}

// 참고링크 추가/제거
function linkControl(type) {
    let appendSel = $(".link .content .rowItem");
    let selCount = $("input[name='link']").length;

    if( type==='add' ) {
        if( selCount<5 ) {
            let cloneHtml = appendSel.find(".itemWrap").eq(0).html();
            let cloneItem = $("<div class='itemWrap clone' />");

            cloneItem.append(cloneHtml);
            cloneItem.find("input[name='linkNo']").val('');
            appendSel.append(cloneItem);
        }
    } else {
        appendSel.find(".itemWrap.clone").eq(-1).remove();
    }
}

// 타입에 맞는 템플릿 가져오기
function getHtmlTemplate(type) {
    let appendTarget = $(".templateContainer");
    let templateName = type=='project' ? 'moimProjectTemplate' : 'moimStudyTemplate';
    let template = document.getElementById(templateName);

    // 템플릿 교체
    appendTarget.html('');
    appendTarget.html(template.innerHTML);

    positionChangeEvent();
    initializePositionSelect('init');

    // 사용하는 플러그인 재 정의
    onlineSelect = $("select[name='onlineNo']").select2();
    languageSelect = $("select[name='language']").select2({
        tags: true,
        placeholder: '기술/언어를 입력해주세요.',
        minimumResultsForSearch: 0
    });

    detailDescEditor = new Editor({
        el: document.querySelector("#detailDesc"),
        height: type=='project' ? '800px' : '500px',
        previewStyle: 'tab',
        initialEditType: 'markdown',
        autofocus: false,
        initialValue: template.content.getElementById("detailDescContent").innerHTML,
        plugins: [colorSyntax]
    });

    if( type=='project' ) {
        platformSelect = $("select[name='platformNo']").select2({
            placeholder: "출시할 플랫폼을 선택해주세요."
        });
    } else if( type=='study' ) {
        studyCategoryselect = $("select[name='studyCategoryNo']").select2();
    }

    if( typeof templateLoadCallback==='function' ) templateLoadCallback(type);
}

// 등록 폼 체크
function formCheck() {
    let form = document.writeForm;
    let languageValues = getMultipleValues("language");
    let detailDesc = detailDescEditor.getMarkdown();

    // Check
    if( !form.subject.value ) {
        requestToast("프로젝트명을 입력해주세요.", "danger", form.subject);
        return false;
    } else if( !form.shortDesc.value ) {
        requestToast("프로젝트명을 입력해주세요.", "danger", form.shortDesc);
        return false;
    } else if( languageValues.length<=0 ) {
        requestToast("사용언어/기술을 하나 이상 선택해주세요.", "danger", form.language);
        return false;
    } else if( form.onlineNo.value!=2 && !form.offAddr.value ) {
        requestToast("오프라인 장소를 입력해주세요.", "danger", form.offAddr);
        return false;
    }

    if( form.type.value==='project' ) {
        let platformNoValues = getMultipleValues("platformNo");

        if( !form.categoryNo.value ) {
            requestToast("분야를 선택해주세요.", "danger");
            $(document).scrollTop($(".category-project").position().top);
            return false;
        } else if( platformNoValues.length<=0 ) {
            requestToast("출시 플랫폼을 선택해주세요.", "danger", form.platformNo);
            return false;
        }
    }

    // Data
    form.detailDesc.value = detailDesc;
}
