package hh.palvelinohjelmointi.tyoajanseuranta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.palvelinohjelmointi.tyoajanseuranta.controllers.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserDetailServiceImpl userDetailsService;	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out
        .and()
        .authorizeRequests().antMatchers("/signup", "/saveuser").permitAll()
        .and()
        .authorizeRequests().antMatchers("/delete**").hasAnyAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .authorizeRequests().antMatchers("/h2-console/**").permitAll()

        .and().csrf().ignoringAntMatchers("/h2-console/**")

        .and().headers().frameOptions().sameOrigin()
        .and()
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/workdaylist")
          .permitAll()
          .and()
      .logout()
          .permitAll();
    }
	

    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
   

}