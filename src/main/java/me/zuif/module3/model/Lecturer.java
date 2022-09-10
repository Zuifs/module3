package me.zuif.module3.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class Lecturer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    @OneToOne(/*mappedBy = "subject_id",*/ cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private Subject subject;
}
