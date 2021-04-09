package com.whut.mapper;

import com.whut.model.SysUserRoleRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleRelMapper {
    int insert(SysUserRoleRel record);

    int insertSelective(SysUserRoleRel record);

    int deleteUserRoles(Integer userId);

    int insertUserRoles(@Param("userId") Integer userId, @Param("roleId") List<Integer> roleId);
}