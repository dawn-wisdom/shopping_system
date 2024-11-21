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
		/**
		 * 1.获取参数
		 * request.getParameter("userName");这里面的参数username就是前端<input>标签中的name,切勿写错
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

				/*
				 * 创建一个session用来存储user
				 * session是javaweb中一个负责存储数据的，相当于你的钥匙包，此时的user便是其中的一个钥匙，
				 * 将user存入包内，需要的时候在取出来，在前端jsp页面非常好用，不明白的同学可以去学一下这里就不赘述了
				 * */
				HttpSession session = request.getSession();
				session.setAttribute("customer", customer);

				// 登录成功后，通过转发跳转到新的界面，当然也可以使用重定向，我们这里为了简单采用转发,若是想转发其他界面只要修改loginSuccess.jsp即可
				String url="ShowAllProducts";
				response.sendRedirect(url);
			} else {
				// user为空，代表用户不存在
				System.out.println("登陆失败");
				// 登录失败通过转发，在回到登录界面继续进行登录
				request.getRequestDispatcher("wrong_pwd.jsp").forward(request, response);// 转发
			}
		}
		else if(role.equals("seller"))
		{
			Seller seller = sellerService.login(username,password);

			if (seller !=null) {
				// 用户不为空，代表用户存在，成功登录
				System.out.println("登录成功");

				/*
				 * 创建一个session用来存储user
				 * session是javaweb中一个负责存储数据的，相当于你的钥匙包，此时的user便是其中的一个钥匙，
				 * 将user存入包内，需要的时候在取出来，在前端jsp页面非常好用，不明白的同学可以去学一下这里就不赘述了
				 * */
				HttpSession session = request.getSession();
				session.setAttribute("seller", seller);

				// 登录成功后，通过转发跳转到新的界面，当然也可以使用重定向，我们这里为了简单采用转发,若是想转发其他界面只要修改loginSuccess.jsp即可
				request.getRequestDispatcher("productsInStore.jsp").forward(request, response);// 转发
			} else {
				// user为空，代表用户不存在
				System.out.println("登陆失败");
				// 登录失败通过转发，在回到登录界面继续进行登录
				request.getRequestDispatcher("index.jsp").forward(request, response);// 转发
			}
		}
		
	}

}

