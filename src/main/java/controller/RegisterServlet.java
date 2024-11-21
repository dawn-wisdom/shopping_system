package controller;

import entity.Customer;
import entity.Seller;
import service.CustomerService;
import service.SellerService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 创建UserService对象
	private CustomerService customerService = new CustomerService();
	private SellerService sellerService=new SellerService();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *  1.获取参数
		 *  2.调用service处理
		 *  3.返回结果 
		 */
		
		// 获取前端输入的两次密码
		String userName= request.getParameter("userName");
		String pwd1 = request.getParameter("pwdName1");
		String pwd2 = request.getParameter("pwdName2");
		String email= request.getParameter("emailName");
		String phone= request.getParameter("phoneName");
		String role=request.getParameter("role");
		boolean rel=false;
		if(role.equals("customer"))
		{
			if(pwd1.equals(pwd2)){
				// 判断密码输入是否相同
				// 根据user里的内容进行注册
				try {
					rel=customerService.register(userName,pwd1,email,phone);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}else {
				// 两次输入密码不同
				System.out.println("两次密码不同，注册失败");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		}
		else if(role.equals("seller"))
		{
			if(pwd1.equals(pwd2)){
				// 判断密码输入是否相同
				// 根据user里的内容进行注册
				try {
					String storeName=request.getParameter("storeName");
					rel=sellerService.register(userName,storeName,pwd1,email,phone);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}else {
				// 两次输入密码不同
				System.out.println("两次密码不同，注册失败");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		}

	}
	
}

