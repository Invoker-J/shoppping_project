package uz.pdp.shopping_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.shopping_project.entity.User;
import uz.pdp.shopping_project.repo.UserRepo;

import java.io.IOException;

@WebServlet(name = "sigUp",value = "/auth/signup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");
        if (!password.equals(rePassword)|| UserRepo.findByUsername(username).isPresent()) {
            resp.sendRedirect("/errPasswordLogin.jsp");
            return;
        }
        User user = User.builder()
                .username(username)
                .password(password)
                .build();

        UserRepo.addUser(user);

        resp.sendRedirect("/index.jsp");

    }
}
