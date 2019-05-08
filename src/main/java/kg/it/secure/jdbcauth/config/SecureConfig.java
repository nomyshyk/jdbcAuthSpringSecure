package kg.it.secure.jdbcauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecureConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // если раскомменить в авторизуется любой зареганый
                // в базе активный юзер
                /*.csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();*/

                // доступ к эндпоинтам строго по роли в БД
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/admin")
                .access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.GET, "/user")
                .access("hasRole('USER')")
                .and().httpBasic()
                .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select login, password, " +
                        "is_enabled from t_user where login=?").
                authoritiesByUsernameQuery("select u.login, " +
                        "ur.role as role " +
                        "from t_user_roles ur " +
                        "inner join t_user u on ur.user_id = u.id " +
                        "where u.login=?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}