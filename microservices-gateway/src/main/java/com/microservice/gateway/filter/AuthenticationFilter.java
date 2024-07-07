package com.microservice.gateway.filter;

import com.microservice.gateway.utils.JwtUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {
    private final RouteValidator routeValidator;

    private final JwtUtils jwtUtils;
    public AuthenticationFilter(){
        super(NameConfig.class);
        this.routeValidator = new RouteValidator();
        this.jwtUtils = new JwtUtils();
    }
    @Override
    public GatewayFilter apply(NameConfig config) {
        return ((exchange, chain) -> {
            if (this.routeValidator.isSecured.test(exchange.getRequest())){
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("missing authorization header");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                }
                try {
                    this.jwtUtils.validateToken(authHeader);
                }catch(Exception e){
                    throw new RuntimeException("un authorized access to application");
                }
                Map<String,Object> claims = this.jwtUtils.getClaims(authHeader);
                List<String> authorities = Arrays.stream(toString().split(",")).toList();
                if(exchange.getRequest().getMethod().name().contains("POST") && (!authorities.contains("CREATE"))){
                        throw new RuntimeException("You do not have the necessary permissions to perform this action");
                }
                if(exchange.getRequest().getMethod().name().contains("DELETE") && (!authorities.contains("DELETE"))){
                    throw new RuntimeException("You do not have the necessary permissions to perform this action");
                }
                if(exchange.getRequest().getMethod().name().contains("PATCH") && (!authorities.contains("REFACTOR"))){
                    throw new RuntimeException("You do not have the necessary permissions to perform this action");
                }
                if(exchange.getRequest().getMethod().name().contains("PUT") && (!authorities.contains("UPDATE"))){
                    throw new RuntimeException("You do not have the necessary permissions to perform this action");
                }
            }
            return chain.filter(exchange);
        });
    }
}
