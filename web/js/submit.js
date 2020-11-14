$(function opt() {
    jQuery("#btn-users").on('click',function (event) {
        if (user_check().form()) {
            var flag = new Boolean();
            flag = true;
            jQuery.when(jQuery.ajax({
                type: "post",
                url: "addUserServlet",
                data: {
                    types: "search",
                    username: $('#username_user').val()
                },
                async: "false",
                dataType: "json",
                success: function (data) {
                    if (data.message == "true") {
                        flag = confirm("该用户名已存在，再次提交会进行修改");
                    }
                }
            })).done(function (){
                if (flag) {
                    jQuery.ajax({
                        type: "post",
                        url: "addUserServlet",
                        data: {
                            types: "add",
                            username: $('#username_user').val(),
                            password: $('#password').val(),
                        },
                        dataType: "json",
                        success: function (data) {
                            alert(data.message);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert(errorThrown);
                        }
                    });
                }
            })
            return false;
        }
        else {
            alert("不合法!");
            return false;
        }
    });
    jQuery("#btn-person").on('click',function (event) {
        if (person_check().form()) {
            var flag = new Boolean();
            flag = true;
            $.when(jQuery.ajax({
                type: "post",
                url: "addPersonServlet",
                data: {
                    types: "search",
                    username: $('#username_person').val()
                },
                async: "false",
                dataType: "json",
                success: function (data) {
                    if (data.message == "true") {
                        flag = confirm("该用户名已存在，再次提交会进行修改");
                    }
                }
            })).done(function (){
                if (flag) {
                    jQuery.ajax({
                        type: "post",
                        url: "addPersonServlet",
                        data: {
                            types: "add",
                            username: $('#username_person').val(),
                            name: $('#name').val(),
                            age: $('#age').val(),
                            teleno: $('#teleno').val()
                        },
                        dataType: "json",
                        success: function (data) {
                            alert(data.message);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert(errorThrown);
                        }
                    });
                }
            })
            return false;
        }
        else {
            alert("不合法!");
            return false;
        }
    });
    jQuery("#btn-users-del").on('click', function () {
        if (del_check().form()) {
            if (confirm("请确定要删除")) {
                jQuery.ajax({
                    type: "post",
                    url: "deleteUserServlet",
                    data: {
                        types: "delete",
                        username: $('#username_user_del').val(),
                    },
                    dataType: "json",
                    success: function (data) {
                        alert(data.message);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert(errorThrown);
                    }
                });
            }
            return false;
        }
    })
});