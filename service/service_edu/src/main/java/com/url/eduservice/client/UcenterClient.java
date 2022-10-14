package com.url.eduservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @author xidazhen
 * @date 2022/10/9 - 14:15
 */
@Component
@FeignClient(name = "service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {
    @PostMapping("/educenter/member/getMemberInfoComment/{memberId}")
    public Map getMemberInfoComment(@PathVariable("memberId") String memberId);

}
