package uz.pdp.shopping_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.shopping_project.entity.User;
import uz.pdp.shopping_project.repo.BasketRepo;
import uz.pdp.shopping_project.repo.UserRepo;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "saveDB", value = "/save/db")
public class SaveDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = null;
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("currentUser");
        if (obj != null) {
            user = (User) obj;
        }


        Optional<User> optionalUser = UserRepo.getUserByCookie(req);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }


        if (user == null) {
            resp.sendRedirect("http://localhost:8080/login.jsp");
            return;
        }

        BasketRepo.addOrder(req);
        resp.sendRedirect("http://localhost:8080/index.jsp");
    }
}
