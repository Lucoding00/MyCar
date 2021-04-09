package com.whut.mapper;

import com.whut.form.BusCarRentForm;
import com.whut.model.BusRent;
import com.whut.query.BusRentQuery;
import com.whut.vo.BusRentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusRentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusRent record);

    int insertSelective(BusRent record);

    BusRent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusRent record);

    int updateByPrimaryKey(BusRent record);

    void insertFormRecord(BusCarRentForm busCarRentForm);

    List<BusRentVo> selectPage(BusRentQuery busRentQuery);

    BusRentVo selectById(Integer rentId);

    int updateFlagState(@Param("rentId") Integer rentId,@Param("carRentEd") Integer carRentEd);
}