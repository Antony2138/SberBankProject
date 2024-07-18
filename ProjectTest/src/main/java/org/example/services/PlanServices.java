package org.example.services;

import org.example.model.Plan;
import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlanServices<P> {
    void save(P plan);

    P findById(long id);

    List<P> lookAll(User user);
    P findByIdAndUser(long id, User user);


    void update(P plan, long id);

    void delete(long id);
}
