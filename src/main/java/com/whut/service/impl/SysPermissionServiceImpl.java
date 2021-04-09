package com.whut.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whut.common.Constant;
import com.whut.common.Result;
import com.whut.form.SysPermissionForm;
import com.whut.mapper.SysPermissionMapper;
import com.whut.model.SysPermission;
import com.whut.query.SysPermissionQuery;
import com.whut.service.SysPermissionService;
import com.whut.shiro.ActiveUser;
import com.whut.vo.SysPermissionMenuVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 20:39
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public Result getLeftMenu() {

        Subject subject = SecurityUtils.getSubject();
        ActiveUser principal = (ActiveUser) subject.getPrincipal();
        principal.getSysUser().getId();
        //获取菜单获取的就是一级菜单，然后在获取的就是之后的菜单
        List<SysPermissionMenuVo> sysPermissionMenuVo = sysPermissionMapper.selectLeftMenuByUserId(principal.getSysUser().getId(), Constant.PERMISSION_TYPE_MENU, Constant.MENU_LV1);
        for (int i = 0; i < sysPermissionMenuVo.size(); i++) {
            //获取一级菜单之后的菜单，然后设置就是这个一个权限的设置
            List<SysPermissionMenuVo> temp = sysPermissionMapper.selectLeftMenuByUserId(principal.getSysUser().getId(), Constant.PERMISSION_TYPE_MENU, sysPermissionMenuVo.get(i).getId());
            sysPermissionMenuVo.get(i).setChildren(temp);
        }
        return new Result(sysPermissionMenuVo);
    }

    @Override
    public List<String> queryUserPermissionTags(Integer id) {
        List<SysPermissionMenuVo> sysPermissionMenuVos = sysPermissionMapper.selectLeftMenuByUserId(id, null, null);
        ArrayList<String> strings = new ArrayList<>();
        for (SysPermissionMenuVo sysPermissionMenuVo : sysPermissionMenuVos) {
            strings.add(sysPermissionMenuVo.getTag());
        }
        return strings;
    }

    @Override
    public Result getPageList(SysPermissionQuery sysPermissionQuery) {
        Page<SysPermissionMenuVo> objects = PageHelper.startPage(sysPermissionQuery.getPage(), sysPermissionQuery.getLimit());
        sysPermissionMapper.selectList(sysPermissionQuery);
        return new Result(objects.toPageInfo());
    }

    @Override
    public Result getAll() {
        List<SysPermissionMenuVo> sysPermissionMenuVos = sysPermissionMapper.selectList(null);
        return new Result(sysPermissionMenuVos);
    }

    @Override
    public Result add(SysPermissionForm sysPermissionForm) {
        sysPermissionMapper.insertForm(sysPermissionForm);
        return new Result();
    }

    @Override
    public Result update(SysPermissionForm sysPermissionForm) {
        SysPermission sysPermission = sysPermissionMapper.selectByPrimaryKey(sysPermissionForm.getId());
        sysPermission.setHref(sysPermissionForm.getHref());
        sysPermission.setIcon(sysPermissionForm.getIcon());
        sysPermission.setParentId(sysPermissionForm.getParentId());
        sysPermission.setSort(sysPermissionForm.getSort());
        sysPermission.setSpread(sysPermissionForm.getSpread().equals(1));
        sysPermission.setTag(sysPermissionForm.getTag());
        sysPermission.setType(sysPermissionForm.getType());
        sysPermission.setTitle(sysPermissionForm.getTitle());
        sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
        return new Result();
    }

    @Override
    public Result delete(Integer id) {
        //需要删除这个树的子权限还有子子权限都需要进行删除，那么就需要进行之后的删除了。
        //所有的id，需要进行删除的一些id的列表根据传递过来的id，然后来进行删除之后的数据
        //删除这一级之后下面的所有的权限的列表
        ArrayList<Integer> ids = new ArrayList<>();
        //二级
        ids.add(id);
        //得到下面一级的操作的一个操作的数据
        List<Integer> childIds = sysPermissionMapper.selectAllChildId(ids);
        while (childIds.size() != 0) {
            ids.addAll(childIds);
            //继续得到下面一级
            childIds = sysPermissionMapper.selectAllChildId(childIds);
        }
        sysPermissionMapper.batchDeleteIds(ids);
        sysPermissionMapper.batchDeletePermIds(ids);
        return new Result();
    }


}
