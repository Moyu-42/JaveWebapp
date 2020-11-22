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
                message = "Success insert person";
            }else {
                message = "Error!";
            }
            obean.put("message", message);
        }
        if ("search".equals(type)) {
            Person person = new Person();
            person.setUsername(request.getParameter("name"));

            Boolean flag = personService.search(person);
            if (flag) {
                obean.put("message", "true");
            }
            else obean.put("message", "false");
        }
        response.getWriter().write(obean.toString());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
