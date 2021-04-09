package com.whut.common;

/**
 * @Description: 业务码 业务消息枚举
 * @author: Todd Ding
 * @date 2020-11-30 11:51
 */
public enum CodeMsg {

    SUCCESS(200, "操作成功"),
    ERROR(110, "程序员送外卖了!"),

    USER_USER_PASSWORD_ERROR(4001001, "用户名或者密码错误!"),

    USER_LOGIN_NAME_EXIST_ERROR(4001002, "用户登录名已被使用!"),
    USER_PHONE_EXIST_ERROR(4001003, "用户手机号已被使用!"),
    USER_ID_CARD_EXIST_ERROR(4001004, "用户身份证号已被使用!"),
    USER_NOT_HAVE_PERMISSION_ERROR(4001005, "用户权限不足!"),
    USER_UPDATE_PASSWORD_ERROR(4001006, "修改失败,原密码不正确"),

    CUSTOMER_ID_CARD_EXIST_ERROR(4002001, "客户身份证号已被使用!"),
    CUSTOMER_PHONE_EXIST_ERROR(4002002, "客户手机号已被使用!"),

    CAR_UPLOAD_IMG_ERROR(4003001, "汽车图片上传失败!"),
    CAR_NUM_EXIST_ERROR(4003002, "汽车车牌号已被使用!"),

    RENT_CUSTOMER_ID_CARD_ERROR(4004001, "客户身份证号不存在!"),
    RENT_CAR_RENTED_ERROR(4004002, "车辆已经出租!"),
    RENT_FAILED_ERROR(4004003, "车辆出租失败,车辆信息发生了变化!"),


    RETURN_CAR_ERROR(4005001, "车辆已经归还,请不要重复还车!"),
    RETURN_FAILED_RENT_CHANGED_ERROR(4005002, "还车失败,出租记录发生变化!"),
    RETURN_FAILED_CAR_CHANG_ERROR(4005003, "还车失败,车辆状态修改失败!"),
    ;
    public Integer code; // 业务码
    public String msg; // 业务消息

    CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
