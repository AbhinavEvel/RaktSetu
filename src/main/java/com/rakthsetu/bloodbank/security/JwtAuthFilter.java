package com.rakthsetu.bloodbank.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rakthsetu.bloodbank.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;
        
        System.out.println("Auth Header: " + authHeader);

        // Header format check: "Bearer <token>"
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);   // "Bearer " ke 7 characters hata do
            try {
                email = jwtUtil.extractEmail(token);
            } catch (Exception e) {
                // Malformed/tampered token — email null hi rahega, aage authentication set nahi hogi
            	System.out.println("Token extraction FAILED: " + e.getMessage()); //exception cath ke liyee
            }
        }
        
        System.out.println("Extracted Email: " + email);   // <-- LINE 3: yahan add karo (try-catch ke bahar, if-block khatam hone ke baad)

        // Agar email mila aur abhi tak koi authentication set nahi hui (duplicate se bachne ke liye)
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            if (jwtUtil.validateToken(token)) {
                String role = jwtUtil.extractRole(token);

                // Spring Security ko role ke saath "ROLE_" prefix chahiye hota hai convention se
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_" + role))
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);   // Request ko aage badhne do (Controller tak)
    }
}
