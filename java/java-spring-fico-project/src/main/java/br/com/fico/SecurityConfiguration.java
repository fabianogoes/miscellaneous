package br.com.fico;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	//**
	// Referencias:
	// http://spr.com/part-5-integrating-spring-security-with-spring-boot-web/
	// http://alanlanzoni.blogspot.com.br/2013/05/pegar-usuario-logado-spring-security.html
	// http://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html
	// 
	// create table users (    username varchar(50) not null primary key,    password varchar(255) not null,    enabled boolean not null) engine = InnoDb;create table authorities (    username varchar(50) not null,    authority varchar(50) not null,    foreign key (username) references users (username),    unique index authorities_idx_1 (username, authority)) engine = InnoDb;
	//
	// Usuário Logado
	// SecurityContextHolder.getContext().getAuthentication().getName();
	//
	
	@Autowired
	private DataSource datasource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	http.authorizeRequests().antMatchers("/login**").permitAll();
    	http.authorizeRequests().antMatchers("/api**").permitAll(); // TODO: implementar autenticação por API
    	http.authorizeRequests().antMatchers("/css**").permitAll();
    	http.authorizeRequests().antMatchers("/js**").permitAll();
    	http.authorizeRequests().antMatchers("/img**").permitAll();
    	http.authorizeRequests().antMatchers("/plugins**").permitAll();
    	http.authorizeRequests().antMatchers("/static/**").permitAll();
        
//        http.csrf().disable();
        
    	//http.authorizeRequests().anyRequest().authenticated();
        http
        	.csrf().disable()
        	.formLogin().failureUrl("/login?error")
            .defaultSuccessUrl("/")
            .loginPage("/login")
            .permitAll()
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
            .permitAll();
    }
    
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//	        .antMatchers("/login")
//        	.antMatchers("/css**")
//        	.antMatchers("/js**")
//        	.antMatchers("/img**")
//        	.antMatchers("/plugins**");
//    }    
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
        userDetailsService.setDataSource(datasource);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
 
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
        auth.jdbcAuthentication().dataSource(datasource);
 
        if(!userDetailsService.userExists("admin@admin.com")) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            User userDetails = new User("admin@admin.com", encoder.encode("admin"), authorities);
            userDetailsService.createUser(userDetails);
        }
        
    }
}
