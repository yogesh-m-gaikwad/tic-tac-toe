package com.tictactoe.gameserver.config;

import com.tictactoe.gameserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * AuthServerConfiguration class for JWT auth server related configurations.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final DataSource dataSource;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    private final ClientDetailsService clientDetailsService;

    private final AuthenticationManager authenticationManager;

    @Value("${check-user-scopes}")
    private Boolean checkUserScopes;

    @Autowired
    public AuthServerConfiguration(@Qualifier("dataSource") DataSource dataSource, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, ClientDetailsService clientDetailsService, @Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.clientDetailsService = clientDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Bean
    public OAuth2RequestFactory requestFactory() {
        CustomOauth2RequestFactory requestFactory = new CustomOauth2RequestFactory(clientDetailsService);
        requestFactory.setCheckUserScopes(true);
        return requestFactory;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    @Bean
    public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter() {
        return new TokenEndpointAuthenticationFilter(authenticationManager, requestFactory());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager).userDetailsService(userDetailsService);
        if (checkUserScopes)
            endpoints.requestFactory(requestFactory());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new CustomTokenEnhancer();
        converter.setKeyPair(
                new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "root@break".toCharArray()).getKeyPair("jwt"));
        return converter;
    }

    /*
     * Add custom user principal information to the JWT token
     */
    class CustomTokenEnhancer extends JwtAccessTokenConverter {
        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
            User user = (User) authentication.getPrincipal();

            Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());

            info.put("user_id", user.getUserId());
            info.put("email", user.getEmail());

            DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
            Date exp = new Date(System.currentTimeMillis() + (1000 * 60 * 30)); // 30 minutes
            customAccessToken.setExpiration(exp);
            customAccessToken.setAdditionalInformation(info);

            return super.enhance(customAccessToken, authentication);
        }
    }

    class CustomOauth2RequestFactory extends DefaultOAuth2RequestFactory {
        @Autowired
        private TokenStore tokenStore;

        public CustomOauth2RequestFactory(ClientDetailsService clientDetailsService) {
            super(clientDetailsService);
        }

        @Override
        public TokenRequest createTokenRequest(Map<String, String> requestParameters,
                                               ClientDetails authenticatedClient) {
            if (requestParameters.get("grant_type").equals("refresh_token")) {
                OAuth2Authentication authentication = tokenStore.readAuthenticationForRefreshToken(
                        tokenStore.readRefreshToken(requestParameters.get("refresh_token")));
                SecurityContextHolder.getContext()
                        .setAuthentication(new UsernamePasswordAuthenticationToken(authentication.getName(), null,
                                userDetailsService.loadUserByUsername(authentication.getName()).getAuthorities()));
            }
            return super.createTokenRequest(requestParameters, authenticatedClient);
        }
    }

}