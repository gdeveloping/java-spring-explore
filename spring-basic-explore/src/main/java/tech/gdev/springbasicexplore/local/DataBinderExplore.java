package tech.gdev.springbasicexplore.local;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;

import java.util.List;

/**
 * @author gdev
 * @date 2025/4/9 22:49
 */
public class DataBinderExplore {
    public static void main(String[] args) throws BindException {
        Person person = new Person();
        DataBinder binder = new DataBinder(person, "person");
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("pets[0].toys[0].toyName", "t1");
        pvs.add("pets[0].petName","p1");
        pvs.add("personName", "Jack");
        pvs.add("age", 18);
        binder.bind(pvs);
        System.out.println(person);
    }
}

@Setter
@Getter
@ToString
class Person {
    String personName;
    int age;
    List<Pet> pets;
}

@Setter
@Getter
@ToString
class Pet {
    String petName;
    List<Toy> toys;
}

@Setter
@Getter
@ToString
class Toy {
    String toyName;
}
