package dao;
import java.util.*;

import bean.Database;
import bean.Person;

public class PersonOpt {
    private Database db;
    public PersonOpt(Database db) {
        this.db = db;
    }
    public void clean() {
        String sql = "drop table if exists person";
        db.update(sql);
        db.getLog(10);
    }
    public void create() {
        String sql = 
        "create table person" +
        "(Username varchar(10) not null, " + 
        "Name varchar(20) not null primary key, " +
        "Age int DEFAULT null, " + 
        "Teleno char(11) DEFAULT null, " +
        "CONSTRAINT username_cons FOREIGN KEY(Username) REFERENCES users(Username) " + 
        "ON DELETE CASCADE ON UPDATE RESTRICT)";
        db.update(sql);
        sql = "alter table person change Name Name varchar(255) character set utf8";
        db.update(sql);
        db.getLog(5);
    }
    public int insert(Person p) {
        String sql = "select * from person where Username = ?";
        List<Map<String, Object>> pList = new ArrayList<Map<String, Object>>();
        pList = db.query(sql, p.getUsername());
        if (pList.isEmpty()) {
            String sql_user = "select * from users where Username = ?";
            List<Map<String, Object>> uList = new ArrayList<Map<String, Object>>();
            uList = db.query(sql_user, p.getUsername());
            db.getLog(4);
            if (uList.isEmpty()) {
                String sql_user_insert = "insert into users(Username, Password) values(?, ?)";
                Object[] obj_user = {p.getUsername(), "888888"};
                db.update(sql_user_insert, obj_user);
                db.getLog(2);
                String sql_person_insert = "insert into person(Username, Name, Age, Teleno) values(?, ?, ?, ?)";
                Object[] obj_person = {p.getUsername(), p.getName(), p.getAge(), p.getTeleno()};
                db.update(sql_person_insert, obj_person);
            }else {
                String sql_person_insert = "insert into person(Username, Name, Age, Teleno) values(?, ?, ?, ?)";
                Object[] obj_person = {p.getUsername(), p.getName(), p.getAge(), p.getTeleno()};
                db.update(sql_person_insert, obj_person);
            }
        }else {
            String sql_ = "update person set Name = ?, Age = ?, Teleno = ? where Username = ?";
            Object[] obj = {p.getName(), p.getAge(), p.getTeleno(), p.getUsername()};
            db.update(sql_, obj);
        }
        db.getLog(6);
        return 1;
    }
    public void delete(Person p) {
        String sql;
        sql = "delete from users where Username like ?";
        db.update(sql, p.getName());
        db.getLog(7);
    }
    public List<Person> query() {
        String sql = "select * from person";
        List<Map<String, Object>> ret = db.query(sql);
        List<Person> ans = new ArrayList<Person>();
        for (Map<String, Object> map: ret) {
            Person person = new Person((String)map.get("Username"), (String)map.get("Name"), (Integer)map.get("Age"), (String)map.get("Teleno"));
            ans.add(person);
        }
        db.getLog(8);
        return ans;
    }
}
