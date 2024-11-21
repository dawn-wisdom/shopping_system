package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ViewTimeServlet")
public class ViewTimeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取ServletContext
        ServletContext context = getServletContext();

        // 从ServletContext中获取当前浏览量，初始化为0
        Integer viewCount = (Integer) context.getAttribute("viewCount");
        if (viewCount == null) {
            viewCount = 0;  // 初始值为0
        }

        // 更新浏览量
        viewCount++;
        context.setAttribute("viewCount", viewCount);

        // 转发到products.jsp
        String url="ShowAllProducts";
        response.sendRedirect(url);
    }
}

