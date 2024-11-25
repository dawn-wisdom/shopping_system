package controller;
import entity.Customer;
import service.OrderService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.SendMailService;

@WebServlet("/UpdateOrderStatusServlet")
public class UpdateOrderStatusServlet extends HttpServlet {
    private OrderService orderService=new OrderService();
    private SendMailService sendMailService=new SendMailService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置字符编码
        request.setCharacterEncoding("UTF-8");

        // 获取请求参数
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int newStatus = Integer.parseInt(request.getParameter("newStatus"));

        // 执行更新操作
        int rowsUpdated = orderService.updateStatus(newStatus,orderId);

        // 返回结果
        if (rowsUpdated > 0) {
            if(newStatus==1)//确认支付
            {
                //获取邮箱
                HttpSession session = request.getSession();
                Customer customer=(Customer) session.getAttribute("customer");
                sendMailService.sendMail(customer.getEmail());
                request.getRequestDispatcher("afterPay.jsp").forward(request, response);// 转发

            }
            else if(newStatus==2)//确认发货
            {
                String url="OrderManage?request_type=1";
                response.sendRedirect(url);
            }
            else if(newStatus==3)//确认收货
            {
                String url="OrderManage?request_type=0";
                response.sendRedirect(url);
            }
        } else {
            response.getWriter().write("failure");
        }
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws  ServletException,IOException{
        doPost(request,response);
    }
}