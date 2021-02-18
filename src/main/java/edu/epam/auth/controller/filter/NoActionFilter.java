package edu.epam.auth.controller.filter;

import edu.epam.auth.constant.ParameterConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Encoding", urlPatterns = "/main")
public class NoActionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String comeFrom = request.getRemoteAddr();
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (request.getParameter(ParameterConstant.ACTION) == null) {
            httpServletResponse.sendRedirect(comeFrom);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
