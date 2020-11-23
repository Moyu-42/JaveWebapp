package service;
import java.util.*;

import bean.Database;
import bean.Person;
import dao.PersonOpt;
public class PersonService {
    private PersonOpt p;
    public PersonService(Database db) {
        p = new PersonOpt(db);
    }
    public int addPerson(Person person) {
        return p.insert(person);
    }
    public List<Person> getQuery() {
        return p.query();
    }
    public boolean search(Person person, int opt) { return p.search(person, opt); }
}
