package com.url.educms.service;

import com.url.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-03
 */
public interface CrmBannerService extends IService<CrmBanner> {

    Map pageBanner(long current, long limit);

    List<CrmBanner> getAllBanner();

}
