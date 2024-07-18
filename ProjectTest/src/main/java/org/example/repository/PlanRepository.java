package org.example.repository;

import org.example.model.Plan;
import org.example.model.User;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Long> {
    @Query(value = "SELECT p FROM Plan p WHERE (p.name LIKE %?1% OR p.description LIKE %?2%) AND p.user = ?3")
    List<Plan> findByNameIsContainingIgnoreCaseOrDescriptionIsContainingIgnoreCase(String description, String name, User user);
    List<Plan> findByUser(User user);
    Plan findByIdAndUser(long id, User user);
    List<Plan> findByUserOrderByRateDesc(User user);
}
