package com.swagger.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;


/***
 * When I wrote this, only God and I understood what I was doing.
 * Now, God only knows
 * @author ChangZehua
 * @date 2019/11/28
 */
@Configuration
@EnableSwagger2 //开启Swagger
public class SwaggerConfig {
	private String value ="";
	/**
	 * SpringBoot默认已经将classpath:/META-INF/resources/和classpath:/META-INF/resources/webjars/映射
	 * 所以该方法不需要重写，如果在SpringMVC中，可能需要重写定义（我没有尝试）
	 * 重写该方法需要 extends WebMvcConfigurerAdapter
	 *
	 */
	//    @Override
	//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	//        registry.addResourceHandler("swagger-ui.html")
	//                .addResourceLocations("classpath:/META-INF/resources/");
	//
	//        registry.addResourceHandler("/webjars/**")
	//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
	//    }

	//配置了Swagger的Docket的Bean实例；
	@Bean
	public Docket docket(Environment env){


		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				//Swagger开关
				//.enable(false)
				.select()

				//RequestHandlerSelectors 配置要扫描接口的方式
				//basePackage(); 指定扫描的包
				//any(); 全部扫描
				//withClassAnnotation(); 扫描类上的注解,参数是一个类的反射对象
				//withMethodAnnotation(); 扫描类上的注解
				//.basePackage("com.swagger.demo.controller")
				.apis(RequestHandlerSelectors.withMethodAnnotation(PostMapping.class)
						)
				//过滤路径
				//.paths(PathSelectors.ant("/demo/**"))
				.build();
	}

	//配置Swagger信息=ApiInfo ##只有构造函数没有set方法
	private ApiInfo apiInfo(){
		//作者信息 user info
		Contact contact = new Contact("ChangZehua","https://cn.bing.com","spromise@yeah.net");

		return new ApiInfo("Eric's Api Documentation",
				"Eric's Api Documentation",
				"1.0",
				"https://cn.bing.com",
				contact,
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList());
	}

	/**
	 * 可以定义多个组，比如本类中定义把test和demo区分开了
	 * （访问页面就可以看到效果了）
	 *
	 */
	@Bean
	public Docket testApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("test")
				.genericModelSubstitutes(DeferredResult.class)
	//                .genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")// base，最终调用接口后会和paths拼接在一起
				.select()
				.paths(or(regex("/api/.*")))//过滤的接口
				.build()
				.apiInfo(apiInfo());
//				.useDefaultResponseMessages(false)
//				.globalResponseMessage(RequestMethod.GET, new ArrayList(new ResponseMessageBuilder().code(500).message("服务器发生异常").responseModel(new ModelRef("Error")).build(), new ResponseMessageBuilder().code(403).message("资源不可用").build()));
	}

	@Bean
	public Docket demoApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("demo")
				.genericModelSubstitutes(DeferredResult.class)
	//              .genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(false)
				.pathMapping("/")
				.select()
				.paths(or(regex("/demo/.*")))//过滤的接口
				.build()
				.apiInfo(apiInfo());
	}



}
