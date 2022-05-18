$(document).ready(function () {
    $(document).on('click', '[class*=update-comment]', function (e) {
        let toolBox = e.target.parentElement
        let textBox = e.target.parentElement.previousElementSibling.previousElementSibling;
        let textOrigin = $(textBox).children().clone();
        let textComment = $(textBox).find('.text-comment')[0].innerHTML;
        let html = `
        <div class="CommentWriter">
            <div class="comment_inbox">
                <textarea class="comment_inbox_text">${textComment}</textarea>
            </div>
            <div class="comment_attach">
                <div class="register_box">
                    <a href="javascript:void(0)" class="btn btn-outline-primary btn_cancel">취소</a>
                    <a href="javascript:void(0)" class="btn btn-primary btn_register" onclick="registerComment(this)">등록<a>
                </div>
            </div>
        </div>`;
        $(textBox).empty().append(html);
        $(toolBox).css({"display": "none"});
        $(document).on('click', '[class*=btn_cancel]', function () {
            $(textBox).empty().append(textOrigin);
            $(toolBox).css({"display": "block"});
        });
    });

    $(document).on('click', 'button.userboard-like', function (e) {
        doVote(this);
    });
});


function createPost(obj) {
    let isLogin = checkLogin(obj);
    if (isLogin) {
        window.location.href = "/board/add-form";
    }
}


function registerComment(obj) {
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    let result = confirm('등록하시겠습니까?');
    if (result) {
        let parent = $(obj).parents()[5];
        let text = $(obj).parents()[2].children[0].children[0];
        let textArea = $(text).val();
        if (isOwn(parent.id)) {
            $.ajax({
                type: "POST",
                url: `/comment/update/${parent.id}/`,
                data: {"content": textArea},
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success: function (response) {
                    if (response.updated) {
                        $(parent).empty().append(response.html);
                    }
                }
            });
        } else {
            alert("본인 글만 수정 가능");
        }
    }
}

function deleteComment(obj) {
    let result = confirm('삭제하시겠습니까?')
    if (result) {
        let parent = obj.parentElement.parentElement.parentElement;
        if (isOwn(parent.id)) {
            $.ajax({
                type: "GET",
                url: `/comment/delete/${parent.id}`,
                success: function (response) {
                    if (response.deleted) {
                        parent.remove();
                    }
                }
            });
        } else {
            alert("본인 글만 삭제 가능");
        }
    }
}

$('#multiForm').on('submit', function (e) {
    e.preventDefault();
    let formData = $(this).serialize();

    $.ajax({
        type: "POST",
        url: MyGlobal.commentCreateUrl,
        cache: false,
        data: formData,
        success: function (response) {
            if (!response.authenticated) {
                alert('로그인 해주세요.')
                window.location.href = `/accounts/login?next=${MyGlobal.detailUrl}`;
            } else {
                $('#multiForm')[0].reset();
                getPage(response.last_page);
            }
        },
        error: function (response) {
            alert('bye');
        }
    })
});


// 로그인 체크
function checkLogin(obj) {
    let result = false;
    $.ajax({
        type: "GET",
        url: "/user/check-login",
        async: false,
        success: function (response) {
            result = true;
        },
        error: function (response) {
            alert("로그인 해주세요!");
            result = false;
        }
    });
    return result;
}

function isOwn(id) {
    console.log(id);
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    let own;
    $.ajax({
        type: "POST",
        url: "/user/check-own",
        async: false,
        data: {"id": id},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            own = true;
        },
        error: function (data) {
            own = false;
        }
    });
    return own;
}

function doVote(obj) {
    console.log("doVote");
    console.log(obj);
    let result = checkLogin(obj);
    if (!result) return;


    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    let status = $(obj).data('status');

    $.ajax({
        type: "POST",
        url: window.location.pathname + '/vote',
        data: {"status": status},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            if (data.code === 3) {
                $('.like-count').each(function (index, item) {
                    item.innerText = data.extra[index];
                });
            } else if(data.code === 4) {
                alert(data.message);
            }
        },
        error: function (data) {
            own = false;
        }
    });
}