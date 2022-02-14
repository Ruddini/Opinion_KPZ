package com.example.hotelopinionapp.config;

import com.example.hotelopinionapp.dto.user.LoggedUserDto;
import com.example.hotelopinionapp.model.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // FIXME: 09.12.2021 fast access only for app test
//        req.getSession().setAttribute("loggedUser", new LoggedUserDto(1, "a@a", UserRole.ADMIN));
//        filterChain.doFilter(req, resp);

        String requestUri = req.getRequestURI();
        LoggedUserDto loggedUser = (LoggedUserDto) req.getSession().getAttribute("loggedUser");
        if (
                requestUri.startsWith("/authFree") ||
                        requestUri.startsWith("/login") ||
                        requestUri.startsWith("/register") ||
                        requestUri.endsWith(".css") ||
                        requestUri.endsWith(".jpg") ||
                        requestUri.endsWith(".png") ||
                        requestUri.endsWith(".woff2") ||
                        requestUri.endsWith(".ttf") ||
                        requestUri.endsWith(".ico") ||
                        requestUri.endsWith("app.js")
        ) {
            filterChain.doFilter(req, resp);
            return;
        } else if (loggedUser != null) {
            if (
                    requestUri.startsWith("/admin") && loggedUser.getRole() == UserRole.ADMIN ||
                            requestUri.startsWith("/user") && loggedUser.getRole() == UserRole.USER
            ) {
                filterChain.doFilter(req, resp);
                return;
            }
        }

        resp.sendRedirect("/login");
    }

    @Override
    public void destroy() {

    }
}
