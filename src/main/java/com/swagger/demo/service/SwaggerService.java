package com.swagger.demo.service;

import com.swagger.demo.model.SwaggerResponse;
import org.springframework.stereotype.Service;

/***
 * When I wrote this, only God and I understood what I was doing.
 * Now, God only knows
 * @author ChangZehua
 * @date 2019/12/3
 */
@Service("swaggerService")
public class SwaggerService {

	public SwaggerResponse getSwaggerResponse(){

		SwaggerResponse swaggerResponse =new SwaggerResponse();
//		swaggerResponse
		return swaggerResponse;
	}
}
