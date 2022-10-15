import request from '@/utils/request'

export default {
  
  createStaData(day) {
    return request({
      url: `/edustatistics/statistics/registerCount/${day}`,
      method: 'get'
    })
  },
  getDataSta(searchObj) {
    return request({
      url: `/edustatistics/statistics/showData/${searchObj.type}/${searchObj.begin}/${searchObj.end}`,
      method: 'get'
    })
  },
  

}

