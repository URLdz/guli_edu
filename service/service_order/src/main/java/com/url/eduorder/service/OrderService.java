package com.url.eduorder.service;

import com.url.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-10
 */
public interface OrderService extends IService<Order> {

    String createOrder(String courseId, String memberId);
}
