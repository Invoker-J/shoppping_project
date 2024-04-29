package uz.pdp.shopping_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.shopping_project.entity.Category;
import uz.pdp.shopping_project.entity.OrderProduct;
import uz.pdp.shopping_project.repo.CategoryRepo;
import uz.pdp.shopping_project.repo.OrderRepo;

import java.io.IOException;

@WebServlet(name = "addCategory" , value = "/category/add")
public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Category category = new Category();
        category.setName(name);
        CategoryRepo.save(category);
        resp.sendRedirect("/admin/category.jsp");


    }
}
