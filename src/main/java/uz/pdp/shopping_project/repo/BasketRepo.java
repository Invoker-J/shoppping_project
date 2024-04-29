package uz.pdp.shopping_project.repo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import uz.pdp.shopping_project.config.ConnectionPoolManager;
import uz.pdp.shopping_project.entity.Basket;
import uz.pdp.shopping_project.entity.Product;
import uz.pdp.shopping_project.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class BasketRepo {


    public static void addOrder(HttpServletRequest req) {

        HttpSession session = req.getSession();
        Object object = session.getAttribute("basket");
        Basket basket;

        User user = null;

        Object obj = session.getAttribute("currentUser");
        if (obj != null) {
            user = (User) obj;
        }


        Optional<User> optionalUser = UserRepo.getUserByCookie(req);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }




        if (user == null) {
            System.out.println("User: " + user);
            return;
        }


        if (object != null) {
            basket = (Basket) object;
            UUID orderId = creatOrder(user.getId());
            for (Map.Entry<Product, Integer> productIntegerEntry : basket.getBasketProducts().entrySet()) {
                String query = "insert into order_product(order_id,product_id,amount) values (?,?,?)";
                try (
                        Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(query)
                ) {
                    preparedStatement.setObject(1,orderId);
                    preparedStatement.setObject(2, productIntegerEntry.getKey().getId());
                    preparedStatement.setObject(3, productIntegerEntry.getValue());
                    preparedStatement.executeUpdate();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
//            session.invalidate();
            session.removeAttribute("basket");
        }



    }


    private static UUID creatOrder(UUID userId) {
        String query = "insert into orders(user_id) values (?) returning id";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                return id;
            } else {
                throw new RuntimeException("Error retrieving basket ID");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
