package com.book.alone.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.book.alone.domain.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.headers().frameOptions().disable()

			.and()
				.authorizeHttpRequests(auth ->
					auth.requestMatchers("/", "/css/**", "/images/**",
						"/js/**", "/resources/**", "/h2-console/**").permitAll()
					.requestMatchers("/api/v1/**").hasRole(Role.USER.name())
					.anyRequest().authenticated()
				)
				.logout()
				.logoutSuccessUrl("/")

			.and()
				.oauth2Login(oauth2 -> oauth2
					.userInfoEndpoint(userInfo -> userInfo
						.userService(this.customOAuth2UserService)
					)
				);

		return http.build();
	}
}
