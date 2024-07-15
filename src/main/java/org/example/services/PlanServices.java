package org.example.services;

import org.example.model.Plan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlanServices {
    void save(Plan plan);

    Optional<Plan> findById(long id);

    List<Plan> lookAll();

    void update(Plan plan, long id);

    void delete(long id);
}
