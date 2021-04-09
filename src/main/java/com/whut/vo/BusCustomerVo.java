package com.whut.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whut.model.BusCustomer;
import lombok.Data;

import java.util.Date;

/**
 * @author 鲁佳磊
 * @create 2021-04-06 14:04
 */
@Data
public class BusCustomerVo extends BusCustomer {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
