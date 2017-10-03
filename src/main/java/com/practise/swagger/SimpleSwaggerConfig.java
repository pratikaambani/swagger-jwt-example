package com.practise.swagger;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.practise.swagger.model.Constants.*;

/**
 * Created by Pratik Ambani on 3/5/2017.
 */
@Configuration
@EnableSwagger
public class SimpleSwaggerConfig {

    @Bean
    @Autowired
    public SwaggerSpringMvcPlugin customImplementation(SpringSwaggerConfig springSwaggerConfig) {
        return new SwaggerSpringMvcPlugin(springSwaggerConfig)
                .apiInfo(new ApiInfo(
                        TITLE,
                        DESCRIPTION,
                        TERMS_OF_SERVICE,
                        CONTACT,
                        LICENSE,
                        LICENSE_URL
                )).includePatterns(PATTERNS);
    }
}
