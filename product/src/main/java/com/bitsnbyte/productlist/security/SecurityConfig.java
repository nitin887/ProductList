package com.bitsnbyte.productlist.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;
    // @Autowired
    // private UserDetailsService userDetailsService;
    // @Autowired
    // private JwtRequestFilter requestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(HttpMethod.POST, "/user/register", "/user/login").permitAll();

                    request.requestMatchers(HttpMethod.GET, "/**").permitAll();
                    request.anyRequest().authenticated();
                }).oauth2ResourceServer(
                        oauth2 -> oauth2
                                .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(jwtAuthenticatorConverter())));

        /*
         * authenticationProvider(authenticationProvider())
         * .sessionManagement(session ->
         * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         * .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
         */
        // .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticatorConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakRoleReader());
        return jwtAuthenticationConverter;
    }

    @Bean
    @ConditionalOnMissingBean(JwtDecoder.class)
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    // return new BCryptPasswordEncoder();
    // }

    /*
     * @Bean
     * public UserDetailsService userDetailsService() {
     * UserDetails admin = User.withUsername("admin")
     * .password(passwordEncoder().encode("admin"))
     * .roles("ADMIN").build();
     * UserDetails seller = User.withUsername("seller")
     * .password(passwordEncoder().encode("seller"))
     * .roles("SELLER").build();
     * return new InMemoryUserDetailsManager(admin, seller);
     */

    /*
     * @Bean
     * public AuthenticationProvider authenticationProvider() {
     * 
     * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
     * provider.setPasswordEncoder(new BCryptPasswordEncoder());
     * provider.setUserDetailsService(userDetailsService);
     * return provider;
     * 
     * }
     * 
     * @Bean
     * public AuthenticationManager
     * authenticationManager(AuthenticationConfiguration config) throws Exception {
     * return config.getAuthenticationManager();
     * 
     * }
     */
}
