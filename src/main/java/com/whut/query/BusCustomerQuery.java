package com.whut.query;

import com.whut.model.BusCustomer;
import lombok.Data;

/**
 * @author 鲁佳磊
 * @create 2021-04-06 13:58
 */
@Data
public class BusCustomerQuery extends Query {
    //用户姓名
    private String name;
    //电话
    private String phone;
    //地址
    private String address;
    //身份证
    private String idCard;


}
