package org.example.services;

import jakarta.transaction.Transactional;
import org.example.model.Plan;
import org.example.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlanServicesImpl implements PlanServices {
    private final PlanRepository planRepository;
    @Autowired
    public PlanServicesImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    @Transactional
    public void save(Plan plan) {
        planRepository.save(plan);
    }

    @Override
    public Optional<Plan> findById(long id) {
        Optional<Plan> foundPlan = planRepository.findById(id);
        return foundPlan;
    }

    @Override
    public List<Plan> lookAll() {
        List<Plan> foundListOfPlans = planRepository.findAll();
        return foundListOfPlans;
    }

    @Override
    @Transactional
    public void update(Plan plan, long id) {
        plan.setId(id);
        planRepository.save(plan);
    }

    @Override
    @Transactional
    public void delete(long id) {
        planRepository.deleteById(id);
    }
}
