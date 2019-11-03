package com.supertrampai.myutils.configutils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @author: LiXiangHong
 * @Email: lxh800109@gmail.com
 * @Description:
 * @Date: Created in 15:28 2019/9/27
 * @Modified By:
 */
@Configuration
public class FileSizeConfig {

    @Value("${multipart.maxFileSize}")
    private String MaxFileSize;

    @Value("${multipart.MaxRequestSize}")
    private String MaxRequestSize;

    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件最大
        factory.setMaxFileSize(MaxFileSize); //KB,MB
        // 设置总上传数据总大小
        factory.setMaxRequestSize(MaxRequestSize);
        return factory.createMultipartConfig();
    }

}
