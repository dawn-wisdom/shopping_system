package controller;
import entity.Product;
import service.ProductSaledService;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/ProductsSaledServlet")
public class ProductsSaledServlet extends HttpServlet{
    private ProductSaledService productSaledService = new ProductSaledService();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Product, Integer> leaderboard = null;
        try {
            leaderboard = productSaledService.getSalesLeaderboard();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("size:"+leaderboard.size());
        //request.setAttribute("leaderboard", leaderboard);
        // 获取当前页码，默认为第一页
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }

        // 每页显示的商品数
        int pageSize = 5;

        // 将 Map 转换为 List，并根据分页来切分数据
        List<Map.Entry<Product, Integer>> leaderboardList = new ArrayList<>(leaderboard.entrySet());

        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, leaderboardList.size());

        List<Map.Entry<Product, Integer>> pageData = leaderboardList.subList(startIndex, endIndex);

        // 设置分页数据到请求属性
        request.setAttribute("leaderboard", pageData);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", (int) Math.ceil((double) leaderboardList.size() / pageSize));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ranking_list.jsp");
        dispatcher.forward(request, response);
    }

}
