package com.url.eduservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xidazhen
 * @date 2022/9/21 - 14:12
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}

