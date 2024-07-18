package org.example.repository;

import org.example.model.ArhivPlan;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArhivRepository extends JpaRepository<ArhivPlan,Long> {
    @Query(value = "SELECT p FROM ArhivPlan p WHERE (p.name LIKE %?1% OR p.description LIKE %?2%) AND p.user = ?3")
    List<ArhivPlan> findByNameIsContainingIgnoreCaseOrDescriptionIsContainingIgnoreCase(String name, String description, User user);
    List<ArhivPlan> findByUser(User user);

}
