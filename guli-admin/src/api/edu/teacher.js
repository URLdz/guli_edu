import request from '@/utils/request'

export default {
  //1.讲师列表(条件查询分页)
  //current 当前页,limit 每页记录数,teacherQuery 条件对象
  // pageTeacherCondition/{current}/{limit}
  getTeacherListPage(current, limit, teacherQuery) {
    return request({
      // url: '/eduservice/teacher/pageTeacherCondition/'+current+"/"+limit,
      url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}`,
      method: 'post',
      //RequestBody获取数据 data将对象转换为json传递到接口中
      data: teacherQuery
    })
  },

  //删除讲师
  deleteTeacherId(id) {
    return request({
      url: `/eduservice/teacher/${id}`,
      method: 'delete'
    })
  },

  //添加讲师
  addTeacher(teacher) {
    return request({
      url: `/eduservice/teacher/addTeacher`,
      method: 'post',
      //RequestBody获取数据 data将对象转换为json传递到接口中
      data: teacher
    })
  },

  //更新讲师信息
  updateTeacherInfo(teacher) {
    return request({
      url: `/eduservice/teacher/updateTeacher`,
      method: 'post',
      //RequestBody获取数据 data将对象转换为json传递到接口中
      data: teacher
    })
  },

  //根据id查询讲师
  getTeacherInfo(id) {
    return request({
      url: `/eduservice/teacher/getTeacher/${id}`,
      method: 'get'
    })
  },

}

