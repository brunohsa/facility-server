package br.com.facility.security;

import br.com.facility.security.handler.AuthenticationEntryPointError;
import br.com.facility.security.handler.AuthenticationLoginFailureHandler;
import br.com.facility.security.filters.JWTAuthenticationFilter;
import br.com.facility.security.filters.LoginFilter;
import br.com.facility.security.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserAuthenticationService userAuthenticationService;

	@Autowired
	private JWTAuthenticationFilter authenticationFilter;

	@Autowired
	private AuthenticationLoginFailureHandler authenticationLoginFailureHandler;

	@Autowired
	private AuthenticationEntryPointError restAuthenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and()
			.csrf().disable();

		http.authorizeRequests()
				.antMatchers("/users/insert", "/facility/**").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated()
				.and()
				.logout().permitAll();

		// filtros de login e autenticação
		http.addFilterBefore(getLoginFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

		http.exceptionHandling()
				.authenticationEntryPoint(restAuthenticationEntryPoint);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userAuthenticationService);//.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	protected LoginFilter getLoginFilter() throws Exception {
		LoginFilter loginFilter = new LoginFilter();
		loginFilter.setAuthenticationManager(authenticationManager());
		loginFilter.setAuthenticationFailureHandler(authenticationLoginFailureHandler);

		return loginFilter;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
			}
		};
	}
}
