import request from '@/utils/request'

export default {
  //查询讲师信息方法
  getCourseList(current,limit,searchObj) {
    return request({
        url: `/eduservice/coursefront/getCourseFront/${current}/${limit}`,
        method: 'post',
        data:searchObj
      })
  },
  getAllSubject(){
    return request({
      url: `/eduservice/subject/getAllSubject`,
      method: 'get'
    })
  },
  getCourseInfo(id){
    return request({
      url: `/eduservice/coursefront/getFrontCourseInfo/${id}`,
      method: 'get'
    })
  }


}
