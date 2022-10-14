package com.url.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xidazhen
 * @date 2022/9/27 - 19:23
 */
@Data
public class CourseQueryVo {

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "课程标题")
    private String title;
}
