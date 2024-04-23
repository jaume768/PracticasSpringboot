package com.example.login_spring.config;

import com.example.login_spring.Interceptor.RequestLoggingInterceptor;
import com.example.login_spring.Interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${project.client.origins}")
    private String cors;
    private final TokenInterceptor tokenInterceptor;
    private final RequestLoggingInterceptor requestLoggingInterceptor;

    @Autowired
    public WebConfiguration(TokenInterceptor tokenInterceptor, RequestLoggingInterceptor requestLoggingInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
        this.requestLoggingInterceptor = requestLoggingInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins(cors.split(","))
                .allowedMethods("PUT", "POST", "GET", "DELETE", "OPTIONS");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Registro del TokenInterceptor para una ruta específica
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/usuariosconroles")
                .addPathPatterns("/roles");

        // Registro del RequestLoggingInterceptor para todas las rutas
        registry.addInterceptor(requestLoggingInterceptor);
    }
}
