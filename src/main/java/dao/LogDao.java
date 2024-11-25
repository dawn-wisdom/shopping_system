package dao;


import entity.Order;
import entity.OrderItem;
import entity.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.DataSourceUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogDao {
    public List<Order> getPurchaseLog(boolean flag) throws SQLException {
        List<Order> purchaseRecord=null;
        String querySql;
        if(flag)
        {
            querySql=" SELECT o.orderId,o.customerId,o.order_time,o.total_cost,o.status  " +
                    " FROM orders o " +
                    "where status>0 " +
                    " order by Order_time ";
        }
        else
        {
            querySql=" SELECT o.orderId,o.customerId,o.order_time,o.total_cost,o.status  " +
                    " FROM orders o " +
                    "where status>0 " +
                    " order by Order_time "+
                    "LIMIT 20";
        }
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());

        return queryRunner.query(querySql, new ResultSetHandler<List<Order>>() {
            public List<Order> handle(ResultSet rs) throws SQLException {
                List<Order> orders = new ArrayList<Order>();
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("orderId"));
                    order.setCustomerId(rs.getInt("customerId"));
                    order.setTotal_cost(rs.getDouble("total_cost"));
                    order.setOrder_time(rs.getDate("Order_time"));
                    order.setStatus(rs.getInt("status"));
                    orders.add(order);
                }
                return orders;
            }
        });
    }

    public List<OrderItem> getPurchaseProductDao(int orderId) throws SQLException {
        List<OrderItem> orderItems=null;
        String sql = " SELECT oi.orderId,p.ProductId,p.productName,p.productImage,oi.quantity " +
                " FROM products p,orderitems oi " +
                " WHERE p.productId=oi.productId  and oi.orderId=?" ;

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        return runner.query(sql, new ResultSetHandler<List<OrderItem>>() {
            public List<OrderItem> handle(ResultSet rs) throws SQLException {

                List<OrderItem> items = new ArrayList<OrderItem>();
                while (rs.next()) {
                    OrderItem item = new OrderItem();

                    item.setOrderId(orderId);
                    item.setQuantity(rs.getInt("quantity"));

                    Product p = new Product();
                    p.setProductId(rs.getInt("productId"));
                    p.setProductName(rs.getString("productName"));
                    p.setProductImage(rs.getString("productImage"));
                    item.setProduct(p);
                    items.add(item);
                }

                return items;
            }
        }, orderId);
    }
}
