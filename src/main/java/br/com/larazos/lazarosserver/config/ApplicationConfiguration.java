package br.com.larazos.lazarosserver.config;

import br.com.larazos.lazarosserver.controller.request.UserRequest;
import br.com.larazos.lazarosserver.model.User;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ApplicationConfiguration {

  @Value("${url.cors.allowed.origins}")
  private String[] allowedOrigins;
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry
          .addMapping("/**")
          .allowedOrigins(allowedOrigins)
          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
          .allowedHeaders("*")
          .maxAge(3600);
      }
    };
  }
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setSkipNullEnabled(true);
    PropertyMap<UserRequest, User> userMap = new PropertyMap<UserRequest, User>() {
      @Override
      protected void configure() {
        skip(destination.getProfiles());
      }
    };
    modelMapper.addMappings(userMap);
    return modelMapper;
  }
}
