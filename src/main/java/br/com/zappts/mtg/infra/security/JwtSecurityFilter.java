package br.com.zappts.mtg.infra.security;

import br.com.zappts.mtg.domain.user.database.entities.UserEntity;
import br.com.zappts.mtg.domain.user.database.repository.UserRepository;
import br.com.zappts.mtg.infra.security.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Optional;

public class JwtSecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserRepository userRepository;

    public JwtSecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = this.getBearerTokenFromRequest(request);

        if(this.tokenService.validateToken(token))  {
            try {
                this.authenticate(token);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "token invalido");
            }
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) {

        Long id = this.tokenService.getUserId(token);

        UserEntity user = this.getUser(id);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserEntity getUser(Long id) {
        Optional<UserEntity> user = this.userRepository.findById(id);

        if(user.isEmpty()) {
            throw new InvalidParameterException("Id de usuario nao existe");
        }

        return user.get();
    }

    private String getBearerTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        return this.tokenService.restoreToken(token);
    }

}
