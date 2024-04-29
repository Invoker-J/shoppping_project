package uz.pdp.shopping_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.shopping_project.repo.CategoryRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "deleteCategory",value = "/category/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UUID id = UUID.fromString(req.getParameter("id"));
        CategoryRepo.deleteById(id);
        resp.sendRedirect("/admin/category.jsp");
    }
}
