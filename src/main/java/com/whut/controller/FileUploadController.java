package com.whut.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.whut.common.CodeMsg;
import com.whut.common.Constant;
import com.whut.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author 鲁佳磊
 * @create 2021-04-06 20:49
 */

@RestController
@RequestMapping("file")
public class FileUploadController {
    /**
     * 上传图片的接口
     *
     * @param image
     * @return
     */
    @RequestMapping("uploadImage.do")
    public Object uploadImage(@RequestParam("image") MultipartFile image, HttpServletRequest request) {
        //原来的名称
        String originalFilename = image.getOriginalFilename();
        //这个是解析这个文件的后缀名
        String extName = FileUtil.extName(originalFilename);
        String newFilename = DateUtil.format(new Date(), "yyyyMMddHHssmm");
        newFilename += "." + extName;
        //获取upload文件加路径
        String upload = request.getServletContext().getRealPath(Constant.UPLOAD_FOLDER);
        //文件保存的物理路径
        String filePath = upload + File.separator + newFilename;
        //需要保存的网络路径
        String url = Constant.UPLOAD_FOLDER + "/"+newFilename;
        try {
            image.transferTo(new File(filePath));
            //将网络的路径进行返回
            return new Result(url);
        }catch (IOException e){
            e.printStackTrace();
        }
        //上传文件路径错误
        return new Result(CodeMsg.CAR_UPLOAD_IMG_ERROR);
    }
}
