package me.zuif.module3.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Grade {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @ManyToMany(cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<Student> students;
    @OneToOne
    private Subject subject;
    private int value;
}
