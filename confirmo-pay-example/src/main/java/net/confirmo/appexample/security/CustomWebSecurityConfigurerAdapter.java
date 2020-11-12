package net.confirmo.appexample.security;

import net.confirmo.appexample.ConfirmoPayExampleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(prefix = "confirmo-pay-example.web-security", value = "enabled", havingValue = "true", matchIfMissing = true)
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private ConfirmoPayExampleProperties confirmoPayExampleConfigProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic().and()
                .authorizeRequests()
                    // UNAUTHENTICATED ENDPOINT - WEBHOOK from confirmo
                    .regexMatchers(HttpMethod.POST, "/invoiceNotification/[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}").permitAll().and()
                .authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.builder()
                        .username(confirmoPayExampleConfigProperties.getUser())
                        .password(passwordEncoder().encode(confirmoPayExampleConfigProperties.getPassword()))
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}