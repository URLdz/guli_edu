package com.url.eduorder.service.impl;

import com.url.commonutils.ordervo.CourseWebVoOrder;
import com.url.commonutils.ordervo.UcenterMemberOrder;
import com.url.eduorder.client.EduClient;
import com.url.eduorder.client.UcenterClient;
import com.url.eduorder.entity.Order;
import com.url.eduorder.mapper.OrderMapper;
import com.url.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.url.eduorder.utils.OrderNoUtil;
import com.url.servicebase.exceptionhandle.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        UcenterMemberOrder userInfoOrder = ucenterClient.getMemberInfoOrder(memberId);
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseCover(courseInfoOrder.getCover());
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setPayType(1);
        order.setStatus(0);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
