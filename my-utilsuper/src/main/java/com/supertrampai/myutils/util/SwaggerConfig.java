package com.supertrampai.myutils.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Profile({"dev","test"})
public class SwaggerConfig {

    @Value("${}")
	private String token;
    @Bean
    public Docket api() {
    	//添加head参数start  
        ParameterBuilder tokenPar = new ParameterBuilder();  
        ParameterBuilder museumId = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("token").description("令牌").defaultValue(token).modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        museumId.name("id").description("id").defaultValue("1").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
//        pars.add(museumId.build());
        //添加head参数end  
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("package"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
//                .tags(new Tag("tag", "商品"))
                .tags(new Tag("tag", "销售"));
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact("lxh","http","lxh800109@gmail.com");
        return new ApiInfoBuilder()
                .title("API接口文档")
                .version("1.0")
                .contact(contact)
                .build();
    }
}