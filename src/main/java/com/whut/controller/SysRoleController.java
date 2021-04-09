package com.whut.controller;

import com.whut.common.validator.ValidatorUtil;
import com.whut.form.SysRoleForm;
import com.whut.query.SysRoleQuery;
import com.whut.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 17:35
 */
@RestController
@RequestMapping("role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("page.do")
    public Object page(SysRoleQuery sysRoleQuery){
        return sysRoleService.selectList(sysRoleQuery);
    }

    @RequestMapping("add.do")
    public Object addRole(SysRoleForm sysRoleForm){
        //对数据进行校验，然后来进行之后的操作
        ValidatorUtil.validator(sysRoleForm);
        return sysRoleService.addRole(sysRoleForm);
    }
    @RequestMapping("update.do")
    public Object updateRole(SysRoleForm sysRoleForm){
        //对数据进行校验，然后来进行之后的操作
        ValidatorUtil.validator(sysRoleForm);
        return sysRoleService.updateRole(sysRoleForm);
    }
    @RequestMapping("all.do")
    public Object allRole(){
        //对数据进行校验，然后来进行之后的操作
        return sysRoleService.allRole();
    }
    @RequestMapping("userRoles.do")
    public Object userRole(@RequestParam("userId") Integer id){
        //对数据进行校验，然后来进行之后的操作
        return sysRoleService.userRole(id);
    }

    @RequestMapping("setRole.do")
    public Object setRole(@RequestParam("userId") Integer userId, @RequestParam("roleId") List<Integer> roleId){
        return sysRoleService.setRoles(userId,roleId);
    }


    @RequestMapping("permissionIds.do")
    public Object permissionIds(Integer id){
        return sysRoleService.getPermissionIds(id);
    }

    /**
     * 批量的设置这个权限的设置
     * @param roleId        角色id
     * @param permissionId      权限id
     * @return  返回一个结果
     */
    @RequestMapping("setRolePermission.do")
    public Object setRolePermission(@RequestParam("roleId") Integer roleId,@RequestParam("permissionId") List<Integer> permissionId){
        return sysRoleService.setRolePermission(roleId,permissionId);
    }



}
