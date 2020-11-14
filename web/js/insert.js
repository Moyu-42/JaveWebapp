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
    return $('#form-users-del').validate({
        rules: {
            username_user_del: {
                required: true,
                remote: {
                    type: "POST",
                    url: "deleteUserServlet",
                    delay: 200,
                    data: {
                        types: "search",
                        username: function () {
                            return $('#username_user_del').val();
                        }
                    },
                    dataType: "JSON",
                    dataFilter: function (data, type) {
                        if (data == "true") {
                            return true;
                        }else return false;
                    }
                }
            }
        }
    });
}
$(user_check());
$(person_check());
$(del_check());