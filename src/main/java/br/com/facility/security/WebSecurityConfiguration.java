package br.com.facility.security;

import br.com.facility.exceptions.handler.FailureAuthenticationHandler;
import br.com.facility.security.filters.JWTAuthenticationFilter;
import br.com.facility.security.filters.LoginFilter;
import br.com.facility.security.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserAuthenticationService userAuthenticationService;

	@Autowired
	private JWTAuthenticationFilter authenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LoginFilter loginFilter = loginFilter(authenticationManager());

			// filtra requisições de login
		http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class)
			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

		http.authorizeRequests()
				.antMatchers("/users/insert", "/facility/**").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.and()
				.logout().permitAll();

		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userAuthenticationService);
				//.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public LoginFilter loginFilter(AuthenticationManager authenticationManager) {
		LoginFilter filter = new LoginFilter();
		filter.setAuthenticationManager(authenticationManager);
		filter.setAuthenticationFailureHandler(authenticationFailureHandler());
		return filter;
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new FailureAuthenticationHandler();
	}
}
