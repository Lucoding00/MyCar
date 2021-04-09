package com.whut.service;


import com.whut.common.Result;
import com.whut.form.BusCustomerForm;
import com.whut.query.BusCustomerQuery;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author 鲁佳磊
 * @create 2021-04-06 14:00
 */
public interface BusCustomerService {
    Result queryPage(BusCustomerQuery busCustomerQuery);

    Result addUser(BusCustomerForm busCustomerForm);

    Result updateCustomer(BusCustomerForm busCustomerForm);


    void exportCustomer(BusCustomerQuery busCustomerQuery, HttpServletResponse httpServletResponse) throws IOException;

    /**
     * 批量处理这个客户的信息，添加这个客户。
     * @param multipartFile
     * @return
     */
    Result importCustomer(MultipartFile multipartFile) throws IOException;
}
