package org.example.repository;

import org.example.model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Long> {
    @Autowired
    List<Plan> findByNameContainingOrDescriptionContaining(Plan plan, String description);
}
