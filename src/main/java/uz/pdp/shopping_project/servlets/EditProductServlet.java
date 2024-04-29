package uz.pdp.shopping_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.shopping_project.entity.Category;
import uz.pdp.shopping_project.entity.Product;
import uz.pdp.shopping_project.repo.CategoryRepo;
import uz.pdp.shopping_project.repo.ProductRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "editProduct", value = "/product/edit")

public class EditProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        UUID categoryId = UUID.fromString(req.getParameter("categoryId"));
        UUID id = UUID.fromString(req.getParameter("id"));
        Product product = new Product(
                id,
                name,
                price,
                categoryId,
                null
        );
        ProductRepo.edit(product);
        resp.sendRedirect("/admin/product.jsp");
    }
}
