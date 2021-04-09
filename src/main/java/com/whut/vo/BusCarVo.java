package com.whut.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whut.model.BusCar;
import lombok.Data;

import java.util.Date;

/**
 * @author 鲁佳磊
 * @create 2021-04-06 19:54
 */
@Data
public class BusCarVo extends BusCar {

    //这个是解析成为这个日期的形式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

}
