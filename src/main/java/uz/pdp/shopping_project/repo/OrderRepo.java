package uz.pdp.shopping_project.repo;

import uz.pdp.shopping_project.config.ConnectionPoolManager;
import uz.pdp.shopping_project.entity.Order;
import uz.pdp.shopping_project.entity.OrderProduct;
import uz.pdp.shopping_project.entity.Product;
import uz.pdp.shopping_project.entity.User;
import uz.pdp.shopping_project.enums.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderRepo {
    public static List<Order> findAll() {
        String query = "select * from orders";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(new Order(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getObject("datetime", LocalDateTime.class),
                        UUID.fromString(resultSet.getString("user_id")),
                        Status.valueOf(resultSet.getString("status"))
                        ));
            }
            return orders;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<OrderProduct> findOrdersByOrderId(UUID id) {

        String query = "select * from order_product where order_id = ?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderProduct> orderProducts = new ArrayList<>();
            while (resultSet.next()) {
                orderProducts.add(
                        new OrderProduct(
                                UUID.fromString(resultSet.getString("order_id")),
                                UUID.fromString(resultSet.getString("product_id")),
                                resultSet.getInt("amount")
                        )
                );
            }
            return orderProducts;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
