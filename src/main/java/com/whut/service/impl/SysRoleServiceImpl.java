package com.whut.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whut.common.Result;
import com.whut.form.SysRoleForm;
import com.whut.mapper.SysRoleMapper;
import com.whut.mapper.SysUserRoleRelMapper;
import com.whut.model.SysRole;
import com.whut.query.SysRoleQuery;
import com.whut.service.SysRoleService;
import com.whut.vo.SysRoleVo;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 17:37
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleRelMapper sysUserRoleRelMapper;


    @Override
    public Result selectList(SysRoleQuery sysRoleQuery) {
        Page<SysRoleVo> sysRoleVos = PageHelper.startPage(sysRoleQuery.getPage(), sysRoleQuery.getLimit());
        sysRoleMapper.selectList(sysRoleQuery);
        return new Result(sysRoleVos.toPageInfo());
    }

    @Override
    public Result addRole(SysRoleForm sysRoleForm) {
        sysRoleMapper.insertForm(sysRoleForm);
        return new Result();
    }

    @Override
    public Result updateRole(SysRoleForm sysRoleForm) {
        //通过id来进行查找record数据，然后将record数据来进行查找
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(sysRoleForm.getId());
        sysRole.setTag(sysRoleForm.getTag());
        sysRole.setName(sysRoleForm.getName());
        sysRole.setDescp(sysRoleForm.getDescp());
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        return new Result();
    }

    @Override
    public Result allRole() {
        //所有的东西都没有的时候，直接使用这个一个query来进行书写，那么就可以查出所有的数据了
        return new Result(sysRoleMapper.selectList(new SysRoleQuery()));
    }

    @Override
    public Result userRole(Integer id) {
        List<SysRole> sysRoleList = sysRoleMapper.selectListByUserId(id);
        return new Result(sysRoleList);
    }

    @Override
    public Result setRoles(Integer userId, List<Integer> roleId) {
        //先删除之前的角色,删除之前的角色
        sysUserRoleRelMapper.deleteUserRoles(userId);
        //添加之后的的角色
        sysUserRoleRelMapper.insertUserRoles(userId, roleId);
        return new Result();
    }

    @Override
    public List<String> queryUserRolesTag(Integer id) {
        //得到所有的用户的角色
        List<SysRole> sysRoleList = sysRoleMapper.selectListByUserId(id);
        //将查询到的数据放到这个数据里面去，得到之后的数据
        ArrayList<String> strings = new ArrayList<>();
        for (SysRole sysRole : sysRoleList) {
            strings.add(sysRole.getTag());
        }
        return strings;
    }

    @Override
    public Result getPermissionIds(Integer id) {
        List<Integer> ids = sysRoleMapper.selectPermissionIds(id);
        return new Result(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result setRolePermission(Integer roleId, List<Integer> permissionId) {
        //删除当前角色权限关系
        sysRoleMapper.deleteRolePermRel(roleId);
        //新增角色权限关系
        sysRoleMapper.batchInsertRolePermRel(roleId, permissionId);
        return new Result();
    }
}
