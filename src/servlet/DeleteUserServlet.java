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
import java.io.IOException;
@WebServlet(name="deleteUserServlet",urlPatterns="/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/jsp; charset=utf-8");

        ServletContext sc = getServletConfig().getServletContext();
        Database db = (Database)sc.getAttribute("database");
        String type = request.getParameter("types");
        UserService userService = new UserService(db);
        JSONObject obean = new JSONObject();
        String message = "";
        if ("delete".equals(type)) {
            User user = new User();
            user.setUsername(request.getParameter("username"));

            int ret = userService.delUser(user);
            if (ret > 0) {
                message = "Delete user success!";
            }
            else message = "Error!";
            obean.put("message", message);
        }
        if ("search".equals(type)) {
            User user = new User();
            user.setUsername(request.getParameter("username_user_del"));

            boolean flag = userService.search(user);
            if (flag) {
                obean.put("valid", true);
            }else {
                obean.put("valid", false);
            }
        }
        response.getWriter().write(obean.toString());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
