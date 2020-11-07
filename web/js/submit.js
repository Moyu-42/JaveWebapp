$(function add() {
    jQuery("#btn-person").on('click',function (event) {
        $('#form-person').bootstrapValidator(); //验证配置
        var validator = $('#form-person').data("bootstrapValidator"); //获取validator对象
        if (validator.isValid()) {
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
                    $('#form-person').data("bootstrapValidator").resetForm();
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            });
            return false;
        }
    });
    jQuery("#btn-users").on('click',function (event) {
        $('#form-users').bootstrapValidator(); //验证配置
        var validator = $('#form-users').data("bootstrapValidator"); //获取validator对象
        if (validator.isValid()) {
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
            return false;
        }
    });
});