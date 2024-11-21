package service;

import dao.CustomerDao;
import dao.SellerDao;
import entity.Customer;
import entity.Seller;

import java.sql.SQLException;

public class SellerService {
    private SellerDao sellerDao = new SellerDao();

    /**
     * 登录操作
     * @param sellerName
     * @param password
     * @return rel
     */
    public Seller login(String sellerName, String password) {
        // 声明一个用户实体类，用于接收返回的user
        Seller seller=null;
        // 调用userDao中方法根据用户名查询是否存在该用户
        seller = sellerDao.login(sellerName, password);

        return seller;
    }

    public boolean register(String sellerName,String stroeName,String password,String email,String phone) throws SQLException {
        Seller seller = new Seller();
        seller.setSellerName(sellerName);
        seller.setPassword(password);
        seller.setEmail(email);
        seller.setPhone(phone);
        seller.setStoreName(sellerName);
        return sellerDao.register(seller);
    }
}
