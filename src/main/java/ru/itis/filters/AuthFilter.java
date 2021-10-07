package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(urlPatterns = {"/profile", "/clients/*", "/tasks"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if (Arrays.stream(req.getCookies()).noneMatch(cookie -> cookie.getName().equals("auth"))) {
            ((HttpServletResponse) response).sendRedirect("/semwork/index");
            return;
        }

        chain.doFilter(request, response);
    }
}
