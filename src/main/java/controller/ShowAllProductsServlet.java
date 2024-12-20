package controller;

import entity.Product;
import service.ProductService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ShowAllProductsServlet")
public class ShowAllProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ProductService productService=new ProductService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 处理 GET 请求
        String searchKey="";
        searchKey=request.getParameter("keyword");
        List<Product> productList=null;
        if(searchKey==null)
        {
            productList=productService.listAll();
            if(productList!=null)
            {
                request.setAttribute("products", productList);
                request.getRequestDispatcher("products.jsp").forward(request, response);
            }
            else
            {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        else
        {
            productList=productService.findProductByName(searchKey);
            if(productList!=null)
            {
                request.setAttribute("products", productList);
                request.getRequestDispatcher("products.jsp").forward(request, response);
            }
            else
            {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

}