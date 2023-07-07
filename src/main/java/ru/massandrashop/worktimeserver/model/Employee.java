package ru.massandrashop.worktimeserver.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String deviceId;

    public Employee(String name, String deviceId) {
        this.name = name;
        this.deviceId = deviceId;
    }

}