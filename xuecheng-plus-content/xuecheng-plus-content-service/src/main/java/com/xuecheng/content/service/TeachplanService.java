package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;

import java.util.List;

/**
 * @description TODO
 * @author Mr.M
 * @date 2022/10/10 14:51
 * @version 1.0
 */
public interface TeachplanService {

 public List<TeachplanDto> findTeachplayTree(Long courseId);

 /**
  * @description 保存课程计划(新增/修改)
  * @param dto
  * @return void
  * @author Mr.M
  * @date 2022/10/10 15:07
 */
 public void saveTeachplan(SaveTeachplanDto dto);

}
