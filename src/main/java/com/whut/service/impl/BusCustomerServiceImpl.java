package com.whut.service.impl;

import cn.hutool.http.HttpResponse;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whut.common.CodeMsg;
import com.whut.common.Constant;
import com.whut.common.Result;
import com.whut.form.BusCustomerForm;
import com.whut.mapper.BusCustomerMapper;
import com.whut.model.BusCustomer;
import com.whut.query.BusCustomerQuery;
import com.whut.query.SysUserQuery;
import com.whut.service.BusCustomerService;
import com.whut.vo.BusCustomerVo;
import com.whut.vo.SysUserVo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @author 鲁佳磊
 * @create 2021-04-06 14:00
 */
@Service
public class BusCustomerServiceImpl implements BusCustomerService {
    @Autowired
    private BusCustomerMapper busCustomerMapper;

    @Override
    public Result queryPage(BusCustomerQuery busCustomerQuery) {
        Page<BusCustomer> objects = PageHelper.startPage(busCustomerQuery.getPage(), busCustomerQuery.getLimit());
        List<BusCustomerVo> sysUserVoList = busCustomerMapper.selectListByUserQuery(busCustomerQuery);
        return new Result(objects.toPageInfo());
    }

    @Override
    public Result addUser(BusCustomerForm busCustomerForm) {
        //业务操作
        BusCustomerQuery busCustomerQuery = new BusCustomerQuery();
        //用户名不可以重复
        busCustomerQuery.setPhone(busCustomerForm.getPhone());
        BusCustomerVo busCustomerVo = busCustomerMapper.selectUserByNameOrPhoneOrIdcard(busCustomerQuery);
        if (busCustomerVo != null) {
            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
            return new Result(CodeMsg.CUSTOMER_PHONE_EXIST_ERROR);
        }
        //手机号不可以重复
        busCustomerQuery = new BusCustomerQuery();
        busCustomerQuery.setIdCard(busCustomerForm.getIdCard());
        busCustomerVo = busCustomerMapper.selectUserByNameOrPhoneOrIdcard(busCustomerQuery);
        if (busCustomerVo != null) {
            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
            return new Result(CodeMsg.CUSTOMER_ID_CARD_EXIST_ERROR);
        }

        /*修改本地数据库的一个版本的错误*/
        busCustomerForm.setCreateTime(new Date());
        busCustomerForm.setUpdateTime(new Date());
        busCustomerMapper.insertSysUserForm(busCustomerForm);
        return new Result();
    }

    @Override
    public Result updateCustomer(BusCustomerForm busCustomerForm) {

        //业务操作
        BusCustomerQuery busCustomerQuery = new BusCustomerQuery();
        //用户名不可以重复
        busCustomerQuery.setPhone(busCustomerForm.getPhone());
        BusCustomerVo busCustomerVo = busCustomerMapper.selectUserByNameOrPhoneOrIdcard(busCustomerQuery);
        if (busCustomerVo != null && !busCustomerVo.getId().equals(busCustomerForm.getId())) {
            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
            return new Result(CodeMsg.CUSTOMER_PHONE_EXIST_ERROR);
        }
        //手机号不可以重复
        busCustomerQuery = new BusCustomerQuery();
        busCustomerQuery.setIdCard(busCustomerForm.getIdCard());
        busCustomerVo = busCustomerMapper.selectUserByNameOrPhoneOrIdcard(busCustomerQuery);
        if (busCustomerVo != null && !busCustomerVo.getId().equals(busCustomerForm.getId())) {
            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
            return new Result(CodeMsg.CUSTOMER_ID_CARD_EXIST_ERROR);
        }
        busCustomerMapper.updateById(busCustomerForm);
        return new Result();
    }

    @Override
    public void exportCustomer(BusCustomerQuery busCustomerQuery, HttpServletResponse httpServletResponse) throws IOException {
        //符合条件的各户
        //response为HttpServletResponse对象
//        httpServletResponse.setContentType("application/vnd.ms-excel;charset=utf-8");
////        //test.xls是弹出下载对话框的 文件名，不能为中文，中文请自行编码
        String encode = URLEncoder.encode("客户信息.xlsx", "utf-8");
////        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + encode);
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        httpServletResponse.setHeader("Content-Disposition","attachment;filename="+encode);


        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        List<BusCustomerVo> busCustomerVos = busCustomerMapper.selectListByUserQuery(busCustomerQuery);
        ExcelWriter writer = ExcelUtil.getWriter(true);

        //自定义标题别名
        writer.addHeaderAlias("id", "序号");
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("idCard", "身份证");
        writer.addHeaderAlias("sex", "性别");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.addHeaderAlias("updateTime", "更改时间");

        writer.write(busCustomerVos, true);
        writer.flush(outputStream, true);
        writer.close();
    }

    @Override
    public Result importCustomer(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //将数据转化成为list对象,在这个地方必须是设置车成为是这个英文的头部，然后进行之后的设置
        List<BusCustomerVo> busCustomerVos = reader.readAll(BusCustomerVo.class);
//        进行插入操作，在这个地方机型设置的时候应该进行判断，然后操作之后操作。
        busCustomerMapper.batchInsert(busCustomerVos);
        return new Result();
    }
}
