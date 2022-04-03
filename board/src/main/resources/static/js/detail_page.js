function deleteComment(obj){
    var result = confirm('삭제하시겠습니까?')
    if (result) {
        var parent = obj.parentElement.parentElement.parentElement;
        $.ajax({
            type: "GET",
            url: `/comment/${parent.id}/delete`,
            success: function (response) {
                if (response.deleted) {
                    parent.remove();
                }
            }
        })
    }
}