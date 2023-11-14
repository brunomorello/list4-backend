//package pt.bmo.list4u.api.shoppinglist.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//
//@EnableWebSecurity
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    @Order(2)
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .cors(Customizer.withDefaults())
//                .authorizeHttpRequests(requests -> requests.anyRequest().authenticated());
//        return httpSecurity.build();
//    }
//
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("http://localhost:[*]", "https://*.herokuapp.com:[*]"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Requestor-Type"));
//        corsConfiguration.setExposedHeaders(Arrays.asList("X-Get-Header"));
//        corsConfiguration.setMaxAge(3600L);
//
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return urlBasedCorsConfigurationSource;
//    }
//}
