import request from '@/utils/request'

export default{
    //查询轮播图列表
    getBannerList(){
        return request({
            url:'/educms/bannerfront/getAllBanner',
            method:'get'
        })
    },
}