package com.whut.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 12:09
 */
@Data
public class BusRetrunForm {
    //换车记录id
    private Integer id;

    @NotEmpty(message = "车牌号不可以为空")
    @Length(min = 7, max = 8, message = "车牌号在7到8位")
    private String num;

    @NotNull(message = "出租记录不可以为空")
    private Integer rentId;

    @NotEmpty(message = "换车时间不可以为空")
    private String returnTime;


    private Integer rentPrice;

    @NotNull(message = "赔付金额不可以为空")
    @Min(value = 0, message = "赔付金额最少为0")
    private Integer payMoney;

    private String problem;

    private Integer totalMoney;

    private Integer userId;

    private Date createTime;

}
