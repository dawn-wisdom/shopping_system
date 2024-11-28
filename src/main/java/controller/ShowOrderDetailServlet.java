package controller;

import dao.OrderItemDao;
import entity.Order;
import service.OrderService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowOrderDetailServlet")
public class ShowOrderDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderService orderService=new OrderService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 处理 GET 请求
        int orderId=Integer.parseInt(request.getParameter("orderId"));
        Order order=orderService.findOrderById(orderId);
        request.setAttribute("order", order);
        request.getRequestDispatcher("showOrderDetail.jsp").forward(request,response);
    }


}

