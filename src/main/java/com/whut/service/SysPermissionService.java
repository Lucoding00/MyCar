package com.whut.service;

import com.whut.common.Result;
import com.whut.form.SysPermissionForm;
import com.whut.query.SysPermissionQuery;

import java.util.List;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 20:39
 */
public interface SysPermissionService {
    Result getLeftMenu();

    List<String> queryUserPermissionTags(Integer id);

    Result getPageList(SysPermissionQuery sysPermissionQuery);

    Result getAll();

    Result add(SysPermissionForm sysPermissionForm);

    Result update(SysPermissionForm sysPermissionForm);

    Result delete(Integer id);
}
