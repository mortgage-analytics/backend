package Group28.Backend.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private JwtUtil jwtUtil;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .authorizeRequests()
            .requestMatchers(HttpMethod.POST, "/api/auth/signin").permitAll()
            .requestMatchers(HttpMethod.POST, "api/auth/signup").permitAll()
            .requestMatchers(HttpMethod.GET, "api/auth/signout").permitAll()
            .requestMatchers(HttpMethod.GET,  "api/data/graphs/byMonth").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(new JwtRequestFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://mortgage-analytics.vercel.app", "https://mortgage-analytics-test.vercel.app"));
    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
    config.setAllowCredentials(true);
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}
