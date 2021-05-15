package com.squirrel.druidmultijpa.config.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean =
                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        servletRegistrationBean.addInitParameter("enabled", "true");
        return servletRegistrationBean;

    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> druidStatFilter() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean =
                new FilterRegistrationBean<>(new WebStatFilter());
        filterRegistrationBean.setName("DruidWebStatFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("enabled", "true");
        filterRegistrationBean.addInitParameter(
                "exclusions", "*.js, *.gif, *.jpg, *.png, *.css, *.icp, /druid/*");
        return filterRegistrationBean;
    }
}
