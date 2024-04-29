package uz.pdp.shopping_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.pdp.shopping_project.entity.Product;
import uz.pdp.shopping_project.repo.ProductRepo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet(name = "addProduct", value = "/product/add")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2 MB
        maxFileSize = 1024 * 1024 * 10,        // 10 MB
        maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Integer price = Integer.parseInt(req.getParameter("price"));
        UUID categoryId = UUID.fromString(req.getParameter("categoryId"));

        Part part = req.getPart("photo");
        InputStream inputStream = part.getInputStream();


        Product product = new Product();

        product.setName(name);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        product.setPhoto(inputStream.readAllBytes());
        ProductRepo.save(product);
        resp.sendRedirect("/admin/product.jsp");
    }
}
