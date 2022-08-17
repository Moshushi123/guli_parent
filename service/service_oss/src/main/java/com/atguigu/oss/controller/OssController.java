package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @version 1.0
 * @auther 刘欢龙
 */
@Api("文件上传")
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像的方法
    @PostMapping("uploadOssFile")
    public R uploadOssFile(MultipartFile file){
        //获取上传文件
        String url = null;
        System.out.println(file);
        //返回上传到oss的路径
        try {
            url = ossService.uploadFileAvatar(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        return R.ok().data("url",url);
    }
}
