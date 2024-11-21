package service;

import dao.LogDao;
import entity.Order;
import entity.OrderItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogService {
    private LogDao logDao=new LogDao();

    public List<Order> getPurchaseLog(boolean flag)
    {
        List<Order> orders=null;
        try{
            orders=logDao.getPurchaseLog(flag);
            for(Order order:orders)
            {
                List<OrderItem> ois=logDao.getPurchaseProductDao(order.getOrderId());
                order.setOrderItems(ois);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
