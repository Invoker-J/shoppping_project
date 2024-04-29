package uz.pdp.shopping_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.shopping_project.entity.Basket;
import uz.pdp.shopping_project.entity.Product;
import uz.pdp.shopping_project.repo.ProductRepo;

import java.io.IOException;
import java.util.UUID;

import static uz.pdp.shopping_project.servlets.BasketServlet.products;

@WebServlet(name = "amountOfProduct", value = "/amount/product")
public class AmountOfProducts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Object object = session.getAttribute("basket");
        Basket basket = new Basket();
        if (object != null) {
            basket = (Basket) object;

            String id = req.getParameter("id");
            if (id.endsWith("plus")) {
                String plusId = id.substring(0, id.length() - 4);
                UUID productId = UUID.fromString(plusId);
                basket.incrementProductQuantity(productId);
            } else if (id.endsWith("minus")) {
                String minusId = id.substring(0, id.length() - 5);
                UUID productId = UUID.fromString(minusId);
                basket.decrementProductQuantity(productId);
            }

            resp.sendRedirect("/user/basket.jsp");
        }

    }
}
