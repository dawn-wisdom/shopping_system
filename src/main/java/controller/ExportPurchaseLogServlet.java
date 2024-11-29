package controller;

import entity.Order;
import entity.OrderItem;
import service.LogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/ExportPurchaseLogServlet")
public class ExportPurchaseLogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final LogService logService = new LogService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应头和字符编码
        response.setContentType("text/csv;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=purchase_log.csv");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = response.getWriter()) {
            // 写入 UTF-8 BOM，确保兼容性
            writer.write('\uFEFF');
            // 获取数据
            List<Order> logs = logService.getPurchaseLog(false);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 写入 CSV 文件头部
            writer.println("订单号,用户ID,订单时间,商品,总价,订单状态");
            String[] statusStr = new String[]{"待支付", "待发货", "待收货", "已送达"};
            // 遍历订单，写入内容
            for (Order order : logs) {
                String orderTime = sdf.format(order.getOrder_time());
                String productStr = "\"" + order.getOrderItems().stream()
                        .map(item -> item.getProduct().getProductName() + "*" + item.getQuantity())
                        .collect(Collectors.joining(", ")) + "\"";

                writer.printf("%d,%d,%s,%s,%.2f,%s%n",
                        order.getOrderId(),
                        order.getCustomerId(),
                        orderTime,
                        productStr,
                        order.getTotal_cost(),
                        statusStr[order.getStatus()]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "导出失败，请重试！");
        }
    }

}