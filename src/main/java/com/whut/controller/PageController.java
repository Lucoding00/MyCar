package com.whut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 视图转化的时候使用的controller层
 *
 * @author 鲁佳磊
 * @create 2021-04-05 23:03
 */

@Controller
@RequestMapping("page")
public class PageController {
    /**
     * 转到主页面去，然后开始之后的操作
     *
     * @return
     */
    @RequestMapping("main.do")
    public String main() {
        return "main";
    }

    /**
     * 转化到userlist中的这个jsp页面，然后来写的。
     *
     * @return
     */
    @RequestMapping("user/list.do")
    public String userList() {
        return "user/list";
    }

    /**
     * 用户管理页面，进行管理操作
     *
     * @return
     */
    @RequestMapping("customer/list.do")
    public String customerList() {
        return "customer/list";
    }

    /**
     * 车辆管理
     *
     * @return
     */
    @RequestMapping("car/list.do")
    public String carList() {
        return "car/list";
    }

    @RequestMapping("rent/list.do")
    public String rentList() {
        return "rent/list";
    }

    @RequestMapping("return/list.do")
    public String returnList() {
        return "return/list";
    }


    /**
     * 角色列表，然后在这个地方进行设置，这个就是觉得表的设置
     *
     * @return
     */
    @RequestMapping("role/list.do")
    public String roleList() {
        return "role/list";
    }

    @RequestMapping("perm/list.do")
    public String permList() {
        return "perm/list";
    }


}
