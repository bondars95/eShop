package com.eshop.trash;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory;
//import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
//import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
//
//@Configuration
//@ComponentScan(basePackages = "com.eshop.web")
//@EnableWebMvc
//public class ApplicationContextConfig extends WebMvcConfigurerAdapter{
//
//    @Bean(name = "viewResolver")
//    TilesViewResolver viewResolver(){
//        TilesViewResolver viewResolver = new TilesViewResolver();
//        return viewResolver;
//    }
//
//    @Bean(name = "tilesConfigurer")
//    TilesConfigurer tilesConfigurer(){
//        TilesConfigurer tilesConfigurer = new TilesConfigurer();
//        tilesConfigurer.setDefinitions("/WEB-INF/views/**/views.xml");
////        tilesConfigurer.setPreparerFactoryClass(SpringBeanPreparerFactory.class);
//        return tilesConfigurer;    
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//    }
//    
//}
