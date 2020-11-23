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
import java.util.List;

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
            List<String> l = (List)sc.getAttribute("list");
            String show = l.get(l.size() - 1);
            l.remove(l.size() - 1);
            StringBuilder sb = new StringBuilder(show);
            sb.replace(2, 3, "1");
            show = sb.toString();
            l.add(show);
            sc.setAttribute("list", l);

            User user = new User();
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));

            int ret = userService.addUser(user);
            if (ret > 0) {
                message = "成功插入user " + user.getUsername();
            }else {
                message = "Error!";
            }
            obean.put("message", message);
        }
        if ("search".equals(type)) {
            String show = "1";
            User user = new User();
            user.setUsername(request.getParameter("username"));

            boolean flag = userService.search(user);
            if (flag) {
                obean.put("message", "true");
                show += "20";
            }else {
                obean.put("message", "false");
                show += "10";
            }
            show += user.getUsername();
            List<String> l = (List)sc.getAttribute("list");
            l.add(show);
            sc.setAttribute("list", l);
        }
        response.getWriter().write(obean.toString());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
