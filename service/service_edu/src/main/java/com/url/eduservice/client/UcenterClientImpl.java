package com.url.eduservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @author xidazhen
 * @date 2022/10/9 - 14:16
 */
@Component
public class UcenterClientImpl implements UcenterClient{
    @Override
    public Map getMemberInfoComment(@PathVariable String memberId) {
        return null;
    }
}
