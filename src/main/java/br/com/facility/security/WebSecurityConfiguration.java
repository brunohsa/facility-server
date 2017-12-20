package br.com.facility.security;

import br.com.facility.exceptions.handlers.AuthenticationEntryPointError;
import br.com.facility.exceptions.handlers.AuthenticationLoginFailureHandler;
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

			// filtros de login e autenticação
		http.addFilterBefore(getLoginFilter(), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

		http.authorizeRequests()
				.antMatchers("/users/insert", "/facility/**").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated()
				.and()
				.logout().permitAll();

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
}
