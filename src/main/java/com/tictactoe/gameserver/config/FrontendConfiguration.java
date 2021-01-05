package com.tictactoe.gameserver.config;

import com.tictactoe.gameserver.model.CustomPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * FrontendConfiguration class for Web MVC server related configurations.
 * Note: all new react paths from the game-app need to added to forward rule similar to
 * <pre>
 * 	registry.addViewController("/").setViewName("forward:/index.html");
 * </pre>
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
public class FrontendConfiguration implements WebMvcConfigurer {

    /**
     * Ensure client-side paths redirect to index.html because client handles routing. NOTE: Do NOT use @EnableWebMvc or it will break this.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/signin").setViewName("forward:/index.html");
        registry.addViewController("/game").setViewName("forward:/index.html");
        registry.addViewController("/game/live").setViewName("forward:/index.html");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserHandlerMethodArgumentResolver());
    }

    @Bean
    public HandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver() {
        return new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return parameter.getParameterType().equals(CustomPrincipal.class);
            }

            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                          NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
                try {
                    return (CustomPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                } catch (Exception e) {
                    return null;
                }
            }
        };
    }
}