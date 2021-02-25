package edu.epam.auth.controller.filter;

import edu.epam.auth.constant.AttributeConstant;
import edu.epam.auth.constant.ParameterConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "NoActionFilter", urlPatterns = "/main")
public class NoActionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String comeFrom = ((HttpServletRequest) request).getHeader("referer");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String locale = request.getParameter(ParameterConstant.LOCALE);
        if (request.getParameter(ParameterConstant.ACTION) == null && locale != null) {
            ((HttpServletRequest) request).getSession().setAttribute(AttributeConstant.LOCALE, locale);
            httpServletResponse.sendRedirect(comeFrom);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
