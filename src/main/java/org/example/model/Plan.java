package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plan")
public class Plan {
    @Column(name = "name")
    @NotNull
    private String name;
    @Column
    @NotNull
    private String description;
    @Column
    @NotNull
    private int rate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


}
