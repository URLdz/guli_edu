package com.url.security.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户实体类
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-12
 */
@Data
@ApiModel(description = "用户实体类")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "微信openid")
	private String username;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "昵称")
	private String nickName;

	@ApiModelProperty(value = "用户头像")
	private String salt;

	@ApiModelProperty(value = "用户签名")
	private String token;

}



