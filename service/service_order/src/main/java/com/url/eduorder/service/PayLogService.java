package com.url.eduorder.service;

import com.url.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-10
 */
public interface PayLogService extends IService<PayLog> {

    Map createNative(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrdersStatus(Map<String, String> map);
}
