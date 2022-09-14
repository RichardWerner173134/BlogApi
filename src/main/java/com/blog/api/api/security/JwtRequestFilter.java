package com.blog.api.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
                username = jwtUtil.extractUsername(jwt);
            }
        } catch(Exception e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);

            if(jwtUtil.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null,
                                userDetails.getAuthorities()
                        );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            filterChain.doFilter(request, response);
            return;
        }
        logger.error("JWT-Auth failed");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        Map<Pattern, HttpMethod> excludeMap = new HashMap<>();
        excludeMap.put(Pattern.compile("/h2-console"), null);
        excludeMap.put(Pattern.compile("/init"), null);
        excludeMap.put(Pattern.compile("/authenticate"), HttpMethod.POST);
        excludeMap.put(Pattern.compile("/register"), HttpMethod.POST);
        excludeMap.put(Pattern.compile("/users"), HttpMethod.GET);
        excludeMap.put(Pattern.compile("/beitraege"), HttpMethod.GET);
        excludeMap.put(Pattern.compile("/beitraege/[0-9]*/addView"), HttpMethod.POST);

        String method = request.getMethod();

        return excludeMap.entrySet()
                .stream()
                .anyMatch(d -> d.getKey().matcher(request.getRequestURI()).find() &&
                        (d.getValue() == null || method.equals(d.getValue().name())));
    }
}
