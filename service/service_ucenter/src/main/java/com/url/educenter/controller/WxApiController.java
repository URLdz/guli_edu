package com.url.educenter.controller;

import com.google.gson.Gson;
import com.url.commonutils.JwtUtils;
import com.url.educenter.entity.UcenterMember;
import com.url.educenter.service.UcenterMemberService;
import com.url.educenter.utils.ConstantWxUtils;
import com.url.educenter.utils.HttpClientUtils;
import com.url.servicebase.exceptionhandle.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author xidazhen
 * @date 2022/10/7 - 10:05
 */
@Api(description = "前台微信登录")
@Controller

@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation("微信扫码登录")
    @GetMapping("login")
    public String getWxCode() {
        // 微信开放平台授权baseUrl  %s相当于?代表占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        //对redirect_url进行URLEncoder编码
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (Exception e) {
        }

        //设置%s里面值
        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "atguigu"
        );
        //重定向到请求微信地址里面
        return "redirect:" + url;
    }

    @ApiOperation("微信扫码成功跳转")
    @GetMapping("callback")
    public String callback(String code, String state) {
        System.out.println(code);
        System.out.println(state);

        try {
            //1 获取code值，临时票据，类似于验证码
            //2 拿着code请求 微信固定的地址，得到两个值 accsess_token 和 openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数 ：id  秘钥 和 code值
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code
            );
            //请求这个拼接好的地址，得到返回两个值 accsess_token 和 openid
            //使用httpclient发送请求，得到返回结果
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            System.out.println("accessTokenInfo:" + accessTokenInfo);
            /**
             * accessTokenInfo:{
             * "access_token":"61_9f_SxPpQxjcGz7EN4Saz5kNGQgRnYY4sxiS8hnWZn2SQIG9eGZQIDzJ0xv2P4koxrrQoPLccLlRheY3LznukoJ7sWEbMPPodGc5o6w4GymE",
             * "expires_in":7200,
             * "refresh_token":"61_ruMGLwWM-2XEJjjSIUFj--xnBuQ8GFzVz1Gul5Q49-uhFx4UbYxdiBa2BAkEjNDbjRbumHjpRBkHPPLHNYYxmA4ZfMwXISy74oo5Ajmzd2I",
             * "openid":"o3_SC5xjPX-q95Z-cZ8gGNunIi_U",
             * "scope":"snsapi_login",
             * "unionid":"oWgGz1I2yxdbK3DKxt06pJPW3Tzo"
             * }
             */

            //从accessTokenInfo字符串获取出来两个值 accsess_token 和 openid
            //把accessTokenInfo字符串转换map集合，根据map里面key获取对应值
            //使用json转换工具 Gson
            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
            String access_token = (String) mapAccessToken.get("access_token");
            String openid = (String) mapAccessToken.get("openid");

            UcenterMember member = ucenterMemberService.getOpenIdMember(openid);
            if (member == null) {
                //3 拿着得到accsess_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid
                );
                //发送请求
                String userInfo = HttpClientUtils.get(userInfoUrl);
                System.out.println("userInfo:" + userInfo);
                //获取返回userinfo字符串扫描人信息
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String) userInfoMap.get("nickname");//昵称
                String headimgurl = (String) userInfoMap.get("headimgurl");//头像

                member = new UcenterMember();
                member.setOpenid(openid);
                member.setAvatar(headimgurl);
                member.setNickname(nickname);
                ucenterMemberService.save(member);
            }

            String token = JwtUtils.getJwtToken(member.getId(),member.getNickname());

            return "redirect:http://localhost:3000?token=" + token;
        } catch (Exception exception) {
            throw new GuliException(20001, "登陆失败");
        }


    }
}
