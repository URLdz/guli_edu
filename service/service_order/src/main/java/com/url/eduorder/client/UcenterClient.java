package com.url.eduorder.client;

import com.url.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xidazhen
 * @date 2022/10/10 - 11:49
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @PostMapping("/educenter/member/getMemberInfoOrder/{memberId}")
    public UcenterMemberOrder getMemberInfoOrder(@PathVariable("memberId") String memberId);
}
