package com.xuecheng.media.service.Impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.media.mapper.MediaFilesMapper;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.model.po.MediaFiles;
import com.xuecheng.media.service.MediaFilesService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Media;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * <p>
 * 媒资信息 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class MediaFilesServiceImpl extends ServiceImpl<MediaFilesMapper, MediaFiles> implements MediaFilesService {
    @Autowired
    MinioClient minioClient;
    @Autowired
    MediaFilesMapper mediaFilesMapper;
    @Value("${minio.bucket.files}")
    private String bucket_files;

    @Override
    public UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, byte[] bytes, String folder, String objectName) {
        String contentType = uploadFileParamsDto.getContentType();
        String fileMd5 = DigestUtils.md5Hex(bytes);
        if(StringUtils.isEmpty(folder)){
            folder = getFileFolder(new Date(),true,true,true);
        } else if (folder.indexOf("/")<0) {
            folder = folder+"/";
        }
        String filename = uploadFileParamsDto.getFilename();
        if (StringUtils.isEmpty(objectName)){
            objectName = fileMd5 + filename.substring(filename.lastIndexOf("."));
        }
        objectName = folder + objectName;

        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucket_files)
                    .object(objectName)
                    .stream(byteArrayInputStream,byteArrayInputStream.available(),-1)
                    .contentType(contentType)
                    .build();
            minioClient.putObject(putObjectArgs);

            //保存到数据库
            MediaFiles mediaFiles = mediaFilesMapper.selectById(fileMd5);
            if(mediaFiles == null){
                mediaFiles = new MediaFiles();

                BeanUtils.copyProperties(uploadFileParamsDto,mediaFiles);
                mediaFiles.setId(fileMd5);
                mediaFiles.setFileId(fileMd5);
                mediaFiles.setCompanyId(companyId);
                mediaFiles.setFilename(filename);
                mediaFiles.setBucket(bucket_files);
                mediaFiles.setFilePath(objectName);
                mediaFiles.setUrl("/"+bucket_files+"/"+objectName);
                mediaFiles.setCreateDate(LocalDateTime.now());
                mediaFiles.setStatus("1");
                mediaFiles.setAuditStatus("002003");
                mediaFilesMapper.insert(mediaFiles);
            }
            UploadFileResultDto uploadFileResultDto = new UploadFileResultDto();
            BeanUtils.copyProperties(mediaFiles,uploadFileResultDto);
            return uploadFileResultDto;




        }catch (Exception e){
            log.debug("上传文件失败:{}",e.getMessage());
        }
        return null;


    }

    private String getFileFolder(Date date, boolean year, boolean month, boolean day){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前日期字符串
        String dateString = sdf.format(new Date());
        //取出年、月、日
        String[] dateStringArray = dateString.split("-");
        StringBuffer folderString = new StringBuffer();
        if(year){
            folderString.append(dateStringArray[0]);
            folderString.append("/");
        }
        if(month){
            folderString.append(dateStringArray[1]);
            folderString.append("/");
        }
        if(day){
            folderString.append(dateStringArray[2]);
            folderString.append("/");
        }
        return folderString.toString();
    }
}
