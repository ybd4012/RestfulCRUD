package com.review.rustfulcrud.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.*;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource(){
        return  new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean statViewServlet(){
        /**
         * ServletRegistrationBean registrationBean = new ServletRegistrationBean( new StatViewServlet(),"/druid/*");
         *                      这一行代码等同于下面的4行
         *         registrationBean.setServlet(new StatViewServlet());
         *
         *         Set<String> urlMappings = new LinkedHashSet<>();
         *         urlMappings.add("/druid/**");
         *         registrationBean.setUrlMappings(urlMappings);
         */
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new StatViewServlet(),"/druid/*");
//        registrationBean.setServlet(new StatViewServlet());
//        Set<String> urlMappings = new LinkedHashSet<>();
//        urlMappings.add("/druid/**");
//        registrationBean.setUrlMappings(urlMappings);
        Map<String, String> map = new HashMap<>();
        map.put("loginUsername","admin");
        map.put("loginPassword","a1b2c3");
        map.put("allow","");//默认允许所有 ip
//        map.put("deny","92.168.13.120");

       registrationBean.setInitParameters(map);
       return registrationBean;
    }
    /**
     * 注册一个filter
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new WebStatFilter());
        Map<String, String> map = new HashMap<>();
        registrationBean.setInitParameters(map);
        map.put("exclusions","/*.js,*.css,/druid/*");
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
            return  registrationBean;
    }
}
