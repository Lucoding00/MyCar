package com.whut.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 鲁佳磊
 * @create 2021-04-06 14:22
 */

@Data
public class BusCustomerForm {
    //客户id,为啥留着这个id，就是为了之后可以修改的时候可以使用到，然后就可以修改
    private Integer id;

    @NotEmpty(message = "名字不可以为空")
    @Length(max = 15,message = "名字最多15位字符")
    private String name;


    @NotEmpty(message = "手机号不可以为空")
    @Length(min = 11,max = 11,message = "手机号只能是11位字符")
    private String phone;

    @NotNull(message = "地址不可以为空")
    private String address;

    @NotEmpty(message = "身份证不可以为空")
    @Length(min = 18,max = 18,message = "身份证只能是18位")
    private String idCard;

    @NotNull(message = "性别不可以为空")
    @Range(min = 1 ,max = 2 ,message = "性别只能是男或者是女")
    private Integer sex;

    private Date createTime;

    private Date updateTime;






}
