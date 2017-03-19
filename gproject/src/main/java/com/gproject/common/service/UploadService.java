package com.gproject.common.service;

import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.common.facade.UploadFacade;
import com.gproject.util.message.ResponseType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * Created by Administrator on 2017/3/19 0019.
 */
@Service
public class UploadService extends BaseService<Object,Integer> implements UploadFacade {
    @Value("${VIRTUAL_PATH}")
    private String VIRTUAL_PATH;

    @Override
    protected BaseMapper<Object, Integer> getMapper() {
        return null;
    }

    @Override
    protected String getMapperNameSpace() {
        return null;
    }

    @Override
    protected String getDefalultDatabase() {
        return null;
    }

    public Object uploadPicture(MultipartFile file) {
        String path="";
        if (file != null && file.getOriginalFilename() != null
                && file.getOriginalFilename().length() > 0) {

            // 获取文件原始名称，用于获取后缀名
            String filename = file.getOriginalFilename();
            //地址
            String pic_path = VIRTUAL_PATH;
            // 设置文件新名称，随机数字+后缀名
            String newFileName = UUID.randomUUID()
                    + filename.substring(filename.lastIndexOf("."));
            // 创建新文件，路径+文件名称
            File newFile = new File(pic_path+"\\"+ newFileName);
            path="upload/"+newFileName;
            // 把文件从内存写入磁盘
            try{
                file.transferTo(newFile);
            }catch (Exception e){
                return FAIL(ResponseType.SYSTEM_ERROR,"upload error");
            }
        }
        return SUCCESS(path);
    }
}
