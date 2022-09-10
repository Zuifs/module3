package me.zuif.module3.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Group {
    String name;
    @OneToMany(cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    List<Student> students;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
}
