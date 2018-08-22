package com.megagao.production.ssm.swaggerConfig; 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     *  注意：.apis("此处指定哪些api生成"),只生成api部分的接口不生成后台管理系统的接口，如果要生成所有接口写RequestHandlerSelectors.any()，
     *  当前只生成前端api调用的那一部分接口就可以了
     * @return
     */
    
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.megagao.production.ssm.controller.ap"))//包名
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("盛大接口API 文档")
				.description("HTTP对外开放接口,只显示api接口，管理平台不显示")
				.version("1.0.0")
				.termsOfServiceUrl("http://xxx.xxx.com")
				.license("LICENSE")
				.licenseUrl("http://xxx.xxx.com")
				.build();
	}

}