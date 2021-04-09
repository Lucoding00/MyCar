package com.whut.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 鲁佳磊
 * @create 2021-04-06 23:05
 */
@Data
public class BusCarForm {

    //主键的操作，汽车ID
    private Integer id;

    @NotEmpty(message = "车牌号不可以为空")
    @Length(min = 7,max = 8,message = "车牌号的长度范围在7位到8位之间")
    private String num;

    @NotNull(message = "类型不可以为空")
    @Range(min = 1,max = 3,message = "汽车车辆类型只能是123")
    private Integer type;

    @NotEmpty(message = "汽车颜色不可以为空")
    @Length(max = 10,message = "汽车颜色最多就是10位元素")
    private String color;

    @NotEmpty(message = "汽车图片不可以为空")
    @Length(max = 100,message = "汽车图片地址最多就是100位字符")
    private String img;

    @NotNull(message = "汽车金额不可以为空")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "汽车金额1-99999")
    private Integer price;

    @NotNull(message = "汽车租金不可以为空")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "汽车租金1-99999")
    private Integer rentPrice;

    @NotNull(message = "汽车押金不可以为空")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "汽车押金1-99999")
    private Integer deposit;

    @Length(max = 100,message = "100个字符限制在这个地方")
    private String descp;

    private Date createTime;



}
