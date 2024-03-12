// input name 에 배열 순서값 넣기
function convertNameArrayNumber(name) {
    let list = $("[name*='"+name+"['");
    let size = list.length;

    list.each((i, element)=> {
        let orgName = $(element).attr("name");
        let chgName = orgName.replace(/\d/g, i);

        $(element).attr("name", chgName);
    });
}

// multiple select 선택 옵션 가져오기
function getMultipleValues(elementName) {
    let valueArray = [];
    let element = $("[name='"+elementName+"']");

    if( element.get(0).localName==='select' ) {
        element.find("option").each((idx, el) => {
            if( $(el).is(":selected") ) {
                valueArray.push($(el).val());
            }
        });
    } else {
        let elementType = element.attr("type");

        if( elementType==='checkbox' ) {
            element.each((idx, el) => {
                if( $(el).is(":checked") ) {
                    valueArray.push($(el).val());
                }
            })
        } else {
            element.each((idx, el) => {
                valueArray.push($(el).val());
            });
        }
    }

    return valueArray;
}

// 직무 중분류 가져오기
function getPositionDetail(positionTarget, detailTarget, placeholder) {
    let positionNo = positionTarget.find("option:selected").val();

    detailTarget.find("option").remove();

    if( placeholder ) {
        detailTarget.append(`<option value="">${placeholder}</option>`);
    }

    if( positionNo ) {
        $.get(`/position/${positionNo}/detail`, data => {
            data.forEach(detail => {
                detailTarget.append(`<option value="${detail.positionDetailNo}">${detail.middleName}</option>`);
            });

            if(typeof getPositionDetailCallback==='function' ) getPositionDetailCallback(detailTarget);
        }, 'json');
    }
}

// toast 알림창 노출
function requestToast(message, bgClass = "primary", focusElement) {
    let toastElement = $("#siteNotice");
    let toast = bootstrap.Toast.getOrCreateInstance(toastElement, {delay: 3000});

    let updateClass = toastElement.attr("class").replace(/text-bg-\S+/, "text-bg-"+bgClass);

    toastElement.attr("class", updateClass);
    toastElement.find(".toast-body").text(message);
    toast.show();

    if( focusElement ) {
        focusElement.focus();
    }
}