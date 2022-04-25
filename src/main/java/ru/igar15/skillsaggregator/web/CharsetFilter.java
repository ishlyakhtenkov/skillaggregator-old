package ru.igar15.skillsaggregator.web;

import javax.servlet.*;
import java.io.IOException;
import java.util.Objects;

public class CharsetFilter implements Filter{
    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException{
        encoding = config.getInitParameter("requestEncoding");
        if (Objects.isNull(encoding)) {
            encoding = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
        if (Objects.isNull(request.getCharacterEncoding())) {
            request.setCharacterEncoding(encoding);
        }
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        next.doFilter(request, response);
    }
}