function user_check() {
    return $('#form-users').validate({
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
        message: {
            username_user: {
                required: "请输入用户名",
                maxlength: "最大长度为10"
            },
            password: {
                required: "请输入密码",
                maxlength: "最大长度为8"
            }
        }
    })
}
function person_check() {
    return $('#form-person').validate({
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
        message: {
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
        }
    });
}
function del_check() {
    jQuery("#form-users-del").bootstrapValidator({
        live: 'submitted',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
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
                        delay: 200,
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
};
$(user_check());
$(person_check());