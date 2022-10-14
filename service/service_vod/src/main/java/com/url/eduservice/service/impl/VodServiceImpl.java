package com.url.eduservice.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.url.eduservice.service.VodService;
import com.url.eduservice.utils.ConstantVodUtils;
import com.url.eduservice.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.HEAD;
import java.io.InputStream;
import java.util.List;


/**
 * @author xidazhen
 * @date 2022/9/29 - 13:40
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadAlyVideo(MultipartFile file) {
        try {

            System.out.println(ConstantVodUtils.ACCESS_KEY_ID +ConstantVodUtils.ACCESS_KEY_SECRET);
            String filename = file.getOriginalFilename();
//            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String title = filename.substring(0,filename.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET,title,filename,inputStream);

            UploadVideoImpl uploadVideo = new UploadVideoImpl();
            UploadStreamResponse response = uploadVideo.uploadStream(request);

            System.out.println("RequestId:" + response.getRequestId());

            String videoId = null;
            if(response.isSuccess()){
                System.out.println("VideoId:" + response.getVideoId());
            }else {
                System.out.println("VideoId:" + response.getVideoId());
                System.out.println("ErrorCode:" + response.getCode());
                System.out.println("ErrorMessage:" + response.getMessage());
            }
            videoId = response.getVideoId();
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteAlyVideo(String id) {
        try {
            System.out.println(ConstantVodUtils.ACCESS_KEY_ID + ConstantVodUtils.ACCESS_KEY_SECRET);
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteBatch(List videoList) {
        try {
            System.out.println(ConstantVodUtils.ACCESS_KEY_ID + ConstantVodUtils.ACCESS_KEY_SECRET);
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            String videoIds = StringUtils.join(videoList.toArray(), ",");
            System.out.println(videoIds);
            request.setVideoIds(videoIds);
            client.getAcsResponse(request);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
