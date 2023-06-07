package com.example.demosecurity.Config;

import com.example.demosecurity.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfiguration {

    private final MyUserDetailsService myUserDetailsService;

    // إنشاء Bean لتوفير DaoAuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    // إنشاء سلسلة من العمليات الأمنية لتأمين المسارات المختلفة
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeRequests() // تصحيح الخطأ في اسم الدالة
                .requestMatchers(HttpMethod.valueOf("/api/v1/auth/register")).permitAll() // السماح للجميع بالوصول للتسجيل
                .requestMatchers("/api/v1/auth/admin").hasAuthority("ADMIN") // السماح للمسؤول بالوصول للمسار الخاص بالمسؤولين
                .requestMatchers("/api/v1/auth/user").hasAuthority("USER") // السماح للمستخدم بالوصول للمسار الخاص بالمستخدمين
                .requestMatchers("/api/v1/login").hasAuthority("ADMIN") // السماح للمسؤول بالوصول لصفحة تسجيل الدخول
                .anyRequest().authenticated() // تأمين جميع المسارات الأخرى
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}