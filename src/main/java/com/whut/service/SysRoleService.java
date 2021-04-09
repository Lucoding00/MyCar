package com.whut.service;

import com.whut.common.Result;
import com.whut.form.SysRoleForm;
import com.whut.query.SysRoleQuery;

import java.util.List;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 17:37
 */
public interface SysRoleService {

    Result selectList(SysRoleQuery sysRoleQuery);

    Result addRole(SysRoleForm sysRoleForm);

    Result updateRole(SysRoleForm sysRoleForm);

    Result allRole();

    Result userRole(Integer id);

    Result setRoles(Integer userId, List<Integer> roleId);

    List<String> queryUserRolesTag(Integer id);

    Result getPermissionIds(Integer id);

    Result setRolePermission(Integer roleId, List<Integer> permissionId);
}
