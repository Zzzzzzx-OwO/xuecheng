package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.service.TeachplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO
 * @author Mr.M
 * @date 2022/10/10 11:55
 * @version 1.0
 */

 @RestController
public class TeachplanController {

     @Autowired
    TeachplanService teachplanService;

  @GetMapping("/teachplan/{courseId}/tree-nodes")
  public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId){
    return teachplanService.findTeachplayTree(courseId);
  }

  @PostMapping("/teachplan")
  public void saveTeachplan(@RequestBody SaveTeachplanDto dto){
      teachplanService.saveTeachplan(dto);
  }
}
