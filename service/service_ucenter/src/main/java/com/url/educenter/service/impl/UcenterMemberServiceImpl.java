package com.url.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.url.commonutils.JwtUtils;
import com.url.commonutils.MD5;
import com.url.educenter.entity.UcenterMember;
import com.url.educenter.entity.vo.MemberVo;
import com.url.educenter.entity.vo.RegisterVo;
import com.url.educenter.mapper.UcenterMemberMapper;
import com.url.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.url.servicebase.exceptionhandle.GuliException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-05
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String ,String > redisTemplate;
    @Override
    public String login(MemberVo memberVo) {
        String mobile = memberVo.getMobile();
        String password = memberVo.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new GuliException(20001,"请检查账号是否完善信息");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        if (mobileMember == null){
            throw new GuliException(20001,"手机号所属账号未注册");
        }
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw new GuliException(20001,"密码错误");
        }
        if (mobileMember.getIsDisabled()){
            throw new GuliException(20001,"用户状态异常，暂时无法登录");
        }

        String token = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)){
            throw new GuliException(20001,"注册信息填写不完整");
        }
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if ((!code.equals(redisCode))){
            throw new GuliException(20001,"验证码错误");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0){
            throw new GuliException(20001,"改手机号已经注册过了，请前往登录");
        }
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("https://guli-edu-dz.oss-cn-shenzhen.aliyuncs.com/2022/09/21/cb2cadc145b34baba6a33eeb55981adcfile.png");
        int insert = baseMapper.insert(member);
        if (insert == 0){
            throw new GuliException(20001,"注册失败，请重试");
        }
    }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember member = baseMapper.selectOne(wrapper);
        return member;
    }

    @Override
    public Integer countRegister(String day) {
        return baseMapper.countRegister(day);
    }
}
