package com.url.educenter.service;

import com.url.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.url.educenter.entity.vo.MemberVo;
import com.url.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-05
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(MemberVo memberVo);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegister(String day);
}
