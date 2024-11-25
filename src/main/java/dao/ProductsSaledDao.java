package dao;

import entity.Product;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import utils.DataSourceUtils;
public class ProductsSaledDao {

    private static final String QUERY =
            "SELECT p.productId, p.productName, p.productImage, IFNULL(SUM(oi.quantity), 0) AS total_sales " +
                    "FROM Products p, OrderItems oi " +
                    "where p.productId = oi.productId " +
                    "GROUP BY p.productId " +
                    "order by total_sales desc";
    public Map<Product, Integer> getSalesLeaderboard() {
        Map<Product, Integer> leaderboard = new LinkedHashMap<>();
        try (Connection conn = DataSourceUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println(QUERY);
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String productName = rs.getString("productName");
                String productImage = rs.getString("productImage");
                int totalSales = rs.getInt("total_sales");
                Product product = new Product();
                product.setProductId(productId);
                product.setProductName(productName);
                product.setProductImage(productImage);
                leaderboard.put(product, totalSales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaderboard;
    }
}
