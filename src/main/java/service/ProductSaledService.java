package service;
import dao.ProductsSaledDao;
import entity.Product;


import java.util.Map;

public class ProductSaledService {
    private ProductsSaledDao productsSaledDao=new ProductsSaledDao();
    public Map<Product, Integer> getSalesLeaderboard()
    {
        return productsSaledDao.getSalesLeaderboard();
    }
}
