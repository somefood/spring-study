package me.somefood.club.security.filter;

import lombok.extern.slf4j.Slf4j;
import me.somefood.club.security.util.JWTUtil;
import net.minidev.json.JSONObject;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class ApiCheckFilter extends OncePerRequestFilter {

    private AntPathMatcher antPathMatcher;
    private String pattern;
    private JWTUtil jwtUtil;

    public ApiCheckFilter(String pattern, JWTUtil jwtUtil) {
        this.antPathMatcher = new AntPathMatcher();
        this.pattern = pattern;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("REQUESTURI: {}", request.getRequestURI());
        log.info("{}", antPathMatcher.matchStart(pattern, request.getRequestURI()));

        if (antPathMatcher.match(pattern, request.getRequestURI())) {
            log.info("ApiCheckFilter.............");

            boolean checkHeader = checkAuthHeader(request);

            if (checkHeader) {
                filterChain.doFilter(request, response);
                return;
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json;charset=utf-8");
                JSONObject json = new JSONObject();
                String message = "FAIL CHECK API TOKEN";
                json.put("code", "403");
                json.put("message", message);

                PrintWriter out = response.getWriter();
                out.print(json);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkAuthHeader(HttpServletRequest request) {
        boolean checkResult = false;

        String authHeader = request.getHeader("Authorization");

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            log.info("Authorization exist: {}", authHeader);

            try {
                String email = jwtUtil.validateAndExtract(authHeader.substring(7));
                log.info("validate result: {}", email);
                checkResult = email.length() > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return checkResult;
    }
}
