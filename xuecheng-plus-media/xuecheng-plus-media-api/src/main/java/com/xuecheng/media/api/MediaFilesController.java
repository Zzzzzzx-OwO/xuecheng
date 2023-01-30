package com.xuecheng.media.api;

import com.alibaba.nacos.common.http.param.MediaType;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.service.MediaFilesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 媒资信息 前端控制器
 * </p>
 *
 * @author itcast
 */
@Api(value = "媒资文件管理接口" ,tags = "媒资文件管理接口")
@RestController
public class MediaFilesController {

    @Autowired
    MediaFilesService mediaFilesService;

    @ApiOperation("媒资文件上传接口")
    @RequestMapping(value = "/upload/coursefile", consumes = {MediaType.MULTIPART_FORM_DATA})
    public UploadFileResultDto upload(@RequestPart("filedata") MultipartFile filedata,
                                      @RequestParam(value = "folder",required = false) String folder,
                                      @RequestParam(value = "objectName",required = false) String objectName) {
        Long companyId = 1232141425L;
        UploadFileParamsDto uploadFileParamsDto = new UploadFileParamsDto();
        String contentType = filedata.getContentType();
        uploadFileParamsDto.setContentType(contentType);
        uploadFileParamsDto.setFileSize(filedata.getSize());
        if (contentType.indexOf("image") >= 0) {
            uploadFileParamsDto.setFileType("001001");
        } else {
            uploadFileParamsDto.setFileType("001003");
        }
        uploadFileParamsDto.setFilename(filedata.getOriginalFilename());
        UploadFileResultDto uploadFileResultDto = new UploadFileResultDto();
        try {
            uploadFileResultDto = mediaFilesService.uploadFile(companyId, uploadFileParamsDto, filedata.getBytes(), folder, objectName);
        } catch (Exception e) {
            XueChengPlusException.cast("上传文件过程中出错");
        }
        return uploadFileResultDto;
    }


}

