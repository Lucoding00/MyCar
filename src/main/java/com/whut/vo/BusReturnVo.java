package com.whut.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whut.model.BusReturn;
import lombok.Data;

import java.util.Date;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 16:07
 */
@Data
public class BusReturnVo extends BusReturn {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

}
