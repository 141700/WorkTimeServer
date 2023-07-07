package ru.massandrashop.worktimeserver.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of = {"macAddress"})
@NoArgsConstructor

@Entity
@Table(name = "points")
public class Point implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String macAddress;

    @NotNull
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    private IdTypes type;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "office_id")
    private Office office;

    public enum IdTypes {WIFI, BLUETOOTH}

    public Point(String title, String macAddress, Office office, IdTypes type) {
        this.title = title;
        this.macAddress = macAddress;
        this.office = office;
        this.type = type;

    }

}
