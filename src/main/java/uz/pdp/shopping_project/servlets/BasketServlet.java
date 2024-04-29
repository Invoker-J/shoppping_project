package uz.pdp.shopping_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.shopping_project.entity.Basket;
import uz.pdp.shopping_project.entity.Product;
import uz.pdp.shopping_project.repo.BasketRepo;
import uz.pdp.shopping_project.repo.CategoryRepo;
import uz.pdp.shopping_project.repo.ProductRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "addBasket", value = "/add/category")
public class BasketServlet extends HttpServlet {
    public static List<Product> products = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID productId = UUID.fromString(req.getParameter("id"));
        Product product = ProductRepo.findById(productId);
//        BasketRepo.addProducts(productId);
//        products.add(product);

        HttpSession session = req.getSession();
        Object object = session.getAttribute("basket");
        Basket basket;
        if (object == null) {
            basket = new Basket();
        } else {
            basket = (Basket) object;
        }

        basket.getBasketProducts().put(product,1);
        session.setAttribute("basket",basket);
        resp.sendRedirect(
                "/user/pageByCategory.jsp?id=%s".formatted(product.getCategoryId())
        );
    }
}
