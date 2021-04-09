package com.whut.service;

import com.whut.common.Result;
import com.whut.form.SysUserForm;
import com.whut.query.SysUserQuery;

/**
 * @author 鲁佳磊
 * @create 2021-04-05 22:53
 */
public interface SysUserService {
    Result queryUser(String username, String password);

    Object queryPage(SysUserQuery sysUserQuery);

    Result addUser(SysUserForm sysUserForm);

    Result resetPwd(Integer id);

    Result updatePassword(Integer id, String newPassword);

    Result updateHeaderImg(String img);
}
