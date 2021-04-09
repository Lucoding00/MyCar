package com.whut.controller;

import com.whut.common.validator.ValidatorUtil;
import com.whut.form.BusRetrunForm;
import com.whut.model.BusReturn;
import com.whut.query.BusReturnQuery;
import com.whut.service.BusRetrunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 12:06
 */

@RestController
@RequestMapping("return")
public class BusRetrunController {
    @Autowired
    private BusRetrunService busRetrunService;

    @RequestMapping("add.do")
    public Object add(BusRetrunForm busRetrunForm){
        ValidatorUtil.validator(busRetrunForm);
        return busRetrunService.addRecord(busRetrunForm);
    }


    @RequestMapping("page.do")
    public Object page(BusReturnQuery busReturnQuery){
        return busRetrunService.selectList(busReturnQuery);
    }




}
