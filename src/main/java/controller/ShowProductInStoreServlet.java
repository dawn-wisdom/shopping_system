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

@WebServlet("/ShowProductInStoreServlet")
public class ShowProductInStoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ProductService productService=new ProductService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 处理 GET 请求
        List<Product> productList=null;
        String store="天天特卖工厂";
        productList=productService.listAll();
        if(productList!=null)
        {
            request.setAttribute("productsInStore" , productList);
            System.out.println(productList.size());
            request.getRequestDispatcher("productsInStore.jsp").forward(request, response);
        }
        else
        {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 处理 POST 请求
        doGet(request,response);
    }
}
