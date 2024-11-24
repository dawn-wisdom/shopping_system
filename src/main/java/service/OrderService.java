package service;


import dao.OrderDao;
import dao.OrderItemDao;
import dao.ProductDao;
import entity.Customer;
import entity.Order;
import entity.OrderItem;
import entity.Seller;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderDao orderDao=new OrderDao();
    private OrderItemDao orderItemDao=new OrderItemDao();
    private ProductDao productDao=new ProductDao();//修改产品表的数量
    public int createOrder(Order order) {
        int orderId=-1;
        try {
            // 1.开启事务
            DataSourceUtils.startTransaction();
            // 2.完成操作
            // 2.1向orders表中添加数据
            orderId=orderDao.createOrder(order);
            System.out.println(orderId);
            order.setOrderId(orderId);
            // 2.2向orderItem表中添加数据
            orderItemDao.addOrderItem(order);
            // 2.3修改商品表中数据.
            productDao.changeProductNum(order);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DataSourceUtils.rollback(); // 事务回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                // 关闭，释放以及提交事务
                DataSourceUtils.releaseAndCloseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orderId;
    }

    public List<Order> findAllOrder() {
        List<Order> orders = null;
        try {
            // 查找出订单信息
            orders = orderDao.findAllOrder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Order> findOrderByUser(Customer customer)
    {
        List<Order> orders=null;
        try{
            orders=orderDao.findOrderByUser(customer);
            for(Order order:orders)
            {
                List<OrderItem> ois=orderItemDao.findOrderItemsById(order.getOrderId());
                order.setOrderItems(ois);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public Order findOrderById(int orderId)
    {
        Order order=null;
        try {
            order = orderDao.findOrderById(orderId);
            List<OrderItem> items = orderItemDao.findOrderItemsById(orderId);
            order.setOrderItems(items);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public int updateStatus(int newStatus,int orderId)
    {
        int rowsAffected=0;
        rowsAffected=orderDao.updateStatus(newStatus,orderId);
        return rowsAffected;
    }

    //根据id删除订单 管理员删除订单
    public void deleteOrderById(int orderId) {
        try {
            DataSourceUtils.startTransaction();//开启事务
            orderItemDao.deleteOrderItems(orderId);
            orderDao.deleteOrderById(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DataSourceUtils.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            try {
                DataSourceUtils.releaseAndCloseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
