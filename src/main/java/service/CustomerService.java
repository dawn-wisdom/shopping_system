package service;

import java.sql.SQLException;

import dao.CustomerDao;
import entity.Customer;

/**
 * 业务层处理
 * */
public class CustomerService {
    // 创建UserDao对象
    private CustomerDao customerDao = new CustomerDao();

    /**
     * 登录操作
     * @param customerName
     * @param password
     * @return rel
     */
    public Customer login(String customerName, String password) {
        // 声明一个用户实体类，用于接收返回的user
        Customer customer=null;
        // 调用userDao中方法根据用户名查询是否存在该用户
        customer = customerDao.login(customerName, password);

        return customer;
    }

    public boolean register(String customerName,String password,String email,String phone) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setPhone(phone);
        return customerDao.register(customer);
    }

}