package com.whut.query;

import lombok.Data;

/**
 * @author 鲁佳磊
 * @create 2021-04-06 10:54
 */
@Data
public abstract class Query {
    private Integer page = 1;
    private Integer limit = 10;
}
