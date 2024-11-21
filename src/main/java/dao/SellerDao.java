package dao;

import entity.Customer;
import entity.Seller;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;

public class SellerDao {
    // 添加用户
    public boolean register(Seller seller) throws SQLException {
        boolean rel=true;
        String sql = "insert into Sellers(sellerName,storeName,password,email,phone) values(?,?,?,?,?)";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        int row = runner.update(sql, seller.getSellerName(), seller.getStoreName(),seller.getPassword(),seller.getEmail(),seller.getPhone());
        if (row == 0) {
            System.out.println("注册失败");
            rel=false;
            throw new RuntimeException();
        }
        System.out.println("注册成功");
        return rel;
    }

    public Seller login(String sellerName,String password)
    {
        String sql="select * from Sellers where sellerName=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        Seller seller = null;
        try {
            System.out.println(sellerName);
            // 查询用户信息
            seller = runner.query(sql, new BeanHandler<>(Seller.class), sellerName);
            if (seller != null) {
                // 判断密码是否匹配
                if (seller.getPassword().equals(password)) {
                    System.out.println("登录成功！");
                } else {
                    System.out.println("密码错误！");
                }
            } else {
                System.out.println("用户不存在！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库查询出错！");
        }
        return seller;
    }
}
