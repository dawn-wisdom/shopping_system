package dao;

import entity.Customer;
import entity.Order;
import entity.Seller;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public int createOrder(Order order) throws SQLException {
        String sql = "insert into orders(customerId, total_cost, Order_time, receive_name, receive_phone, receive_address) values(?,?,?,?,?,?)";

        // 2. 生成QueryRunner实例
        QueryRunner runner = new QueryRunner();

        // 3. 使用insert()方法插入数据并获取生成的orderId
        Object[] params = { order.getCustomerId(), order.getTotal_cost(), order.getOrder_time(),
                order.getReceive_name(), order.getReceive_phone(), order.getReceive_address() };

        // 执行插入操作并返回自动生成的主键（orderId）
        Number generatedKey = runner.insert(DataSourceUtils.getConnection(), sql, new ScalarHandler<>(), params);

        // 返回生成的主键（orderId）
        return generatedKey.intValue();  // 将生成的主键转换为int类型
    }

    //按用户显示信息
    public List<Order> findOrderByUser(final Customer customer) throws SQLException {
        String sql = "select * from orders where customerId=? order by order_time desc";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new ResultSetHandler<List<Order>>() {
            public List<Order> handle(ResultSet rs) throws SQLException {
                List<Order> orders = new ArrayList<Order>();
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("orderId"));
                    order.setCustomerId(rs.getInt("customerId"));
                    order.setTotal_cost(rs.getDouble("total_cost"));
                    order.setOrder_time(rs.getDate("Order_time"));
                    order.setStatus(rs.getInt("status"));
                    order.setReceive_address(rs.getString("receive_address"));
                    order.setReceive_name(rs.getString("receive_name"));
                    order.setReceive_phone(rs.getString("receive_phone"));

                    orders.add(order);
                }
                return orders;
            }
        }, customer.getCustomerId());
    }

    //查询所有的订单
    public List<Order> findAllOrder() throws  SQLException{
        String sql = "select orders.*,customers.* from orders,customers where customers.customerId=orders.customerId order by orders.order_time desc";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new ResultSetHandler<List<Order>>() {
            public List<Order> handle(ResultSet rs) throws SQLException {
                List<Order> orders = new ArrayList<Order>();
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("orderId"));
                    order.setCustomerId(rs.getInt("customerId"));
                    order.setTotal_cost(rs.getDouble("total_cost"));
                    order.setOrder_time(rs.getDate("Order_time"));
                    order.setStatus(rs.getInt("status"));
                    order.setReceive_address(rs.getString("receive_address"));
                    order.setReceive_name(rs.getString("receive_name"));
                    order.setReceive_phone(rs.getString("receive_phone"));

                    orders.add(order);
                }
                return orders;
            }
        });
    }

    //根据id查询订单
    public Order findOrderById(int orderId) throws SQLException {
        String sql = "select * from orders,customers where orders.customerId=customers.customerId and orders.orderId=? ";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new ResultSetHandler<Order>() {
            public Order handle(ResultSet rs) throws SQLException {
                Order order = new Order();
                while (rs.next()) {
                    order.setOrderId(rs.getInt("orderId"));
                    order.setCustomerId(rs.getInt("customerId"));
                    order.setTotal_cost(rs.getDouble("total_cost"));
                    order.setOrder_time(rs.getDate("Order_time"));
                    order.setStatus(rs.getInt("status"));
                    order.setReceive_address(rs.getString("receive_address"));
                    order.setReceive_name(rs.getString("receive_name"));
                    order.setReceive_phone(rs.getString("receive_phone"));

                    Customer customer = new Customer();
                    customer.setCustomerId(rs.getInt("customers.customerId"));
                    customer.setCustomerName(rs.getString("customers.customerName"));
                    customer.setPassword(rs.getString("customers.password"));
                    customer.setPhone(rs.getString("customers.phone"));
                    customer.setEmail(rs.getString("customers.email"));
                    order.setCustomer(customer);
                }
                return order;
            }
        }, orderId);
    }
    //更新订单状态
    public int updateStatus(int newStatus,int orderId)
    {
        String sql="update orders set status=? where orderId=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return runner.update(sql,newStatus,orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //根据id删除订单
    public void deleteOrderById(int  orderId) throws SQLException {
        String sql="delete from orders where orderId=?";
        QueryRunner runner = new QueryRunner();
        runner.update(DataSourceUtils.getConnection(),sql,orderId);
    }
}
