package fr.excilys.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
				.and().withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER").and()
				.withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and()
				.formLogin();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DigestAuthenticationEntryPoint digestEntryPoint() {
		final DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new DigestAuthenticationEntryPoint();
		digestAuthenticationEntryPoint.setRealmName("Contacts Realm via Digest Authentication");
		digestAuthenticationEntryPoint.setKey("alize");
		return digestAuthenticationEntryPoint;
	}

	@Bean
	public DigestAuthenticationFilter digestAuthentificAuthenticationFilter(
			DigestAuthenticationEntryPoint authenticationEntryPoint) {
		final InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
		inMemoryUserDetailsManager.createUser(User.builder().username("user").password("user").authorities("READ")
				.accountExpired(false).accountLocked(false).disabled(false).build());
		final DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
		digestAuthenticationFilter.setUserDetailsService(inMemoryUserDetailsManager);
		digestAuthenticationFilter.setAuthenticationEntryPoint(authenticationEntryPoint);
		return digestAuthenticationFilter;
	}

}