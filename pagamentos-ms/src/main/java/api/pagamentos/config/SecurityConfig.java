package api.pagamentos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Value("${frontend.port}")
    private String port;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(a -> a.requestMatchers("/token", "/refresh-token").permitAll()
//                        .requestMatchers("/api/pedidos").hasRole("USER")
//                        .requestMatchers("/api/pedidos/status").hasRole("ADMIN")
//                        .anyRequest().authenticated())
//                .oauth2ResourceServer(
//                        oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(new JWTConverter()))).build();
//    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web -> web.ignoring().requestMatchers(
//                "/v3/api-docs/**",
//                "/swagger-ui/**"));
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(port)
                .allowedMethods("GET", "POST", "DELETE", "PUT");
    }
}
