package com.whut.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 0:17
 */
@Data
public class BusCarRentForm {

    //这个是id的作用，便于之后可以进行修改的操作
    private Integer id;

    @NotEmpty(message = "车牌号不可以为空")
    @Length(min = 7,max = 8,message = "车牌号的长度范围在7位到8位之间")
    private String num;

    @NotNull(message = "类型不可以为空")
    @Range(min = 1,max = 3,message = "汽车车辆类型只能是123")
    private Integer type;

    @NotNull(message = "汽车租金不可以为空")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "汽车租金1-99999")
    private Integer rentPrice;


    @NotNull(message = "汽车押金不可以为空")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "汽车押金1-99999")
    private Integer deposit;

    private String name;

    @NotEmpty(message = "身份证不可以为空")
    @Length(min = 18,max = 18,message = "身份证只能是18位")
    private String idCard;

    @NotEmpty(message = "出租时间不可以为空")
    private String rentTime;


    private String beginTime;

    private String endTime;


    private Date createTime;

    private Date updateTime;

    //业务员id
    private Integer userId;





}
