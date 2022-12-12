package com.andrew2dos.restingplace.security;

import com.andrew2dos.restingplace.config.CustomAccessDeniedHandler;
import com.andrew2dos.restingplace.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // можно не вкл т.к. уже есть в @EnableWebSecurity
@EnableWebSecurity // Включает безопасность
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
        // Преобразователь паролей
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index", "/bookingList", "/error", "/fragments", "/forbidden", "/login", "/user/register")
                .permitAll() // .permitAll()  разрешено всем пользователям
                .antMatchers("/user/register")
                //.antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN") разрешено только админам. "ROLE_" добавит сам
                //.antMatchers("/read_profile/**").hasAuthority("READ_PROFILE") права доступа. разрешено тем у кого есть в БД право READ_PROFILE
                .permitAll()
                .anyRequest().authenticated() // .authenticated("/profil/**") разрешено только аунтифицированнм пользователям
                .and()// Разделитель
                .formLogin() // .loginProcessingUrl("/signin") указывает где искать кстомную форму, иначе будет spring стандарт форма для логина
                .loginProcessingUrl("/signin")
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/index") // куда кинуть после успешного логина
                .usernameParameter("userName")
                .passwordParameter("password")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                // .logout() указываем куда перекинуть после логаута, иначе будет выкидывать на форму для логина
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll()
                .deleteCookies("JSESSIONID")
                .and()
                .rememberMe().tokenValiditySeconds(3600000).key("secret").rememberMeParameter("checkRememberMe");
    }

//          // Хранение пользователей в inMemory
//    @Bean
//    public UserDetailsService users() throws Exception { // Создание пользователей
//        UserDetails user = User.builder() // UserDetails - мин. ин-ция о пользователях
//                .username("user")
//                .password("{bcrypt}dfdsfdsfjoidjfa-/655654@@$%$")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder() // UserDetails - мин. ин-ция о пользователях
//                .username("admin")
//                .password("{bcrypt}dfdsfdsfjoidjfa-/655654@@$%$")
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(user,admin); // загоняем пользователей в память
//    }

//          // JDBC(БД) Authentication:
//    @Bean
//    public JdbcUserDetailsManager user(DataSource dataSource) { // DataSource - знает информацию о БД
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}dfdsfdsfjoidjfa-/655654@@$%$")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}dfdsfdsfjoidjfa-/655654@@$%$")
//                .roles("ADMIN", "USER")
//                .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource); // загоняем пользователей в БД
//        if(jdbcUserDetailsManager.userExists(user.getUsername())){ // проверка на уже существующего пользователя
//            jdbcUserDetailsManager.deleteUser(user.getUsername());
//        }
//        if(jdbcUserDetailsManager.userExists(admin.getUsername())){
//            jdbcUserDetailsManager.deleteUser(admin.getUsername());
//        }
//        jdbcUserDetailsManager.createUser(user); // загоняем пользователей в БД
//        jdbcUserDetailsManager.createUser(admin);
//
//        return jdbcUserDetailsManager;
//    }

            // использование данный для аунтефикации из своих собственных таблиц
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);// проверка на существование пользователя
//        return authenticationProvider;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

}
