package edu.egg.agendate.seguridad;

import edu.egg.agendate.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServicio us;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(us);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

     String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
            };
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
               // "/registro-prof-form**",
                //"/js/**",
                //"/css/**",
                //"/img/**").permitAll()
                resources).permitAll()
                .antMatchers("/","/index").permitAll()
                .antMatchers("registro-turnos","/registration").permitAll()
                .and().formLogin()                                                            
                        .loginPage("/login") 
                        .loginProcessingUrl("/logincheck")
                        .usernameParameter("email") 
                        .passwordParameter("contraseña") 
                        .defaultSuccessUrl("/index", true).permitAll()
                .and().csrf().disable();
                //.antMatchers("/inicio/login").permitAll();
                //.anyRequest().authenticated()
                //.antMatchers("/profesional*","/").access("hasAuthority('ROLE_USER')");
                //.antMatchers("/").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
    }
}
