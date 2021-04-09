package com.whut.mapper;

import com.whut.common.Result;
import com.whut.form.BusCarForm;
import com.whut.model.BusCar;
import com.whut.query.BusCarQuery;
import com.whut.vo.BusCarVo;

import java.util.List;

public interface BusCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusCar record);

    int insertSelective(BusCar record);

    BusCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusCar record);

    int updateByPrimaryKey(BusCar record);

    List<BusCarVo> selectList(BusCarQuery busCarQuery);

    void insertFormData(BusCarForm busCarForm);

    BusCarVo getRecordByNum(String num);
}