package com.yugioh.api.webapi.config;

import com.yugioh.api.webapi.interceptor.IpInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author Create By lieber
 * @Description
 * @Date Create in 2018/5/17 11:18
 * @Modify By
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurationSupport {

    /**
     * 把我们的拦截器注入为bean
     *
     * @return 拦截器
     */
    @Bean
    public HandlerInterceptor getMyInterceptor() {
        return new IpInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
