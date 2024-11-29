package dao;

import entity.Product;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.DataSourceUtils;
public class ProductsSaledDao {

    private static final String QUERY =
            "SELECT p.productId, p.productName, p.productImage, IFNULL(SUM(oi.quantity), 0) AS total_sales " +
                    "FROM Products p, OrderItems oi " +
                    "where p.productId = oi.productId " +
                    "GROUP BY p.productId " +
                    "order by total_sales desc";
    QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());

    public Map<Product, Integer> getSalesLeaderboard() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        return queryRunner.query(QUERY,new ResultSetHandler <Map<Product, Integer>>(){
            public Map<Product, Integer> handle(ResultSet rs) throws SQLException{
                Map<Product, Integer> leaderboard = new LinkedHashMap<>();
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
                return leaderboard;
            }
        });

    }
}
