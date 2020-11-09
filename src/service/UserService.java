package service;

import bean.*;
import dao.UserOpt;

import java.util.List;

public class UserService {
    private UserOpt u;
    public UserService(Database db) { u = new UserOpt(db); }
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
