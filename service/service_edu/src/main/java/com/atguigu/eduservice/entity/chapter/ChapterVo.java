package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @auther 刘欢龙
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    //字节
    private List<VideoVo> children = new ArrayList<>();
}
