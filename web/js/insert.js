function user_check() {
    return $('#form-users').validate({
        errorElement : 'span',
        errorClass : 'help-block',
        rules: {
            username_user: {
                required: true,
                maxlength: 10
            },
            password: {
                required: true,
                maxlength: 8
            }
        },
        messages: {
            username_user: {
                required: "请输入用户名",
                maxlength: "最大长度为10"
            },
            password: {
                required: "请输入密码",
                maxlength: "最大长度为8"
            }
        },
        //自定义错误消息放到哪里
        errorPlacement : function(error, element) {
            element.next().remove();//删除显示图标
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);//显示错误消息提示
        },
        //给未通过验证的元素进行处理
        highlight : function(element) {
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        //验证通过的处理
        success : function(label) {
            var el=label.closest('.form-group').find("input");
            el.next().remove();//与errorPlacement相似
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
    })
}
function person_check() {
    return $('#form-person').validate({
        errorElement : 'span',
        errorClass : 'help-block',
        rules: {
            username_person: {
                required: true,
                maxlength: 10
            },
            name: {
                required: true,
                maxlength: 20
            },
            age: {
                digits: true,
                max: 800,
                min: 0
            },
            teleno: {
                digits: true,
                maxlength: 11,
                minlength: 11
            }
        },
        messages: {
            username_person: {
                required: "请输入用户名",
                maxlength: "最大长度为10"
            },
            name: {
                required: "请输入名称",
                maxlength: "最大长度为20"
            },
            age: {
                digits: "年龄只能为数字",
                max: "不能超过800",
                min: "不能低于0"
            },
            teleno: {
                digits: "电话号码只能为数字",
                maxlength: "长度只能为11位",
                minlength: "长度只能为11位"
            }
        },
        //自定义错误消息放到哪里
        errorPlacement : function(error, element) {
            element.next().remove();//删除显示图标
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);//显示错误消息提示
        },
        //给未通过验证的元素进行处理
        highlight : function(element) {
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        //验证通过的处理
        success : function(label) {
            var el=label.closest('.form-group').find("input");
            el.next().remove();//与errorPlacement相似
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
    });
}
$(function del_check() {
    jQuery("#form-users-del").bootstrapValidator({
        live: 'enabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
        excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
        submitButtons: $('#btn-users-del'),
        feedbackIcons: {//根据验证结果显示的各种图标
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username_user_del: {
                validators: {
                    notEmpty: {
                        message: '请输入用户名'
                    },
                    stringLength: {
                        min: 0,
                        max: 10,
                        message: '长度必须在10之内'
                    },
                    remote: {
                        url: 'deleteUserServlet',
                        type: "post",
                        message: "该Username不存在",
                        delay: 1000,
                        data: {
                            types: "search",
                            username: $("input[name=username_user_del]").val()
                        },
                        dataType: "json"
                    }
                }
            }
        }
    });
});
$(user_check());
$(person_check());