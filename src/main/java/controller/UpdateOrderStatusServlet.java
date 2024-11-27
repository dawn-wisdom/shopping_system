package controller;
import com.aliyun.oss.common.comm.ServiceClient;
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
import service.CustomerService;
@WebServlet("/UpdateOrderStatusServlet")
public class UpdateOrderStatusServlet extends HttpServlet {
    private OrderService orderService=new OrderService();
    private SendMailService sendMailService=new SendMailService();
    private CustomerService customerService=new CustomerService();
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


                request.getRequestDispatcher("afterPay.jsp").forward(request, response);// 转发

            }
            else if(newStatus==2)//确认发货
            {
                String url="OrderManage?request_type=1";
                //获取邮箱
                int customerId= Integer.parseInt(request.getParameter("customerId"));
                Customer customer= customerService.findCustomerById(customerId);
                System.out.println(customer.getEmail());
                if(customer!=null)
                    sendMailService.sendMail(customer.getEmail());
                response.sendRedirect(url);
            }
            else if(newStatus==3)//确认收货
            {
                sendMailService.sendMail2Seller(orderId);
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