import request from '@/utils/request'

export default {
  //1.根据课程获取章节和小节数据列表
  getAllChapterVideo(courseId) {
    return request({
      url: `/eduservice/chapter/getChapterVedio/${courseId}`,
      method: 'get'
    })
  },
  addChapter(chapter) {
    return request({
      url: `/eduservice/chapter/addChapter`,
      method: 'post',
      data: chapter
    })
  },
  getChapter(chapterId) {
    return request({
      url: `/eduservice/chapter/getChapterInfo/${chapterId}`,
      method: 'get'
    })
  },
  updateChapter(chapter) {
    return request({
      url: `/eduservice/chapter/updateChapter`,
      method: 'post',
      data: chapter
    })
  },
  deleteChapter(chapterId) {
    return request({
      url: `/eduservice/chapter/deleteChapter/${chapterId}`,
      method: 'delete'
    })
  },

}

