package com.whut.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whut.common.CodeMsg;
import com.whut.common.Constant;
import com.whut.common.Result;
import com.whut.common.exception.BussiException;
import com.whut.form.BusCarRentForm;
import com.whut.mapper.BusCarMapper;
import com.whut.mapper.BusCustomerMapper;
import com.whut.mapper.BusRentMapper;
import com.whut.model.BusCar;
import com.whut.model.SysUser;
import com.whut.query.BusCustomerQuery;
import com.whut.query.BusRentQuery;
import com.whut.service.BusCarRentService;
import com.whut.shiro.ActiveUser;
import com.whut.vo.BusCarVo;
import com.whut.vo.BusCustomerVo;
import com.whut.vo.BusRentVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 0:15
 */
@Service
public class BusCarRentServiceImpl implements BusCarRentService {
    @Autowired
    private BusRentMapper busRentMapper;

    @Autowired
    private BusCustomerMapper busCustomerMapper;

    @Autowired
    private BusCarMapper busCarMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addCarRent(BusCarRentForm busCarRentForm) {
        BusCustomerQuery busCustomerQuery = new BusCustomerQuery();
        //得到这个idcard的数值的数据，然后放到这个query里面
        busCustomerQuery.setIdCard(busCarRentForm.getIdCard());
        //检查这个form提交过来的一个数据的客户的idcard，是否是正确的，根据idcard来进行查询
        BusCustomerVo busCustomerVo = busCustomerMapper.selectUserByNameOrPhoneOrIdcard(busCustomerQuery);
        if (busCustomerVo == null) {            //如果不存在，那么就抛出一个用户的idcard不存在的错误。
            return new Result(CodeMsg.RENT_CUSTOMER_ID_CARD_ERROR);
        }
        //通过这个设置的车的编号来查这辆车，然后通过这辆车，我可以得到的数据就是buscarvo，来得到这个视图数据。
        BusCarVo recordByNum = busCarMapper.getRecordByNum(busCarRentForm.getNum());
        //如果是已经租出去了，那么就报这个错误，就是车已经租出去了
        if (recordByNum.getIsRent().equals(Constant.CAR_RENT_ED)) {
            return new Result(CodeMsg.RENT_CAR_RENTED_ERROR);
        }
        //通过这个得到这个车的id，然后再来查询这个车
        BusCar busCar = busCarMapper.selectByPrimaryKey(recordByNum.getId());
        busCar.setIsRent(Constant.CAR_RENT_ED);
        busCar.setVersion(recordByNum.getVersion() + 1);
        int i = busCarMapper.updateByPrimaryKeySelective(busCar);
        if (i != 1) {
            throw new BussiException(CodeMsg.RENT_FAILED_ERROR.code, CodeMsg.RENT_FAILED_ERROR.msg);
        }
        String rentTime = busCarRentForm.getRentTime();
        String[] split = rentTime.split("~");
        busCarRentForm.setBeginTime(split[0].trim());
        busCarRentForm.setEndTime(split[1].trim());


        busCarRentForm.setName(busCustomerVo.getName());
        Subject subject = SecurityUtils.getSubject();
        ActiveUser principal = (ActiveUser) subject.getPrincipal();
        SysUser sysUser = principal.getSysUser();
        busCarRentForm.setUserId(sysUser.getId());
        //设置默认的时间,开始时间还有结束时间
        busCarRentForm.setCreateTime(new Date());
        busCarRentForm.setUpdateTime(new Date());
        busRentMapper.insertFormRecord(busCarRentForm);
        return new Result();
    }

    @Override
    public Result selectList(BusRentQuery busRentQuery) {
        Page<BusRentVo> objects = PageHelper.startPage(busRentQuery.getPage(), busRentQuery.getLimit());
        if (StrUtil.isNotEmpty(busRentQuery.getBeginTime())){
            //设置这个得到的时间，然后将这个时间得到开始时间还有结束时间来进行设置
            String[] split = busRentQuery.getBeginTime().split("~");
            busRentQuery.setMinBeginTime(split[0].trim());
            busRentQuery.setMaxBeginTime(split[1].trim());
        }
        busRentMapper.selectPage(busRentQuery);
        return new Result(objects.toPageInfo());
    }
}
