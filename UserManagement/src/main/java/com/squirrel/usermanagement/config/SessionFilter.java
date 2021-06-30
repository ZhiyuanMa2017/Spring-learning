package com.squirrel.usermanagement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SessionFilter implements Filter {

    protected Logger LOGGER = LoggerFactory.getLogger(SessionFilter.class);
    private static Set<String> GREENURLSET = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        GREENURLSET.add("/toRegister");
        GREENURLSET.add("/toLogin");
        GREENURLSET.add("/login");
        GREENURLSET.add("/loginOut");
        GREENURLSET.add("/register");
        GREENURLSET.add("/verified");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=UTF-8");
        if (uri.endsWith(".js")
                || uri.endsWith(".css")
                || uri.endsWith(".jpg")
                || uri.endsWith(".gif")
                || uri.endsWith(".png")
                || uri.endsWith(".ico")) {
            LOGGER.debug("security filter, pass, " + request.getRequestURI());
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        System.out.println("request uri is: " + uri);
        if (GREENURLSET.contains(uri) || uri.contains("/verified/")) {
            LOGGER.debug("security filter, pass, " + request.getRequestURI());
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String id = (String) request.getSession().getAttribute(WebConfiguration.LOGIN_KEY);
        if (ObjectUtils.isEmpty(id)) {
            String html =
                    "<script type=\"text/javascript\">window.location.href=\"/toLogin\"</script>";
            servletResponse.getWriter().write(html);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
