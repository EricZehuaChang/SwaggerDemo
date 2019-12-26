package com.swagger.demo.controller;


import com.swagger.demo.model.SwaggerResponse;
import com.swagger.demo.service.SwaggerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * When I wrote this, only God and I understood what I was doing.
 * Now, God only knows
 * @author ChangZehua
 * @date 2019/11/28
 */

@RestController
@ResponseBody
@Api(tags = "操作用户的api", description = "提供用户相关的 Rest API")
public class SwaggerController {

	// 注入Service
	@Resource(name = "swaggerService")
	private SwaggerService swaggerService;

	@RequestMapping(value = "/path")
	public String hello(){
		return "hello";
	}

	@GetMapping(value="get")
	public String getMasseage(){
		return "Information";
	}

	@ApiOperation("Nomal API")
	@PostMapping(value = "accounts")
	public SwaggerResponse create(@RequestBody SwaggerResponse account) {

		return swaggerService.getSwaggerResponse();
	}
}
