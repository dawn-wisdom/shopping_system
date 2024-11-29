package service;
import dao.ProductsSaledDao;
import entity.Product;


import java.sql.SQLException;
import java.util.Map;

public class ProductSaledService {
    private ProductsSaledDao productsSaledDao=new ProductsSaledDao();
    public Map<Product, Integer> getSalesLeaderboard() throws SQLException {
        return productsSaledDao.getSalesLeaderboard();
    }
}
