package com.whut.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whut.common.CodeMsg;
import com.whut.common.Result;
import com.whut.common.validator.ValidatorUtil;
import com.whut.form.BusCarForm;
import com.whut.mapper.BusCarMapper;
import com.whut.model.BusCar;
import com.whut.model.BusCustomer;
import com.whut.query.BusCarQuery;
import com.whut.service.BusCarService;
import com.whut.vo.BusCarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 鲁佳磊
 * @create 2021-04-06 19:32
 */

@Service
public class BusCarServiceImpl implements BusCarService {
    @Autowired
    private BusCarMapper busCarMapper;

    @Override
    public Result queryPage(BusCarQuery busCarQuery) {
        Page<BusCarVo> objects = PageHelper.startPage(busCarQuery.getPage(), busCarQuery.getLimit());
        busCarMapper.selectList(busCarQuery);
        return new Result(objects.toPageInfo());
    }

    @Override
    public Result addCar(BusCarForm busCarForm) {
        //进行验证，起初就开始进行验证,根据这个数字来进行查找
        BusCarVo busCarVo = busCarMapper.getRecordByNum(busCarForm.getNum());
        if (busCarVo != null) {
            return new Result(CodeMsg.CAR_NUM_EXIST_ERROR);
        }
        busCarForm.setCreateTime(new Date());
        busCarMapper.insertFormData(busCarForm);
        return new Result();
    }
}
