$(function check() {
    jQuery("#form-users").bootstrapValidator({
        live: 'enabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
        excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
        submitButtons: $('#btn-users'),
        message: '通用的验证失败消息',//好像从来没出现过
        feedbackIcons: {//根据验证结果显示的各种图标
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username_user: {
                validators: {
                    notEmpty: {
                        message: '请输入用户名'
                    },
                    stringLength: {
                        min: 0,
                        max: 10,
                        message: '长度必须在10之内'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '请输入密码'
                    },
                    stringLength: {
                        min: 0,
                        max: 8,
                        message: '长度必须在8之内'
                    }
                }
            }
        }
    });
    jQuery("#form-person").bootstrapValidator({
        live: 'enabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
        excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
        submitButtons: $('#btn-person'),//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
        feedbackIcons: {//根据验证结果显示的各种图标
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username_person: {
                validators: {
                    notEmpty: {
                        message: '请输入用户名'
                    },
                    stringLength: {
                        min: 0,
                        max: 10,
                        message: '长度必须在10之内'
                    }
                }
            },
            name: {
                validators: {
                    notEmpty: {
                        message: '请输入名字'
                    },
                    stringLength: {
                        min: 0,
                        max: 20,
                        message: '长度必须在20之内'
                    }
                }
            },
            age: {
                validators: {
                    digits: {
                        message: '该值只能包含数字'
                    },
                    greaterThan: {
                        value: 0,
                        message: '年龄要大于0'
                    },
                    lessThan: {
                        value: 800,
                        message: '年龄要小于800'
                    }
                }
            },
            teleno: {
                validators: {
                    digits: {
                        message: '该值只能包含数字'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '长度必须为11位'
                    }
                }
            }
        }
    });
});