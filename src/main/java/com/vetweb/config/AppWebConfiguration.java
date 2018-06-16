package com.vetweb.config;
// @author 11151504898

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.vetweb.controller", "com.vetweb.dao", "com.vetweb.scheduled",
    "com.vetweb.model", "com.vetweb.dao.auth", "com.vetweb.model.auth", "com.vetweb.controller.advice",
    "com.vetweb.model.error", "com.vetweb.model.pojo", "com.vetweb.service", "com.vetweb.controller.rest"})
public class AppWebConfiguration extends WebMvcConfigurerAdapter implements WebApplicationInitializer {
	
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
        bundle.setBasename("/WEB-INF/messages");
        bundle.setDefaultEncoding("UTF-8");
        bundle.setCacheSeconds(1);
        return bundle;
    }
    
    @Bean
    public FormattingConversionService mvcConversionService(){
        FormattingConversionService formattingConversionService = new DefaultFormattingConversionService(true);
        DateTimeFormatterRegistrar formatter = new DateTimeFormatterRegistrar();
        formatter.setDateFormatter((DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        formatter.registerFormatters(formattingConversionService);
        return formattingConversionService;
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/forbidden").setViewName("/login/403");
        registry.addViewController("/table").setViewName("/proprietario/table");
        registry.addViewController("/").setStatusCode(HttpStatus.NOT_FOUND).setViewName("/exception/404");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }
    
    @Bean
    public LocaleResolver localeResolver(){
        return new CookieLocaleResolver();
    }
    
    @Bean
    public MultipartResolver multipartResolver() {
    	return new StandardServletMultipartResolver();
    }
    
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("razaoSocial", "vetwork");		
		servletContext.setInitParameter("fundadaEm", LocalDate.now()
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));	
		servletContext.setInitParameter("cnpj", "11.545.952/0001-07");
		servletContext.setInitParameter("proprietario", "proprietario");
	}
	
}
