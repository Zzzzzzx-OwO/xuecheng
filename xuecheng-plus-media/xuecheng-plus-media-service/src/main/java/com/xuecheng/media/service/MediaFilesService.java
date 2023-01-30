package com.xuecheng.media.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.model.po.MediaFiles;

/**
 * <p>
 * 媒资信息 服务类
 * </p>
 *
 * @author itcast
 * @since 2023-01-30
 */
public interface MediaFilesService extends IService<MediaFiles> {

    public UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto,byte[] bytes,String folder,String objectName);

}
