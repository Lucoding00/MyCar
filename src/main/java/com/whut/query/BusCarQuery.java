package com.whut.query;

import lombok.Data;

/**
 * @author 鲁佳磊
 * @create 2021-04-06 19:46
 */

@Data
public class BusCarQuery extends Query{
    //需要查询的条件就是哪些数据呢。
    //代表的就是车牌号
    private String num;
    //车型
    private Integer type;
    //颜色
    private String color;
    //最大金额
    private String maxPrice;
    //最小金额
    private String minPrice;
    //最小租金
    private String minRentPrice;
    //最大租金
    private String maxRentPrice;
    //出租状态
    private Integer isRent;
    //描述
    private String descp;





}
