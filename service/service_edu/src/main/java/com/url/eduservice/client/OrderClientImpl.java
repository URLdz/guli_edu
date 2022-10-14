package com.url.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * @author xidazhen
 * @date 2022/10/10 - 19:55
 */
@Component
public class OrderClientImpl implements OrderClient{
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
