package ru.liga.intership.badcode.service;


import ru.liga.intership.badcode.domain.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonService {


    /**
     * Возвращает средний индекс массы тела всех лиц мужского пола старше 18 лет
     *
     * @return
     */
    public double getAdultMaleUsersAverageBMI() {
        double totalImt = 0.0;
        long countOfPerson;
        List<Person> adultPersons = getPersonList("SELECT * FROM person WHERE sex = 'male' AND age >= 18");
        for (Person p : adultPersons) {
            double heightInMeters = p.getHeight() / 100d;
            double imt = p.getWeight() / (Double) (heightInMeters * heightInMeters);
            totalImt += imt;
            System.out.println(p.getBmi());
        }
        countOfPerson = adultPersons.size();
        System.out.println("Average imt - " + totalImt / countOfPerson);
        System.out.println(countOfPerson);
        return totalImt / countOfPerson;
    }

    public List<Person> getPersonList(String query) {
        List<Person> adultPersons = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:test", "sa", "");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
            Person p = new Person(rs.getLong("id"), rs.getString("sex"),
                    rs.getString("name"), rs.getLong("age"),
                    rs.getLong("weight"), rs.getLong("height"));
            adultPersons.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adultPersons;
    }

}
