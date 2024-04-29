package uz.pdp.shopping_project.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.shopping_project.entity.User;
import uz.pdp.shopping_project.repo.UserRepo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@WebFilter(filterName = "checkFilterAdmin",urlPatterns = {"/admin/*",})
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        Object object = session.getAttribute("currentUser");

        if (object == null) {
            Optional<Cookie> cookieOptional = Arrays.stream(req.getCookies()).filter(cookie -> cookie.getName().equals("userId")).findFirst();

            if (cookieOptional.isPresent()) {
                System.out.println("Cookie found");
                Cookie cookie = cookieOptional.get();
                UUID userId = UUID.fromString(cookie.getValue());
                Optional<User> optionalUser = UserRepo.findByUserId(userId);

                if (optionalUser.isPresent()) {
                    if (optionalUser.get().getRole().equals("admin")) {
                        session.setAttribute("currentUser",optionalUser.get());
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                }
            }
                resp.sendRedirect("/404");

        } else {
            User currentUser = (User) object;
            if (!currentUser.getRole().equals("admin")){
                resp.sendRedirect("/404");
                return;
            }
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}