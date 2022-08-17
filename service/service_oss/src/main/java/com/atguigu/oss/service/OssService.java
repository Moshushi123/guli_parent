package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @version 1.0
 * @auther 刘欢龙
 */
public interface OssService {

    String uploadFileAvatar(MultipartFile file) throws IOException;
}
