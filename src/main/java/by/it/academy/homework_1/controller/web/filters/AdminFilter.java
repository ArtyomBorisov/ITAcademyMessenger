package by.it.academy.homework_1.controller.web.filters;

import by.it.academy.homework_1.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession(false);
        if((session != null) && (session.getAttribute("user") != null)) {
            User user = (User) session.getAttribute("user");
            if ("admin".equals(user.getLogin())) {
                chain.doFilter(request, response);
                return;
            }
        }

        res.sendRedirect(contextPath + "/mainPage");
    }

    @Override
    public void destroy() {
    }
}
