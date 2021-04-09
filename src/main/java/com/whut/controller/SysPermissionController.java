package com.whut.controller;

import com.whut.common.validator.ValidatorUtil;
import com.whut.form.SysPermissionForm;
import com.whut.query.SysPermissionQuery;
import com.whut.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 20:36
 */
@RestController
@RequestMapping("permission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 获取当前的左侧菜单
     *
     * @return
     */
    @RequestMapping("leftMenu.do")
    public Object getUserMenu() {
        return sysPermissionService.getLeftMenu();
    }

    @RequestMapping("all.do")
    public Object getAll() {
        return sysPermissionService.getAll();
    }

    /**
     * 分页查询这个权限列表类
     *
     * @param sysPermissionQuery
     * @return
     */
    @RequestMapping("page.do")
    public Object getPageList(SysPermissionQuery sysPermissionQuery) {
        return sysPermissionService.getPageList(sysPermissionQuery);
    }

    @RequestMapping("add.do")
    public Object add(SysPermissionForm sysPermissionForm) {
        ValidatorUtil.validator(sysPermissionForm);
        return sysPermissionService.add(sysPermissionForm);
    }
    @RequestMapping("update.do")
    public Object update(SysPermissionForm sysPermissionForm) {
        ValidatorUtil.validator(sysPermissionForm);
        return sysPermissionService.update(sysPermissionForm);
    }

    @RequestMapping("delete.do")
    public Object delete(Integer id) {
        return sysPermissionService.delete(id);
    }


}
