package com.swagger.demo.model;

import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * When I wrote this, only God and I understood what I was doing.
 * Now, God only knows
 * @author ChangZehua
 * @date 2019/11/28
 */
@Data
@ApiModel(value = "Swagger返回对象模型")
public class SwaggerResponse{
	@ApiModelProperty(value = "用户名", required = true)
	private String user;
	@ApiModelProperty(value = "密码", required = true)
	private String password;
	@ApiModelProperty(value="计数",required =true)
	private int count;

}
