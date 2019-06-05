package me.mason.springbatch.config;

import cn.hutool.core.util.BooleanUtil;
import me.mason.springbatch.interceptor.RequestIdTraceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description  web配置
 * @Author Mason
 * @Since 2019/6/1
 **/
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Autowired
    RequestIdTraceInterceptor requestIdTraceInterceptor;
    @Value("${swagger.enable}")
    private Boolean swaggerEnable;

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加requestId
        registry.addInterceptor(requestIdTraceInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/v2/api-docs").excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/swagger-ui.html").excludePathPatterns("/doc.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger
        if(BooleanUtil.isTrue(swaggerEnable)){
            registry.addResourceHandler("doc.html")
                    .addResourceLocations("classpath:/META-INF/resources/");

            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");

            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

}
