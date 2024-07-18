package org.example.services;

import org.example.model.ArhivPlan;
import org.example.model.Plan;
import org.example.model.User;
import org.example.repository.ArhivRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArhivPlanImpl implements PlanServices<ArhivPlan>{
    private ArhivRepository planRepository;

    public ArhivPlanImpl(ArhivRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public void save(ArhivPlan plan) {
        planRepository.save(plan);
    }

    public void save(Plan plan) {
        ArhivPlan plan1 = new ArhivPlan();
        plan1.setPlanCategory(plan.getPlanCategory());
        plan1.setUser(plan.getUser());
        plan1.setName(plan.getName());
        plan1.setRate(plan.getRate());
        plan1.setTime(plan.getTime());
        plan1.setDescription(plan.getDescription());
        save(plan1);
    }

    @Override
    public ArhivPlan findById(long id) {
        ArhivPlan foundPlan = planRepository.findById(id).get();
        return foundPlan;
    }

    @Override
    public List<ArhivPlan> lookAll(User user) {
        List<ArhivPlan> foundListOfPlans = planRepository.findByUser(user);
        return foundListOfPlans;
    }

    @Override
    public ArhivPlan findByIdAndUser(long id, User user) {
        return null;
    }


    @Override
    public void update(ArhivPlan plan, long id) {

    }
    @Override
    @Transactional
    public void delete(long id) {
        planRepository.deleteById(id);
    }

    public List<ArhivPlan> findByNameIsContainingIgnoreCaseOrDescriptionIsContainingIgnoreCase(String description, String name, User user){
        return planRepository.findByNameIsContainingIgnoreCaseOrDescriptionIsContainingIgnoreCase(description, name, user);
    }
}
