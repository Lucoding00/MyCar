package com.whut.shiro;


import com.whut.common.CodeMsg;
import com.whut.common.Result;
import com.whut.model.SysUser;
import com.whut.service.SysPermissionService;
import com.whut.service.SysRoleService;
import com.whut.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description: 认证鉴权器
 * @author: Todd Ding
 * @date 2020-11-30 11:50
 */
public class MyAuthortion extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;


    /**
     * 用户认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户名和密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        char[] pwd = usernamePasswordToken.getPassword();
        String password = String.valueOf(pwd);
        // 根据用户名和密码查询用户
        Result rs = sysUserService.queryUser(username, password);
        // 根据用户名和密码没有查询到数据  则直接返回null
        if (!rs.getCode().equals(CodeMsg.SUCCESS.code)) {
            return null;
        }
        SysUser sysUser = (SysUser) rs.getData();
        String realname = sysUser.getRealname();

        // 根据用户ID 查询用户所有的角色标识
        List<String> roleTags = sysRoleService.queryUserRolesTag(sysUser.getId());
        // 根据用户ID 查询用户所有的权限标识
        List<String> permissionTags = sysPermissionService.queryUserPermissionTags(sysUser.getId());

        ActiveUser activeUser = new ActiveUser();
        activeUser.setSysUser(sysUser);
        activeUser.setRealname(realname);
        activeUser.setRoles(roleTags);
        activeUser.setImg(sysUser.getImg());
        activeUser.setPermissions(permissionTags);
        // shiro自己校验密码
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(activeUser, password, realname);
        return authenticationInfo;
    }

    /**
     * 用户鉴权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //将这个用户的角色还有权限获取到，然后执行之后的代码
        ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
        //在这个地方就是设置这个角色还有权限
        List<String> roles = activeUser.getRoles();
        List<String> permissions = activeUser.getPermissions();
        //在这个地方进行返回的验证信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

}
