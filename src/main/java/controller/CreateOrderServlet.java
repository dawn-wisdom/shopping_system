package controller;

import entity.Customer;
import entity.Order;
import entity.OrderItem;
import entity.Product;
import service.OrderService;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
@WebServlet("/CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 处理 GET 请求
        //获取所有的商品
        HttpSession session = request.getSession();
       Customer customer = (Customer) session.getAttribute("customer");
        // 2.从购物车中获取商品信息
        Map<Product, Integer> buy = (Map<Product, Integer>)session.getAttribute("product2Buy");
        // 3.将数据封装到订单对象中
        Order order = new Order();
        order.setCustomerId(customer.getCustomerId());
        String total_cost_str = request.getParameter("money");
        double total_cost=0;
        if (total_cost_str != null) {
            // 转换为数字类型
            total_cost=Double.parseDouble(total_cost_str);
            order.setTotal_cost(Double.parseDouble(total_cost_str));
        }
        order.setStatus(0);
        Date today = new Date();
        order.setOrder_time(today);//获取当前日期
        order.setReceive_address(request.getParameter("receive_address"));
        order.setReceive_phone(request.getParameter("receive_phone"));
        order.setReceive_name(request.getParameter("receive_name"));

        for (Product p : buy.keySet()) {
            OrderItem item = new OrderItem();
            //item.setOrderId(order.getOrderId());
            item.setQuantity(buy.get(p));
            item.setProductId(p.getProductId());
            order.getOrderItems().add(item);
        }
        // 4.调用service中添加订单操作.
        OrderService service = new OrderService();
        int orderId=service.createOrder(order);
        session.removeAttribute("product2Buy");
        System.out.println("创建订单成功");
        //重定向
        response.sendRedirect(request.getContextPath() + "/payment.jsp?orderId=" + orderId + "&total_cost=" + total_cost);
    }

}