package me.study.jpatodo.auth.config;

import lombok.RequiredArgsConstructor;
import me.study.jpatodo.auth.application.AccountService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final TokenStore tokenStore;
    private final AppProperties appProperties;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()")
                .passwordEncoder(passwordEncoder); // Secret 설정시 인코딩 해서 관리.
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // jdbc를 사용해 db에서 관리하는게 이상적이다.
                .withClient(appProperties.getClientId()) // Client Id
                .authorizedGrantTypes("password", "refresh_token", "authorization_code")//refresh_token을 받기위해
                .scopes("read", "write")
                .redirectUris(appProperties.getRedirectUri())
                .secret(passwordEncoder.encode(appProperties.getClientSecret())) // app secret
                .accessTokenValiditySeconds(10 * 60 * 6)
                .refreshTokenValiditySeconds(6 * 10 * 60 * 24);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .userDetailsService(accountService);
    }
}
