package com.utkarsh.filmcampbackend.config;

import com.utkarsh.filmcampbackend.service.JWTService;
import com.utkarsh.filmcampbackend.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    JWTService jwtService;
    @Autowired
    ApplicationContext context;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader =request.getHeader("Authorization");
        String token=null;
        String userName=null;
        if(authHeader!=null&&authHeader.startsWith("Bearer ")){
            token= authHeader.substring(7);
            try {
                userName = jwtService.extractUsername(token);
            } catch (io.jsonwebtoken.ExpiredJwtException e) {
                // The token is expired! We log it quietly and leave userName as null.
                // This allows public endpoints to still work for this mobile user.
                logger.info("An expired JWT token was sent to the server. Proceeding as anonymous.");
            } catch (io.jsonwebtoken.JwtException e) {
                // This catches other token errors (like bad signatures or malformed strings)
                logger.info("An invalid JWT token was sent to the server. Proceeding as anonymous.");
            }
        }
        if(userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=context.getBean(MyUserDetailsService.class).loadUserByUsername(userName);
            if(jwtService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            };
        }
        filterChain.doFilter(request,response);
    }

}
