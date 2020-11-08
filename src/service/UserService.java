package service;

import bean.*;
import dao.UserOpt;

import java.util.List;

public class UserService {
    private Database db = new Database();
    private UserOpt u;
    public UserService() {
        u = new UserOpt(db);
    }
    public int addUser(User user) {
        return u.insert(user);
    }
    public int delUser(User user) {
        return u.delete(user);
    }
    public List<User> getQuery() {
        return u.query();
    }
    public boolean search(User user) { return u.search(user); }
}
