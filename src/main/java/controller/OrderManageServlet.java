package controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Customer;
import entity.Order;
import entity.Seller;
import service.OrderService;
//查找所有订单
@WebServlet("/OrderManageServlet")
public class OrderManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取当前对象

        // 创建Service层对象
        OrderService service = new OrderService();
        //区分请求类型
        int request_type=Integer.parseInt(request.getParameter("request_type"));
        if(request_type==0)
        {
            HttpSession session=request.getSession();
            Customer customer=(Customer) session.getAttribute("customer");
            if (customer == null) {
                // 未登录的处理
                request.setAttribute("errorMessage", "您还没有登录");
                // 如果 referer 存在，则重定向回来源页面
                request.getRequestDispatcher("ShowAllProducts").forward(request,response);
                return;
            }

            List<Order> orders=service.findOrderByUser(customer);
            request.setAttribute("orders",orders);
            request.getRequestDispatcher("orderHistory.jsp").forward(request,response);
        }
        else if(request_type==1)
        {
            // 调用Service层对象的findAllOrder()方法查询订单列表
            List<Order> orders = service.findAllOrder();
            //将查询到的订单信息添加到request作用域
            request.setAttribute("orders", orders);
            // 将请求转发到list.jsp页面
            request.getRequestDispatcher("orderManage.jsp").forward(request,response);
        }

    }
}
