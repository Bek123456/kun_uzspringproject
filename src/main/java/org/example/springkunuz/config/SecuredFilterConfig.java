/*
package org.example.springkunuz.config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecuredFilterConfig {
    @Autowired
    private TokenFilter tokenFilter;
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(tokenFilter);
        bean.addUrlPatterns("/region/adm");
        bean.addUrlPatterns("/region/adm/*");

        bean.addUrlPatterns("/articletype/*");
        bean.addUrlPatterns("/articletype/adm/*");

        bean.addUrlPatterns("/category/adm");
        bean.addUrlPatterns("/category/adm/*");

        bean.addUrlPatterns("/profile/adm");
        bean.addUrlPatterns("/profile/adm/*");

        bean.addUrlPatterns("/article/adm");
        bean.addUrlPatterns("/article/adm/*");

        bean.addUrlPatterns("/api/auth/verification/email/jwt");

        bean.addUrlPatterns("/articlelike/*");
        bean.addUrlPatterns("/articlelike/created/*");

        bean.addUrlPatterns("/comment");
        bean.addUrlPatterns("/comment/*");

        bean.addUrlPatterns("/commentlike");
        bean.addUrlPatterns("/commentlike/*");

        return bean;
    }

}
*/
