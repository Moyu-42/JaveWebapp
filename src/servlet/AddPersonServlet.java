package servlet;

import bean.Person;
import bean.User;
import com.alibaba.fastjson.JSONObject;
import service.PersonService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "addPersonServlet", urlPatterns = {"/addPersonServlet"})
public class AddPersonServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/jsp; charset=utf-8");

        String type = request.getParameter("types");
        PersonService personService = new PersonService();
        JSONObject obean = new JSONObject();
        String message = "";
        if ("add".equals(type)) {
            Person person = new Person();
            person.setUsername(request.getParameter("username"));
            person.setName(request.getParameter("name"));
            person.setAge(Integer.parseInt(request.getParameter("age")));
            person.setTeleno(request.getParameter("teleno"));

            int row = personService.addPerson(person);
            if (row > 0) {
                message = "Success insert person";
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
