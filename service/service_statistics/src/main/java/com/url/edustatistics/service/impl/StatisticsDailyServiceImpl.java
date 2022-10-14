package com.url.edustatistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.url.commonutils.CommonResult;
import com.url.edustatistics.client.UcenterClient;
import com.url.edustatistics.entity.StatisticsDaily;
import com.url.edustatistics.mapper.StatisticsDailyMapper;
import com.url.edustatistics.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.url.servicebase.exceptionhandle.GuliException;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-10
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void registerCount(String day) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        Integer count = baseMapper.selectCount(wrapper);
        if (count != 0 ){
            int delete = baseMapper.delete(wrapper);
            if (delete == 0 ){
                throw new GuliException(20001,"删除旧纪录失败");
            }
        }
        CommonResult commonResult = ucenterClient.countRegister(day);
        Integer countRegister = (Integer) commonResult.getData().get("countRegister");
        StatisticsDaily sta = new StatisticsDaily();

        sta.setRegisterNum(countRegister);
        sta.setDateCalculated(day);
        sta.setVideoViewNum(RandomUtils.nextInt(100,200));
        sta.setLoginNum(RandomUtils.nextInt(100,200));
        sta.setCourseNum(RandomUtils.nextInt(100,200));
        baseMapper.insert(sta);
    }

    @Override
    public Map<String, Object> showData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        //动态选择查询的列
        wrapper.select("date_calculated",type);
        List<StatisticsDaily> staList = baseMapper.selectList(wrapper);
        //list集合实现前端接收 json
        List<String> date_calculatedList = new ArrayList<>();
        List<Integer> numDataList = new ArrayList<>();
        switch (type){
            case "login_num":
                for (int i = 0; i < staList.size(); i++) {
                    StatisticsDaily daily = staList.get(i);
                    date_calculatedList.add(daily.getDateCalculated());
                    numDataList.add(daily.getLoginNum());
                }
                break;
            case "register_num":
                for (int i = 0; i < staList.size(); i++) {
                    StatisticsDaily daily = staList.get(i);
                    date_calculatedList.add(daily.getDateCalculated());
                    numDataList.add(daily.getRegisterNum());
                }
                break;
            case "video_view_num":
                for (int i = 0; i < staList.size(); i++) {
                    StatisticsDaily daily = staList.get(i);
                    date_calculatedList.add(daily.getDateCalculated());
                    numDataList.add(daily.getVideoViewNum());
                }
                break;
            default:
                for (int i = 0; i < staList.size(); i++) {
                    StatisticsDaily daily = staList.get(i);
                    date_calculatedList.add(daily.getDateCalculated());
                    numDataList.add(daily.getCourseNum());
                }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("date_calculatedList",date_calculatedList);
        map.put("numDataList",numDataList);
        return map;
    }
}
