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

public class OrderItemDao {
    // 添加订单项
    public void addOrderItem(Order order) throws SQLException {
        // 1.生成sql语句
        String sql = "insert into orderItems(orderId, productId, quantity) values(?,?,?)";

        QueryRunner runner = new QueryRunner();

        List<OrderItem> items = order.getOrderItems();

        Object[][] params = new Object[items.size()][3];

        int orderId=order.getOrderId();
        for(int i=0;i<items.size();i++)
        {
            items.get(i).setOrderId(orderId);
        }

        for (int i = 0; i < params.length; i++) {
            params[i][0] = items.get(i).getOrderId();
            params[i][1] = items.get(i).getProductId();
            params[i][2] = items.get(i).getQuantity();
        }

        runner.batch(DataSourceUtils.getConnection(), sql, params);
    }

    //根据订单id删除订单项
    public void deleteOrderItems(int orderId) throws SQLException {
        String sql="delete from orderItems where orderId=?";

        QueryRunner runner=new QueryRunner();

        runner.update(DataSourceUtils.getConnection(),sql,orderId);
    }

    public List<OrderItem> findOrderItemsById(int orderId) throws SQLException {
        List<OrderItem> orderItems=null;
        String sql = "select * from orderItems,Products where products.productId=orderItems.productId and orderItems.orderId=?";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        return runner.query(sql, new ResultSetHandler<List<OrderItem>>() {
            public List<OrderItem> handle(ResultSet rs) throws SQLException {

                List<OrderItem> items = new ArrayList<OrderItem>();
                while (rs.next()) {
                    OrderItem item = new OrderItem();

                    item.setOrderId(orderId);
                    item.setQuantity(rs.getInt("quantity"));

                    Product p = new Product();
                    p.setCategory(rs.getString("category"));
                    p.setProductId(rs.getInt("productId"));
                    p.setDescription(rs.getString("description"));
                    p.setProductImage(rs.getString("productImage"));
                    p.setProductName(rs.getString("productName"));
                    p.setPnum(rs.getInt("pnum"));
                    p.setProductPrice(rs.getDouble("productPrice"));
                    item.setProduct(p);

                    items.add(item);
                }

                return items;
            }
        }, orderId);
    }

}
