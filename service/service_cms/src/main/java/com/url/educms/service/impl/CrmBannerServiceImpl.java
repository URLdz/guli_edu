package com.url.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.url.educms.entity.CrmBanner;
import com.url.educms.mapper.CrmBannerMapper;
import com.url.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-03
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public Map pageBanner(long current, long limit) {
        Page<CrmBanner> pageBanner = new Page<>(current,limit);
        baseMapper.selectPage(pageBanner,null);
        List<CrmBanner> records = pageBanner.getRecords();
        long total = pageBanner.getTotal();
        Map map = new HashMap();
        map.put("records",records);
        map.put("total",total);
        return map;
    }

    @Override
    public List<CrmBanner> getAllBanner() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //last方法拼接 sql语句
        wrapper.last("limit 2");
        List<CrmBanner> list = baseMapper.selectList(wrapper);
        return list;
    }
}
