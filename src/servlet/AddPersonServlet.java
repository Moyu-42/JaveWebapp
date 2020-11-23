package servlet;

import bean.Database;
import bean.Person;
import bean.User;
import com.alibaba.fastjson.JSONObject;
import service.PersonService;
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

@WebServlet(name = "addPersonServlet", urlPatterns = {"/addPersonServlet"})
public class AddPersonServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/jsp; charset=utf-8");

        HttpSession sc = request.getSession();
        Database db = (Database)sc.getAttribute("database");
        String type = request.getParameter("types");
        PersonService personService = new PersonService(db);
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

            Person person = new Person();
            person.setUsername(request.getParameter("username"));
            person.setName(request.getParameter("name"));
            String age = request.getParameter("age");
            Integer age_;
            if (age.isEmpty()) {
                age_ = null;
            }
            else age_ = Integer.parseInt(age);
            person.setAge(age_);
            person.setTeleno(request.getParameter("teleno"));

            int ret = personService.addPerson(person);
            if (ret > 0) {
                message = "成功插入person " + person.getUsername();
            }else {
                message = "Error!";
            }
            obean.put("message", message);
        }
        if ("search".equals(type)) {
            String show = "2";

            Person person = new Person();
            person.setName(request.getParameter("name"));
            person.setUsername(request.getParameter("username"));
            Boolean flag = personService.search(person, 0);
            Boolean flag1 = personService.search(person, 1);
            if (flag && !flag1) {
                show += "10";
                obean.put("message", "Name_exist");
            }
            else {
                if (flag1) {
                    show += "20";
                    obean.put("message", "Username_exist");
                }
                else {
                    show += "10";
                    obean.put("message", "false");
                }
            }
            List<String> l = (List)sc.getAttribute("list");
            show += person.getUsername();
            l.add(show);
            sc.setAttribute("list", l);
        }
        response.getWriter().write(obean.toString());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
