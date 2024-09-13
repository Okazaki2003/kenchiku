package com.seproject.buildmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // http.authorizeRequests(authorizeRequests -> authorizeRequests
    // // TODO:開発中はフルオープンでhttpアクセスを可能とする
    // .requestMatchers("/**").permitAll().anyRequest().authenticated())
    // .formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
    // .logout(logout -> logout.permitAll());

    http.formLogin(login -> login.loginPage("/login") // ログインが必要な時、このURLに対応するページを送出する
        .defaultSuccessUrl("/menu").permitAll() // フォーム認証画面は認証不要
    ).authorizeHttpRequests(authz -> authz.requestMatchers("/css/**").permitAll() // CSSファイルは認証不要
        .requestMatchers("/").permitAll() // トップページは認証不要
        .anyRequest().authenticated() // 他のURLはログイン後アクセス可能
    ).logout(logout -> logout.logoutUrl("/logout") // ログアウトURL
        .logoutSuccessUrl("/login?logout") // ログアウト成功後のリダイレクトURL
        .invalidateHttpSession(true) // セッションを無効化
        .deleteCookies("JSESSIONID") // クッキーを削除
        .permitAll());
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // @Bean
  // public UserDetailsService userDetailsService() {
  // return username -> {
  // if ("user".equals(username)) {
  // return org.springframework.security.core.userdetails.User.withUsername("user")
  // .password(passwordEncoder().encode("password")).roles("USER").build();
  // } else {
  // throw new UsernameNotFoundException("User not found");
  // }
  // };
  // }
}
