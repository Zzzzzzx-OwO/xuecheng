package com.xuecheng.content.api;

import com.xuecheng.base.exception.ValidationGroups;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description TODO
 * @author Mr.M
 * @date 2022/10/7 16:22
 * @version 1.0
 */
@Api(value = "课程管理接口",tags = "课程管理接口")
@RestController
public class CourseBaseInfoController {

    @Autowired
    CourseBaseInfoService courseBaseInfoService;

    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
  public PageResult<CourseBase> list(PageParams params, @RequestBody QueryCourseParamsDto queryCourseParamsDto){
        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(params,queryCourseParamsDto);
        return  courseBasePageResult;
    }


    @ApiOperation("新增课程接口")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody @Validated AddCourseDto addCourseDto){
        Long companyid = 22L;
        CourseBaseInfoDto courseBase = courseBaseInfoService.createCourseBase(companyid,addCourseDto);
        return courseBase;
    }

    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto getCourseBaseById(@PathVariable Long courseId){


        return courseBaseInfoService.getCourseBaseInfo(courseId);
    }

    @PutMapping("/course")
    public CourseBaseInfoDto modifyCourseBase(@RequestBody EditCourseDto dto){

        Long companyId =1232141425L;
        return courseBaseInfoService.updateCourseBase(companyId,dto);
    }
}
