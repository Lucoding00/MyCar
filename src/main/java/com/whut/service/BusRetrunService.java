package com.whut.service;

import com.whut.common.Result;
import com.whut.form.BusRetrunForm;
import com.whut.query.BusReturnQuery;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 12:07
 */
public interface BusRetrunService {
    Result addRecord(BusRetrunForm busRetrunForm);

    Result selectList(BusReturnQuery busReturnQuery);
}
