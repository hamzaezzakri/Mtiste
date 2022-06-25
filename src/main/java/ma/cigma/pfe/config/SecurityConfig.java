package ma.cigma.pfe.config;

import ma.cigma.pfe.jwt.AuthEntryPointJwt;
import ma.cigma.pfe.jwt.AuthenticationTokenFilter;
import ma.cigma.pfe.jwt.AuthorizationTokenFilter;
import ma.cigma.pfe.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 3:17 PM
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHnadler;
    @Autowired
    private AuthorizationTokenFilter authorizationTokenFilter;
    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHnadler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authorizationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilter(new AuthenticationTokenFilter(authenticationManager(),jwtUtils));
    }

}
