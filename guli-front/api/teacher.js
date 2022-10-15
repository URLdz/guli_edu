import request from '@/utils/request'

export default {
  //查询讲师信息方法
  getTeacherList(current,limit) {
    return request({
        url: `/eduservice/teacherfront/getTeacherFront/${current}/${limit}`,
        method: 'get'
      })
  },
  getTeacherInfo(id){
    return request({
        url: `/eduservice/teacherfront/getTeacherFrontInfo/${id}`,
        method: 'get'
      })
  }
}
