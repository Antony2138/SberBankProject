package org.example.config;
import org.example.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserConfig {

    private final UserService userService;

    public UserConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> userService.findByEmail(email)
                .map(user -> org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                        .password(user.getPassword())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
