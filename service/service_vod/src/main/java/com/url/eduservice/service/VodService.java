package com.url.eduservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xidazhen
 * @date 2022/9/29 - 13:40
 */
public interface VodService {
    String uploadAlyVideo(MultipartFile file);

    Boolean deleteAlyVideo(String id);

    Boolean deleteBatch(List videoList);
}
