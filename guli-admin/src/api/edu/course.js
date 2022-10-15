import request from '@/utils/request'

export default {
  //1.添加课程
  addCourseInfo(courseInfo) {
    return request({
      url: `/eduservice/course/addCourseInfo`,
      method: 'post',
      data: courseInfo
    })
  },
  //2.查询所有讲师
  getListTeacher() {
    return request({
      url: `/eduservice/teacher/findAll`,
      method: 'get'
    })
  },
  //3.根据课程id查询课程基本信息
  getCourseInfoId(courseId){
    return request({
      url:`/eduservice/course/getCourseInfo/${courseId}`,
      method:'get'
    })
  },
  updateCourseInfo(courseInfo){
    return request({
      url:`/eduservice/course/updateCourseInfo`,
      method:'post',
      data: courseInfo
    }) 
  },
  getPublishCourseInfo(courseId){
    return request({
      url:`/eduservice/course/getPublishCourseInfo/${courseId}`,
      method:'get'
    }) 
  },
  publishCourse(courseId){
    return request({
      url:`/eduservice/course/publishCourse/${courseId}`,
      method:'post'
    }) 
  },
  publishCourse(courseId){
    return request({
      url:`/eduservice/course/publishCourse/${courseId}`,
      method:'post'
    }) 
  },

  getCourseListPage(current, limit, courseQuery) {
    return request({
      url: `/eduservice/course/pageCourseCondition/${current}/${limit}`,
      method: 'post',
      //RequestBody获取数据 data将对象转换为json传递到接口中
      data: courseQuery
    })
  },
  
  deleteCourseId(id) {
    return request({
      url: `/eduservice/course/${id}`,
      method: 'delete'
    })
  },
}

