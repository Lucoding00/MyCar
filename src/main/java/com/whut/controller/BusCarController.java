package com.whut.controller;

import com.whut.common.validator.ValidatorUtil;
import com.whut.form.BusCarForm;
import com.whut.query.BusCarQuery;
import com.whut.service.BusCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 鲁佳磊
 * @create 2021-04-06 19:33
 */
@RequestMapping("car")
@Controller
public class BusCarController {
    @Autowired
    private BusCarService busCarService;

    /**
     * 将这个解析成为一个json格式的字符串
     * @param busCarQuery
     * @return
     */
    @RequestMapping("page.do")
    @ResponseBody
    public Object carList(BusCarQuery busCarQuery){
        return busCarService.queryPage(busCarQuery);
    }


    @RequestMapping("add.do")
    @ResponseBody
    public Object addCar(BusCarForm busCarForm){
        ValidatorUtil.validator(busCarForm);
        return busCarService.addCar(busCarForm);
    }

}
