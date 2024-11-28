package controller;

import entity.Order;
import service.OrderService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderService orderService=new OrderService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 处理 GET 请求
        int orderId=Integer.parseInt(request.getParameter("orderId"));
        int request_type=Integer.parseInt(request.getParameter("request_type"));
        orderService.deleteOrderById(orderId);
        if(request_type==0)
            response.sendRedirect("OrderManage?request_type=0");
        else
            response.sendRedirect("OrderManage?request_type=1");
    }

}

