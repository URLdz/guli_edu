package com.url.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xidazhen
 * @date 2022/9/23 - 13:42
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    //一个一级带多个二级
    private List<TwoSubject> children = new ArrayList<>();
}
