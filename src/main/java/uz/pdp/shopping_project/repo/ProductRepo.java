package uz.pdp.shopping_project.repo;

import uz.pdp.shopping_project.config.ConnectionPoolManager;
import uz.pdp.shopping_project.entity.Category;
import uz.pdp.shopping_project.entity.Product;
import uz.pdp.shopping_project.servlets.BasketServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ProductRepo {
    public static List<Product> findAll() {
        String query = "select * from product";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(new Product(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        UUID.fromString(resultSet.getString("category_id")),
                        resultSet.getBytes("photo")
                ));

            }
            return products;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(Product product) {
        String query = "insert into product(name,price,category_id,photo) values (?,?,?,?)";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setObject(3, product.getCategoryId());
            System.out.println(Arrays.toString(product.getPhoto()));
            preparedStatement.setBytes(4, product.getPhoto());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Product findById(UUID id) {
        String query = "select * from product where id = ?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Product product = new Product(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    UUID.fromString(resultSet.getString("category_id")),
                    resultSet.getBytes("photo")
            );
            return product;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void edit(Product product) {
        String query = "update product set name = ?,price = ?,category_id = ? where id = ?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setObject(2, product.getPrice());
            preparedStatement.setObject(3, product.getCategoryId());
            preparedStatement.setObject(4, product.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> getAllProductsByCategory(UUID id) {
        String query = "select * from product where category_id = ?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(new Product(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        UUID.fromString(resultSet.getString("category_id")),
                        resultSet.getBytes("photo")
                ));

            }
            return products;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteById(UUID id) {
        String query = "delete from product where id=?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1,id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Map<Product, Integer> productIntegerMap = countProducts();
        productIntegerMap.forEach((product, integer) -> {

        });
    }

    public static Map<Product, Integer> countProducts() {
        Map<Product, Integer> productCounts = new HashMap<>();


        for (Product product : BasketServlet.products) {
            if (productCounts.containsKey(product)) {
                productCounts.put(product, productCounts.get(product) + 1);
            } else {
                productCounts.put(product, 1);
            }
        }
        return productCounts;
    }
    public static Integer getCountsOfProducts(UUID id){
                int count = 0;
        for (Product product : BasketServlet.products) {
            if (product.getId().equals(id)) {
                count++;
            }
        }
        return count;
    }
}