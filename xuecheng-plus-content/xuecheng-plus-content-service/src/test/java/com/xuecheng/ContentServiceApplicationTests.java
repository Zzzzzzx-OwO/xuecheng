package com.xuecheng;


import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.model.dto.QueryCourseParamsDto;
import com.xuecheng.model.po.CourseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContentServiceApplicationTests {

    @Autowired
    CourseBaseMapper courseBaseMapper;
    @Autowired
    CourseBaseInfoService courseBaseInfoService;
    @Test
    void testCourseBaseMapper(){
        CourseBase courseBase = courseBaseMapper.selectById(22);
        Assertions.assertNotNull(courseBase);
    }

    @Test
    void testCourseBaseInfoService(){
        PageParams pageParams = new PageParams();
        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(pageParams,new QueryCourseParamsDto());
        System.out.println(courseBasePageResult);
    }

}
