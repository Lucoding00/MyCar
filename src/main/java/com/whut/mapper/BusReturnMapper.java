package com.whut.mapper;

import com.whut.form.BusRetrunForm;
import com.whut.model.BusReturn;
import com.whut.query.BusReturnQuery;
import com.whut.vo.BusReturnVo;

import java.util.List;

public interface BusReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusReturn record);

    int insertSelective(BusReturn record);

    BusReturn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusReturn record);

    int updateByPrimaryKey(BusReturn record);

    int insertForm(BusRetrunForm busRetrunForm);

    List<BusReturnVo> selectList(BusReturnQuery busReturnQuery);
}