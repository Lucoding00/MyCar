package com.whut.service;

import com.whut.common.Result;
import com.whut.form.BusCarRentForm;
import com.whut.query.BusRentQuery;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 0:14
 */
public interface BusCarRentService {
    Result addCarRent(BusCarRentForm busCarRentForm);

    Result selectList(BusRentQuery busRentQuery);
}
