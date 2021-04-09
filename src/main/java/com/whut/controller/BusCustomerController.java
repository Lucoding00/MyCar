package com.whut.controller;


import com.whut.common.validator.ValidatorUtil;
import com.whut.form.BusCustomerForm;
import com.whut.query.BusCustomerQuery;
import com.whut.service.BusCustomerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 鲁佳磊
 * @create 2021-04-06 13:56
 */
@RestController
@RequestMapping("customer")
public class BusCustomerController {
    @Autowired
    private BusCustomerService busCustomerService;


    @RequestMapping("page.do")
    public Object userList(BusCustomerQuery busCustomerQuery){
        return busCustomerService.queryPage(busCustomerQuery);
    }

    @RequestMapping("add.do")
    public Object addCustomer(BusCustomerForm busCustomerForm){
        //进行数据格式的校验，然后得到之后的数据。
        ValidatorUtil.validator(busCustomerForm);
        return busCustomerService.addUser(busCustomerForm);
    }

    @RequestMapping("update.do")
    public Object updateCustomer(BusCustomerForm busCustomerForm){
        //进行数据格式的校验，然后得到之后的数据。
        ValidatorUtil.validator(busCustomerForm);
        return busCustomerService.updateCustomer(busCustomerForm);
    }


    @RequestMapping("export.do")
    public void exportCustomer(BusCustomerQuery busCustomerQuery,HttpServletResponse httpServletResponse) throws IOException {
        busCustomerService.exportCustomer(busCustomerQuery,httpServletResponse);
    }

    /**
     * 处理文件上传请求
     * @param multipartFile
     * @return
     */
    @RequestMapping("import.do")
    public Object importCustomer(@RequestParam("customers") MultipartFile multipartFile) throws IOException {
        return busCustomerService.importCustomer(multipartFile);
    }


}
