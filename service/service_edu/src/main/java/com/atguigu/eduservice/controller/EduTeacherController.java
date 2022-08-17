package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-08-12
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {


    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "查询所有讲师")
    @GetMapping("findAll")
    public com.atguigu.commonutils.R findAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    /**
     * id值需要通过路径获取
     * @return
     */
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    /**
     * 分页查询讲师
     * @param current
     * @param limit
     * @return
     */
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current",value = "当前页",required = true)@PathVariable long current,
                             @ApiParam(name = "limit",value = "显示条数",required = true)@PathVariable long limit){
        //创建配置对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //调用方法实现
         eduTeacherService.page(pageTeacher, null);
         long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> recoras = pageTeacher.getRecords();//数据list集合

//        Map map = new HashMap();
//        map.put("total",total);
//        map.put("recoras",recoras);
//        return R.ok().data("map",map);
        return R.ok().data("total",total).data("recoras",recoras);
    }

    /**
     * 条件查询带分页
     * @param current
     * @param limit
     * @param teacherQuery
     * @return
     */
    @ApiOperation(value = "条件查询带分页")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "current",value = "当前页",required = true)@PathVariable long current,
                                  @ApiParam(name = "limit",value = "显示条数",required = true)@PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        //创建配置对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //mybatis动态sql
        //判断条件指是否为空，如果不为空拼接条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
            //构建条件
            wrapper.like("name",name);
        }
        if(null != level){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法
        eduTeacherService.page(pageTeacher,wrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords(); //数据集合
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }
    }

    /**
     * 查询讲师
     * @param id
     * @return
     */
    @ApiOperation(value = "查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
         EduTeacher eduTeacher = eduTeacherService.getById(id);
         return R.ok().data("teacher",eduTeacher);
    }

    @ApiOperation(value = "修改讲师信息")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean update = eduTeacherService.updateById(eduTeacher);
        if(update){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @ApiOperation(value = "根据id修改讲师")
    @PutMapping("{id}")
    public R updateById(@ApiParam(name = "id",value = "讲师id",required = true)@PathVariable String id,
                        @ApiParam(name = "eduTeacher",value = "讲师对象",required = true)@RequestBody EduTeacher eduTeacher){
        eduTeacher.setId(id);
        eduTeacherService.updateById(eduTeacher);
        return R.ok();
    }
}

