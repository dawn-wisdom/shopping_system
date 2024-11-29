package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Customer;
import entity.Seller;
import service.CustomerService;
import service.SellerService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 创建UserService对象
	private CustomerService customerService = new CustomerService();
	private SellerService sellerService=new SellerService();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 1. 获取参数 2. 验证用户 3. 返回结果
		 */
		String role=request.getParameter("role");
		String username = request.getParameter("userName");
		String password = request.getParameter("pwdName");
		
		// 调用userService对象中的login方法验证登录用户
		if(role.equals("customer"))
		{
			Customer customer = customerService.login(username,password);

			if (customer !=null) {
				// 用户不为空，代表用户存在，成功登录
				System.out.println("登录成功");

				HttpSession session = request.getSession();
				session.setAttribute("customer", customer);

				String url="ShowAllProducts";
				response.sendRedirect(url);
			} else {
				// user为空，代表用户不存在
				System.out.println("登陆失败");
				request.setAttribute("errorMessage", "账号或密码错误，请重试！");
				// 登录失败通过转发，在回到登录界面继续进行登录
				request.getRequestDispatcher("login.jsp").forward(request, response);// 转发
			}
		}
		else if(role.equals("seller"))
		{
			Seller seller = sellerService.login(username,password);

			if (seller !=null) {
				// 用户不为空，代表用户存在，成功登录
				System.out.println("登录成功");
				HttpSession session = request.getSession();
				session.setAttribute("seller", seller);

				String url="ShowProductInStore";
				response.sendRedirect(url);

			} else {
				System.out.println("登陆失败");
				request.setAttribute("errorMessage", "账号或密码错误，请重试！");
				// 登录失败通过转发，在回到登录界面继续进行登录
				request.getRequestDispatcher("login.jsp").forward(request, response);// 转发
			}
		}
		
	}

}

