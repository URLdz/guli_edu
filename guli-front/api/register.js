import request from '@/utils/request'

export default{
    //验证码
    sendCode(phone){
        return request({
            url: `/edumsm/msm/send/${phone}`,
            method:'get'
        })
    },
    //注册方法 
    registerMember(formItem){
        return request({
            url: `/educenter/member/register`,
            method:'post',
            data:formItem
        })
    },
}