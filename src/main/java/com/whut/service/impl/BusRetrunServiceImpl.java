package com.whut.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whut.common.CodeMsg;
import com.whut.common.Constant;
import com.whut.common.Result;
import com.whut.common.exception.BussiException;
import com.whut.form.BusRetrunForm;
import com.whut.mapper.BusCarMapper;
import com.whut.mapper.BusRentMapper;
import com.whut.mapper.BusReturnMapper;
import com.whut.model.BusCar;
import com.whut.query.BusReturnQuery;
import com.whut.service.BusRetrunService;
import com.whut.shiro.ActiveUser;
import com.whut.vo.BusCarVo;
import com.whut.vo.BusRentVo;
import com.whut.vo.BusReturnVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 12:08
 */
@Service
public class BusRetrunServiceImpl implements BusRetrunService {

    @Autowired
    private BusReturnMapper busReturnMapper;

    @Autowired
    private BusRentMapper busRentMapper;

    @Autowired
    private BusCarMapper busCarMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)               //添加事务的回滚的操作
    public Result addRecord(BusRetrunForm busRetrunForm) {
        //业务数据的检查
        //查询出租记录的状态
        //判断出租记录的状态
        BusRentVo busRentVo = busRentMapper.selectById(busRetrunForm.getRentId());
        //如果是已经换车的话，进行抛出这个异常
        if (busRentVo.getFlag().equals(Constant.CAR_RETURN_ED)) {
            return new Result(CodeMsg.RENT_CAR_RENTED_ERROR);
        }
        //如果没有还车，那么就修改状态为已经归还车辆
        int i = busRentMapper.updateFlagState(busRetrunForm.getRentId(), Constant.CAR_RENT_ED);
        if (i != 1) {
            return new Result(CodeMsg.RETURN_FAILED_CAR_CHANG_ERROR);
        }
        BusCarVo recordByNum = busCarMapper.getRecordByNum(busRetrunForm.getNum());
        BusCar busCar = busCarMapper.selectByPrimaryKey(recordByNum.getId());
        busCar.setIsRent(Constant.CAR_RENT_NOT);
        busCar.setVersion(busCar.getVersion() + 1);
        int j = busCarMapper.updateByPrimaryKeySelective(busCar);
        if (j != 1) {     //修改失败，那么就代表之后的的操作是错误的,那么就是返回这个修改的错误
            throw new BussiException(CodeMsg.RETURN_FAILED_CAR_CHANG_ERROR.code,CodeMsg.RETURN_FAILED_CAR_CHANG_ERROR.msg);
        }
        String beginTime = busRentVo.getBeginTime();
        String returnTime = busRetrunForm.getReturnTime();
        Integer rentPrice = busRentVo.getRentPrice();           //单日租金
        DateTime parseBeginTime = DateUtil.parse(beginTime, "yyyy-MM-dd");
        DateTime parseReturnTime = DateUtil.parse(returnTime, "yyyy-MM-dd");
        int days = (int) DateUtil.betweenDay(parseBeginTime, parseReturnTime, true);
        //总租金
        rentPrice *=days;
        //总金额
        int totalMoney = rentPrice + busRetrunForm.getPayMoney();
        busRetrunForm.setRentPrice(rentPrice);
        busRetrunForm.setTotalMoney(totalMoney);
        Subject subject = SecurityUtils.getSubject();
        ActiveUser principal = (ActiveUser) subject.getPrincipal();
        //设置这个userid的一个操作
        busRetrunForm.setUserId(principal.getSysUser().getId());
        //如果是成功的，那么就返回就完事了
        busRetrunForm.setCreateTime(new Date());
        busReturnMapper.insertForm(busRetrunForm);
        return new Result();
    }

    @Override
    public Result selectList(BusReturnQuery busReturnQuery) {
        Page<BusReturnVo> objects = PageHelper.startPage(busReturnQuery.getPage(), busReturnQuery.getLimit());
        busReturnMapper.selectList(busReturnQuery);

        return new Result(objects.toPageInfo());
    }
}
