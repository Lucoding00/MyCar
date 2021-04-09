package com.whut.query;

import lombok.Data;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 11:07
 */

@Data
public class BusRentQuery extends Query {
    //车牌号
    private String num;
    //客户名称
    private String name;
    //出租状态
    private Integer flag;
    //开始时间
    private String beginTime;

    //最小开始时间
    private String minBeginTime;
    //最大开始时间
    private String maxBeginTime;


}
