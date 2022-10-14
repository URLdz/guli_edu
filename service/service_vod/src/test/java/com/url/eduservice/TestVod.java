package com.url.eduservice;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.url.eduservice.utils.ConstantVodUtils;
import org.junit.Test;

import java.util.List;

/**
 * @author xidazhen
 * @date 2022/9/28 - 21:25
 */
public class TestVod {
    //获取视频播放地址
    @Test
    public void test1() throws Exception{
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);

        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //向request对象里面设置视频id
        request.setVideoId("728774de8ce54b1494c908f781473ea8");

        //调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }

    //获取视频播放凭证
    @Test
    public void test2() throws Exception{
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);

        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        request.setVideoId("728774de8ce54b1494c908f781473ea8");
        response = client.getAcsResponse(request);
        System.out.println(response);
    }

    //获取视频播放凭证
    @Test
    public void test3() throws Exception{
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);

        //创建获取视频地址request和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        request.setVideoId("728774de8ce54b1494c908f781473ea8");
        response = client.getAcsResponse(request);
        System.out.println(response.getPlayAuth());
    }


    @Test
    public void upload(){
        String accessKeyId = ConstantVodUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantVodUtils.ACCESS_KEY_SECRET;
        String fileName = "D:\\0课程\\谷粒学苑\\项目资料\\1-阿里云上传测试视频\\6 - What If I Want to Move Faster.mp4";
        String title = "testfile1";
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        request.setPartSize(2*1024*1024L);
        request.setTaskNum(1);
        UploadVideoImpl uploadVideo = new UploadVideoImpl();
        UploadVideoResponse response = uploadVideo.uploadVideo(request);
        System.out.println("RequestId:" + response.getRequestId());
        if(response.isSuccess()){
            System.out.println("VideoId:" + response.getVideoId());
        }else {
            System.out.println("VideoId:" + response.getVideoId());
            System.out.println("ErrorCode:" + response.getCode());
            System.out.println("ErrorMessage:" + response.getMessage());
        }

    }

}
