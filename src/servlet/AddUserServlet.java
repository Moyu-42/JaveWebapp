package servlet;

import bean.User;
import com.alibaba.fastjson.JSONObject;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addUserServlet", urlPatterns = {"/addUserServlet"})
public class AddUserServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/jsp; charset=utf-8");

        String type = request.getParameter("types");
        UserService userService = new UserService();
        JSONObject obean = new JSONObject();
        String message = "";
        if ("add".equals(type)) {
            User user = new User();
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));

            int row = userService.addUser(user);
            if (row > 0) {
                message = "Success insert user";
            }else {
                message = "Error!";
            }
            obean.put("message", message);
            response.getWriter().write(obean.toString());
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
