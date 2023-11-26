package br.com.cleilsonandrade.gestao_vagas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.cleilsonandrade.gestao_vagas.providers.JWTCandidateProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {

  @Autowired
  private JWTCandidateProvider jwtCandidateProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // SecurityContextHolder.getContext().setAuthentication(null);
    String header = request.getHeader("Authorization");

    if (request.getRequestURI().startsWith("/candidates")) {

      if (header != null && header.startsWith("Bearer ")) {
        var token = this.jwtCandidateProvider.validateToken(header);

        if (token == null) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return;
        }

        request.setAttribute("candidate_id", token.getSubject());

        var roles = token.getClaim("roles").asList(Object.class);

        var grants = roles.stream()
            .map(
                role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
            .toList();

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(),
            SecurityContextHolder.getContext(),
            grants);

      }
    }

    filterChain.doFilter(request, response);
  }

}
