package com.whut.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whut.common.CodeMsg;
import com.whut.common.Constant;
import com.whut.common.Result;
import com.whut.form.SysUserForm;
import com.whut.mapper.SysUserMapper;
import com.whut.model.SysUser;
import com.whut.query.SysUserQuery;
import com.whut.service.SysUserService;
import com.whut.shiro.ActiveUser;
import com.whut.vo.SysUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 鲁佳磊
 * @create 2021-04-05 22:53
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 根据用户名和密码来查询数据
     * @param username
     * @param password
     * @return
     */
    @Override
    public Result queryUser(String username, String password) {
        SysUser user = sysUserMapper.selectUserByNameAndPwd(username,password);
        if (user==null){
            return new Result(CodeMsg.USER_USER_PASSWORD_ERROR);
        }
        //默认使用这个正确的代码，然后使用这个数据当中这个返回的一个数据放到这个上面
        return new Result(user);
    }

    @Override
    public Object queryPage(SysUserQuery sysUserQuery) {
        Page<SysUser> objects = PageHelper.startPage(sysUserQuery.getPage(), sysUserQuery.getLimit());
        List<SysUserVo> sysUserVoList = sysUserMapper.selectListByUserQuery(sysUserQuery);
        return new Result(objects.toPageInfo());
    }

    @Override
    public Result addUser(SysUserForm sysUserForm) {
        //业务操作
        SysUserQuery sysUserQuery = new SysUserQuery();
        //用户名不可以重复
        sysUserQuery.setLoginName(sysUserForm.getLoginName());
        SysUserVo sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdcard(sysUserQuery);
        if (sysUserVo!=null){
            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
            return new Result(CodeMsg.USER_LOGIN_NAME_EXIST_ERROR);
        }
        //手机号不可以重复
        sysUserQuery=new SysUserQuery();
        sysUserQuery.setPhone(sysUserForm.getPhone());
        sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdcard(sysUserQuery);
        if (sysUserVo!=null){
            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
            return new Result(CodeMsg.USER_PHONE_EXIST_ERROR);
        }
        //身份证不可以重复
        sysUserQuery=new SysUserQuery();
        sysUserQuery.setIdCard(sysUserForm.getIdCard());
        sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdcard(sysUserQuery);
        if (sysUserVo!=null){
            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
            return new Result(CodeMsg.USER_ID_CARD_EXIST_ERROR);
        }
        //对默认密码进行加密，然后设置到这个里面去
        Md5Hash md5Hash = new Md5Hash(Constant.DEFAULT_PASSWORD,Constant.MD5_SALT,2);
        sysUserForm.setLoginPassword(md5Hash.toString());
        sysUserMapper.insertSysUserForm(sysUserForm);
        return new Result();
    }

    /**
     * 修改密码的操作
     * @param id
     * @return
     */
    @Override
    public Result resetPwd(Integer id) {
        Md5Hash md5Hash = new Md5Hash(Constant.DEFAULT_PASSWORD,Constant.MD5_SALT,2);
        //根据id查出来这个用户的对象
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        sysUser.setLoginPassword(md5Hash.toString());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return new Result();
    }

    @Override
    public Result updatePassword(Integer id, String newPassword) {
        sysUserMapper.updatePasword(id,newPassword);
        return new Result();
    }

    @Override
    public Result updateHeaderImg(String img) {
        Subject subject = SecurityUtils.getSubject();
        ActiveUser principal = (ActiveUser) subject.getPrincipal();
        Integer id = principal.getSysUser().getId();
        sysUserMapper.updateUserHeaderImg(id,img);
        return new Result();
    }
}
