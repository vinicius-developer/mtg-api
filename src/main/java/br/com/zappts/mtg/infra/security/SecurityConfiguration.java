package br.com.zappts.mtg.infra.security;

import br.com.zappts.mtg.domain.user.database.repository.UserRepository;
import br.com.zappts.mtg.infra.security.service.AuthenticationService;
import br.com.zappts.mtg.infra.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    private final TokenService tokenService;

    private final UserRepository userRepository;

    public SecurityConfiguration(@Autowired AuthenticationService authenticationService,
                                 TokenService tokenService,
                                 UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.authenticationService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http = this.configureCorsAndCsrfDisable(http);

        http = this.configureSessionStateless(http);

        http = this.configureErrorHandler(http);

        http = this.configureRoutes(http);

        http = this.configureFilter(http);

    }

    private HttpSecurity configureFilter(HttpSecurity http) {
        return http.addFilterBefore(
                new JwtSecurityFilter(this.tokenService, this.userRepository),
                UsernamePasswordAuthenticationFilter.class
        );
    }

    private HttpSecurity configureRoutes(HttpSecurity http) throws Exception {
        return http.cors().and().csrf().disable().authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/user/login")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/user/create")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/list/find/*")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/list/all")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and();
    }

    private HttpSecurity configureCorsAndCsrfDisable(HttpSecurity http) throws Exception {
        return http.cors().and().csrf().disable();
    }

    private HttpSecurity configureSessionStateless(HttpSecurity http) throws Exception {
        return http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();
    }

    private HttpSecurity configureErrorHandler(HttpSecurity http) throws Exception {
        return http.exceptionHandling()
                .authenticationEntryPoint(
                        ((request, response, authException) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    authException.getMessage()
                            );
                        })
                ).and();
    }
}
