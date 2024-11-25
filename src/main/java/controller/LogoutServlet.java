package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前会话
        HttpSession session = request.getSession(false); // 获取当前会话，如果没有则返回 null

        if (session != null) {
            // 销毁会话
            session.invalidate();
        }

        // 重定向到登录页面
        response.sendRedirect("login.jsp");
    }
}
