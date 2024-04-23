package com.example.login_spring.Interceptor;
import com.example.login_spring.Service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;

    public TokenInterceptor(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Quitar "Bearer "
            try {
                tokenService.extractRolFromToken(token);
                return true;
            } catch (ExpiredJwtException e) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token expirado");
                return false;
            } catch (Exception e) {
                response.sendError(HttpStatus.FORBIDDEN.value(), "Acceso Prohibido");
                return false;
            }
        } else {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "No autorizado");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}