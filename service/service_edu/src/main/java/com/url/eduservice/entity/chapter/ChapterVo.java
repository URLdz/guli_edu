package com.url.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xidazhen
 * @date 2022/9/24 - 16:49
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    private List<VedioVo> children = new ArrayList<>();
}
