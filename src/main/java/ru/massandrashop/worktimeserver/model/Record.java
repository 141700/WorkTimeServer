package ru.massandrashop.worktimeserver.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "wt_records")
public class Record implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "office_id")
    private Office office;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDateTime timeStamp;

    public Record(Office office, Employee employee) {
        this.office = office;
        this.employee = employee;
        this.timeStamp = LocalDateTime.now();
    }

}