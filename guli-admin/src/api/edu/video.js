import request from '@/utils/request'

export default {
  //1.根据课程获取章节和小节数据列表
  
  addVideo(video) {
    return request({
      url: `/eduservice/video/addVideo`,
      method: 'post',
      data: video
    })
  },
  getVideo(videoId) {
    return request({
      url: `/eduservice/video/getVideoInfo/${videoId}`,
      method: 'get'
    })
  },
  updateVideo(video) {
    return request({
      url: `/eduservice/video/updateVideo`,
      method: 'post',
      data: video
    })
  },
  deleteVideo(videoId) {
    return request({
      url: `/eduservice/video/${videoId}`,
      method: 'delete'
    })
  },
  deleteAliyunvod(id){
    return request({
      url:`/eduvod/video/deleteAlyVideo/${id}`,
      method:'delete'
    })
  }

}

