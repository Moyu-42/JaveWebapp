package servlet;

import bean.Database;
import bean.User;
import com.alibaba.fastjson.JSONObject;
import service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "addUserServlet", urlPatterns = {"/addUserServlet"})
public class AddUserServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/jsp; charset=utf-8");

        HttpSession sc = request.getSession();
        Database db = (Database)sc.getAttribute("database");
        String type = request.getParameter("types");
        UserService userService = new UserService(db);
        JSONObject obean = new JSONObject();
        String message = "";
        if ("add".equals(type)) {
            User user = new User();
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));

            int ret = userService.addUser(user);
            if (ret > 0) {
                message = "Success insert user";
            }else {
                message = "Error!";
            }
            obean.put("message", message);
        }
        if ("search".equals(type)) {
            User user = new User();
            user.setUsername(request.getParameter("username"));

            boolean flag = userService.search(user);
            if (flag) {
                obean.put("message", "true");
            }else {
                obean.put("message", "false");
            }
        }
        response.getWriter().write(obean.toString());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
