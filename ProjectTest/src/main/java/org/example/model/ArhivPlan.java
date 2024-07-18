package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "arhivPlan")
public class ArhivPlan {

    @Column(name="name")
    private String name;

    @Column
    private String description;

    @Column
    private LocalDateTime time;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int rate;
    @Column
    private PlanCategory planCategory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
