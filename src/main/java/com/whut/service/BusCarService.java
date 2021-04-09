package com.whut.service;

import com.whut.common.Result;
import com.whut.form.BusCarForm;
import com.whut.query.BusCarQuery;

/**
 * @author 鲁佳磊
 * @create 2021-04-06 19:32
 */
public interface BusCarService {
    Result queryPage(BusCarQuery busCarQuery);

    Result addCar(BusCarForm busCarForm);
}
