package com.xuecheng.media.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.base.model.RestResponse;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.model.po.MediaFiles;

import java.io.File;

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



    public MediaFiles addMediaFilesToDb(Long companyId,String fileId,UploadFileParamsDto uploadFileParamsDto,String objectName,String bucket);

    public RestResponse<Boolean> checkFile(String fileMd5);

    public RestResponse<Boolean> checkChunk(String fileMd5, int chunkIndex);

    public RestResponse uploadChunk(String fileMd5, int chunk, byte[] bytes);

    public RestResponse mergechunks(Long companyId,String fileMd5,int chunkTotal,UploadFileParamsDto uploadFileParamsDto);

    public MediaFiles getFileById(String id);

    public File downloadFileFromMinIO(File file, String bucket, String objectName);

    public void addMediaFilesToMinIO(String filePath, String bucket, String objectName);
}
