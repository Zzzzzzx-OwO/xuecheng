package com.xuecheng.content.mapper;

import com.xuecheng.content.model.po.CourseCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.model.dto.CourseCategoryTreeDto;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {
    List<CourseCategoryTreeDto> selectTreeNodes(String id);
}
