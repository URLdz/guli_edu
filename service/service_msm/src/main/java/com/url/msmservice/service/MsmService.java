package com.url.msmservice.service;

import java.util.Map;

/**
 * @author xidazhen
 * @date 2022/10/4 - 20:47
 */
public interface MsmService {
    Boolean sendMsm(String phone, Map<String,Object> param);
}
