package uz.pdp.shopping_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import uz.pdp.shopping_project.entity.User;
import uz.pdp.shopping_project.repo.UserRepo;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "auth", value = "/auth/login")

public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");

        Optional<User> userOptional = UserRepo.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                HttpSession session = req.getSession();
                session.setAttribute("currentUser", user);

                if (Objects.equals(rememberMe,"on")) {
                    Cookie cookie = new Cookie(
                            "userId",user.getId().toString()
                    );
                    cookie.setPath("/");
                    cookie.setSecure(false);
                    cookie.setMaxAge(60*60);

                    resp.addCookie(cookie);
                }

                if (user.getRole().equals("user")) {
                    resp.sendRedirect("/index.jsp");
                } else if(user.getRole().equals("admin")) {
                    resp.sendRedirect("/admin/admin.jsp");
                }
                return;
            }
        }
            resp.sendRedirect("/login.jsp");
    }
}