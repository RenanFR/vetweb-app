package com.vetweb.config;
 // @author 11151504898
import com.vetweb.config.security.SecurityConfig;
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SecurityConfig.class, AppWebConfiguration.class, ConfigJPA.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        OpenEntityManagerInViewFilter managerInViewFilter = new OpenEntityManagerInViewFilter();
        return new Filter[]
        {
            encodingFilter, managerInViewFilter
        };
    }
    
    @Override
    protected void customizeRegistration(Dynamic registration) {
    	registration.setMultipartConfig(new MultipartConfigElement(""));
    }
    
}
