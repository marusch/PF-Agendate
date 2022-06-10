/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

<<<<<<< HEAD

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioServicio us;
=======
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServicio us;

>>>>>>> 8e5fcb11ce01fe34be64b5a2a0a49c0aa827f767
     @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
<<<<<<< HEAD
=======

>>>>>>> 8e5fcb11ce01fe34be64b5a2a0a49c0aa827f767
     @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(us);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
<<<<<<< HEAD
=======
    
>>>>>>> 8e5fcb11ce01fe34be64b5a2a0a49c0aa827f767
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
<<<<<<< HEAD
=======
   
   
>>>>>>> 8e5fcb11ce01fe34be64b5a2a0a49c0aa827f767
   @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                "/registro**",
                "/js/**",
                "/css/**",
                "/img/**").permitAll();
<<<<<<< HEAD
    }
}  

=======
               
    }
    
    

}
>>>>>>> 8e5fcb11ce01fe34be64b5a2a0a49c0aa827f767
