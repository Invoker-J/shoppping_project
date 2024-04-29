package uz.pdp.shopping_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.shopping_project.entity.Category;
import uz.pdp.shopping_project.repo.CategoryRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "editCategory", value = "/category/edit")
public class EditCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        UUID id = UUID.fromString(req.getParameter("id"));
        Category category = new Category(
                id,name
        );
        CategoryRepo.edit(category);
        resp.sendRedirect("/admin/category.jsp");
    }
}
