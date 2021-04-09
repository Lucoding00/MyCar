package com.whut.mapper;

import com.whut.form.BusCustomerForm;
import com.whut.model.BusCustomer;
import com.whut.query.BusCustomerQuery;
import com.whut.vo.BusCustomerVo;
import com.whut.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusCustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusCustomer record);

    int insertSelective(BusCustomer record);

    BusCustomer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusCustomer record);

    int updateByPrimaryKey(BusCustomer record);

    List<BusCustomerVo> selectListByUserQuery(BusCustomerQuery busCustomerQuery);

    BusCustomerVo selectUserByNameOrPhoneOrIdcard(BusCustomerQuery busCustomerQuery);

    void insertSysUserForm(BusCustomerForm busCustomerForm);

    void updateById(BusCustomerForm busCustomerForm);

    /**
     * 这个就是设置的这个用户的列表，然后进行之后的列表的操作。
     * @param busCustomerVos
     */
    void batchInsert(@Param("customers") List<BusCustomerVo> busCustomerVos);
}