package controller;
import entity.Product;
import service.ProductService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ShowProductDetailServlet")
public class ShowProductDetailServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    ProductService productService=new ProductService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 处理 GET 请求
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = productService.findProductById(productId);
        if(product!=null)
        {
            request.setAttribute("selected_product", product);
            request.getRequestDispatcher("product_detail.jsp").forward(request, response);
        }
        else
        {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }


}
