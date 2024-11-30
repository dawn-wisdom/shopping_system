package controller;
import entity.Customer;
import entity.Product;


import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import service.ProductService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ShowProductDetailServlet")
public class ShowProductDetailServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    ProductService productService=new ProductService();
    private static final Logger logger = LogManager.getLogger(ShowProductDetailServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // TODO: 处理 GET 请求
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = productService.findProductById(productId);
        HttpSession session=request.getSession();
        Customer customer=(Customer) session.getAttribute("customer");
        // 记录访问日志
        logger.info("User-{} is trying to view product with ID: {}", customer.getCustomerId(), productId);

        if(product!=null)
        {
            logger.info("Product found: {}", productId);
            request.setAttribute("selected_product", product);
            request.getRequestDispatcher("product_detail.jsp").forward(request, response);
        }
        else
        {
            logger.warn("Product with ID {} not found.", productId);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}
