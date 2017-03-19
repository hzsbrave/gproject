package com.gproject.common.facade;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2017/3/19 0019.
 */
public interface UploadFacade {

    public Object uploadPicture(MultipartFile file);

}
