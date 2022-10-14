package com.url.servicebase.exceptionhandle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xidazhen
 * @date 2022/9/18 - 12:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {
    private Integer code;
    private String msg;
}
