import request from '@/utils/request'

export default {
  getPageList(current, limit, courseId) {
        return request({
          url: `/eduservice/comment/getCommentListPage/${current}/${limit}`,
          method: 'get',
          params: {courseId}
    
        })
    
      },

  addComment(comment) {
    return request({
      url: `/eduservice/comment/addComment`,
      method: 'post',
      data: comment

    })
  }

}