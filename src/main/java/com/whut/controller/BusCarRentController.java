package com.whut.controller;

import cn.hutool.core.util.StrUtil;
import com.whut.common.validator.ValidatorUtil;
import com.whut.form.BusCarForm;
import com.whut.form.BusCarRentForm;
import com.whut.query.BusCarQuery;
import com.whut.query.BusRentQuery;
import com.whut.service.BusCarRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 0:13
 */

@RestController
@RequestMapping("rent")
public class BusCarRentController {
    @Autowired
    private BusCarRentService busCarRentService;


    @RequestMapping("add.do")
    public Object add(BusCarRentForm busCarRentForm){
        ValidatorUtil.validator(busCarRentForm);
        return busCarRentService.addCarRent(busCarRentForm);
    }

    @RequestMapping("page.do")
    public Object pageList(BusRentQuery busRentQuery ){

        return busCarRentService.selectList(busRentQuery);
    }




}
