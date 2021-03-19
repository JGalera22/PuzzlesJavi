package com.salesianostriana.dam.PuzzlesJavi.security

import com.salesianostriana.dam.PuzzlesJavi.security.jwt.JwtAuthenticationEntryPoint
import com.salesianostriana.dam.PuzzlesJavi.security.jwt.JwtAuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class ConfigurePasswordEncoder() {

    @Bean
    fun passwordEncoder() : PasswordEncoder = BCryptPasswordEncoder()

}

@Configuration
class ConfigureCors() {

    @Bean
    fun corsConfigurer() = object: WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry) {
            registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600)
        }
    }
}


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAuthorizationFilter: JwtAuthorizationFilter,
    private val passwordEncoder: PasswordEncoder

) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        // @formater:off
        http
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/login", "/auth/token", "/auth/register").permitAll()
            .antMatchers(HttpMethod.GET, "/puzzle", "/puzzle/{id}").permitAll()

            //Borrar
            .antMatchers(HttpMethod.GET, "/puzzle/**", "/usuario/**").permitAll()
            .antMatchers(HttpMethod.DELETE, "/puzzle/**", "/usuario/**").permitAll()
            .antMatchers(HttpMethod.POST, "/puzzle/**", "/usuario/**").permitAll()
            .antMatchers(HttpMethod.PUT, "/puzzle/**", "/usuario/**").permitAll()

             /*
            .antMatchers(HttpMethod.GET, "/puzzle/deseado").hasRole("USER")
            .antMatchers(HttpMethod.POST, "/puzzle/deseado/{id}").hasRole("USER")
            .antMatchers(HttpMethod.DELETE,"/puzzle/deseado/{id}").hasRole("USER")
            .antMatchers(HttpMethod.GET, "usuario/mine").hasRole("USER")
            */

            /*Borrar, no sirve*/
                /*
            .antMatchers(HttpMethod.POST, "/puzzle/deseado/{id}", "/puzzle/{id}/img").hasRole("USER")
            .antMatchers(HttpMethod.DELETE, "/puzzle/{id}", "/puzzle/deseado/{id}", "/puzzle/{id}/img/{hash}").hasRole("USER")
            .antMatchers(HttpMethod.GET, "usuario/mine").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/usuario", "/usuario/{id}").hasRole("USER")
            .antMatchers(HttpMethod.PUT, "/usuario/{id}").hasRole("USER")
            .antMatchers(HttpMethod.DELETE, "usuario/{id}").hasRole("USER")
                */

                /*
            .antMatchers(HttpMethod.GET, "/puzzle/deseado", "/puzzle", "/puzzle/{id}").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/puzzle", "/puzzle/deseado/{id}", "/puzzle/{id}/img").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/puzzle/{id}").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/puzzle/{id}", "/puzzle/deseado/{id}", "/puzzle/{id}/img/{hash}").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "usuario/mine").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/usuario", "/usuario/{id}").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/usuario/{id}").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "usuario/{id}").hasRole("ADMIN")
*/
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter::class.java)

        http.headers().frameOptions().disable()
        // @formater:on
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}