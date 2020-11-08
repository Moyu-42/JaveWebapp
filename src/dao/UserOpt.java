package dao;
import java.util.*;

import bean.Database;
import bean.User;

public class UserOpt {
    private Database db;
    public UserOpt(Database db) {
        this.db = db;
    }
    public void clean() {
        String sql = "drop table if exists users";
        db.update(sql);
        db.getLog(9);
    }

    public void create() {
        String sql = 
        "create table users(Username varchar(10) not null primary key, " + 
        "Password varchar(8) not null)";
        db.update(sql);
        db.getLog(1);
    }

    public int insert(User user) {
        String sql = "select * from users where Username = ?";
        List<Map<String, Object>> uList = new ArrayList<Map<String, Object>>();
        uList = db.query(sql, user.getUsername());
        db.getLog(4);
        if (uList.isEmpty()) {
            sql = "insert into users(Username, Password) values(?, ?)";
            Object[] obj = { user.getUsername(), user.getPassword() };
            db.update(sql, obj);
        }
        else {
            sql = "update users set Password = ? where Username = ?";
            Object[] obj = { user.getPassword(), user.getUsername() };
            db.update(sql, obj);
        }
        db.getLog(2);
        return 1;
    }

    public int delete(User user) {
        String sql;
        sql = "delete from users where Username like ?";
        db.update(sql, user.getUsername());
        db.getLog(3);
        return 1;
    }

    public List<User> query() {
        String sql = "select * from users";
        List<Map<String, Object>> ret = db.query(sql);
        List<User> ans = new ArrayList<User>();
        for (Map<String, Object> map : ret) {
            User user = new User((String) map.get("Username"), (String) map.get("Password"));
            ans.add(user);
        }
        db.getLog(4);
        return ans;
    }

    public boolean search(User user) {
        String sql = "select * from users where Username = ?";
        List<Map<String, Object>> uList = new ArrayList<>();
        uList = db.query(sql, user.getUsername());
        db.getLog(4);
        if (uList.isEmpty()) {
            return false;
        }
        else return true;
    }
}
