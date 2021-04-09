package com.whut.controller;


import cn.hutool.core.util.StrUtil;
import com.whut.common.CodeMsg;
import com.whut.common.Constant;
import com.whut.common.Result;
import com.whut.common.validator.ValidatorUtil;
import com.whut.form.SysUserForm;
import com.whut.query.SysUserQuery;
import com.whut.service.SysUserService;
import com.whut.shiro.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块使用时使用的模块层
 *
 * @author 鲁佳磊
 * @create 2021-04-05 23:03
 */
@RestController
@RequestMapping("sysuser")
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;

    /**
     * 使用登录名称还有密码来进行登录
     *
     * @param username 名称
     * @param password 密码
     * @return 最后的一个结果
     */
    @PostMapping("login.do")
    public Object login(String username, String password) {
        /*对传输过来的密码进行加密！！！*/
        Md5Hash md5Hash = new Md5Hash(password, Constant.MD5_SALT, 2);
        /*使用用户名还有密码来得到这个token的值，然后在这个地方进行之后的操作*/
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, md5Hash.toString());
        /*得到这个都西昂，使用这个对象来进行认证。*/
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        /*如果为true，那么就已经认证*/
        return new Result();
    }

    /**
     * 退出用户登录
     */
    @RequestMapping("logout.do")
    public Object logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {

            subject.logout();
        }
        /*返回默认的登录的数据模块，然后在这个地方*/
        return new Result();
    }

    @RequestMapping("page.do")
    public Object userList(SysUserQuery sysUserQuery){
        return sysUserService.queryPage(sysUserQuery);
    }

    /**
     * 这个是新增一个用户，然后放到这个这边
     * @param sysUserForm
     * @return
     */
    @RequestMapping("add.do")
    public Object addUser(SysUserForm sysUserForm){
        //进行数据格式的校验，然后得到之后的数据。
        ValidatorUtil.validator(sysUserForm);
        return sysUserService.addUser(sysUserForm);
    }

    /**
     * 重置密码的操作
     * @param id 前端传递过来的一个数据的id值，然后进行之后的操作
     * @return
     */
    @RequestMapping("reset.do")
    public Object reset(Integer id){
        return sysUserService.resetPwd(id);
    }


    @RequestMapping("updatePassword.do")
    public Object updatePassword(String password,String newPassword){
        //校验当前密码还有实际的密码
        Subject subject = SecurityUtils.getSubject();
        ActiveUser user = (ActiveUser) subject.getPrincipal();
        String loginPassword = user.getSysUser().getLoginPassword();
        Md5Hash md5Hash = new Md5Hash(password,Constant.MD5_SALT,2);
        if (!StrUtil.equals(loginPassword,md5Hash.toString())){
            return new Result(CodeMsg.USER_UPDATE_PASSWORD_ERROR);
        }
        Md5Hash md5Hash1 = new Md5Hash(newPassword, Constant.MD5_SALT, 2);
        Result result = sysUserService.updatePassword(user.getSysUser().getId(), md5Hash1.toString());
        if (result.getCode().equals(200)){
            subject.logout();//退出操作，进行之后的操作
        }
        return result;
    }
    @RequestMapping("updateHeaderImg.do")
    public Object updateHeaderImg(String img){
//        直接改变当前用户的图片的地址
        Result result = sysUserService.updateHeaderImg(img);
        return result;            //创建新的最后一个结果，然后就是可以设置这个结果
    }

}
