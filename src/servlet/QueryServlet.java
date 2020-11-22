package servlet;
import bean.Database;
import bean.Person;
import bean.User;
import service.PersonService;
import service.UserService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="queryServlet",urlPatterns="/queryServlet")
public class QueryServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sc = request.getSession();
        Database db = (Database)sc.getAttribute("database");
        UserService user = new UserService(db);
        List<User> uList = user.getQuery();
        PersonService person = new PersonService(db);
        List<Person> pList = person.getQuery();

        request.setAttribute("uList", uList);
        request.setAttribute("pList", pList);
        request.getRequestDispatcher("/show_data.jsp").forward(request, response);
    }
}
