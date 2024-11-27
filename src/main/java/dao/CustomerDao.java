package dao;
import java.sql.SQLException;

import entity.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.DataSourceUtils;

//顾客的相关功能，登录、注册
public class CustomerDao {
    // 添加用户
    public boolean register(Customer customer) throws SQLException {
        boolean rel=true;
        String sql = "insert into Customers(customerId,customerName,password,email,phone) values(?,?,?,?,?)";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        int row = runner.update(sql, customer.getCustomerId(), customer.getCustomerName(),customer.getPassword(),customer.getEmail(),customer.getPhone());
        if (row == 0) {
            System.out.println("注册失败");
            rel=false;
            throw new RuntimeException();
        }
        System.out.println("注册成功");
        return rel;
    }

    public Customer login(String customerName,String password)
    {
        String sql="select * from Customers where customerName=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        Customer customer = null;
        try {
            System.out.println(customerName);
            // 查询用户信息
            customer = runner.query(sql, new BeanHandler<>(Customer.class), customerName);
            if (customer != null) {
                // 判断密码是否匹配
                if (customer.getPassword().equals(password)) {
                    System.out.println("登录成功！");
                } else {
                    System.out.println("密码错误！");
                    return null;
                }
            } else {
                System.out.println("用户不存在！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库查询出错！");
        }
        return customer;
    }

    public Customer findCustomerById(int id)
    {
        String sql="select * from Customers where customerId=?";
        QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
        Customer customer=null;
        try
        {
            customer=runner.query(sql,new BeanHandler<>(Customer.class),id);

        }catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("查无此顾客");
        }
        return customer;
    }
}
