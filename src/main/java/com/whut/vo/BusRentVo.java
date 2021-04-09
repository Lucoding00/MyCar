package com.whut.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whut.model.BusRent;
import lombok.Data;

import java.util.Date;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 11:12
 */
@Data
public class BusRentVo extends BusRent {


    //格式化时间的操作，在这个地方直接来进行书写
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;


}
