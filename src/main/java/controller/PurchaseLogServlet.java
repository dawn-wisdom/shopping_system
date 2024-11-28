package controller;

import entity.Order;
import service.LogService;
import service.OrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PurchaseLogServlet")
public class PurchaseLogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LogService logService=new LogService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> logs = new ArrayList<>();
        logs=logService.getPurchaseLog(false);//是否全部导出
        request.setAttribute("purchaseLogs", logs);
        request.getRequestDispatcher("/purchaseLog.jsp").forward(request, response);
    }

}

