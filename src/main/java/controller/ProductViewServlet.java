package controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

@WebServlet("/ProductViewServlet")
public class ProductViewServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html; charset=UTF-8");
        // 获取文件的路径
        String filePath = "view.log";
        // 创建 FileReader 和 BufferedReader 来读取文件内容
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            // 读取文件内容
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append("<br>");  // 将每一行添加到 StringBuilder 并加上 <br> 标签
            }

            // 输出文件内容到网页
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>文件内容：</h2>");
            out.println(fileContent.toString());  // 输出文件内容
            out.println("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().println("文件读取失败！");
        }
    }
}
