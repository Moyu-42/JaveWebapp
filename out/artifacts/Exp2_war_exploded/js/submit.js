$(function add() {
    jQuery("#btn-person").on('click',function (event) {
        $('#form-person').bootstrapValidator(); //验证配置
        var validator = $('#form-person').data("bootstrapValidator"); //获取validator对象
        if (validator.isValid()) {
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
    });
    jQuery("#btn-users").on('click',function (event) {
        $('#form-users').bootstrapValidator(); //验证配置
        var validator = $('#form-users').data("bootstrapValidator"); //获取validator对象
        if (validator.isValid()) {
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
    });
    jQuery("#btn-users-del").on('click', function () {
        $('#form-users-del').bootstrapValidator(); //验证配置
        var validator = $('#form-users-del').data("bootstrapValidator"); //获取validator对象
        if (validator.isValid()) {
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