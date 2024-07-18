package org.example.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plan")
public class Plan {
    @Column(name = "name")
    @NotBlank(message = "Заполните поле")
    private String name;
    @Column
    @NotNull
    private String description;
    @Column
    @NotNull
    private int rate;
    @Column
    @NotNull
    private LocalDateTime time;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private PlanCategory planCategory;
    @Column
    private boolean completed = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
