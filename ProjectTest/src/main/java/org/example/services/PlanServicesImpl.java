package org.example.services;

import org.example.model.ArhivPlan;
import org.example.model.User;
import org.springframework.transaction.annotation.Transactional;
import org.example.model.Plan;
import org.example.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PlanServicesImpl implements PlanServices<Plan> {
    private final PlanRepository planRepository;
    @Autowired
    public PlanServicesImpl(PlanRepository planRepository, PlanRepository planArhiv) {
        this.planRepository = planRepository;
    }

    @Override

    public void save(Plan plan) {
        planRepository.save(plan);
    }

    public void save(ArhivPlan plan) {
        Plan plan1 = new Plan();
        plan1.setUser(plan.getUser());
        plan1.setName(plan.getName());
        plan1.setRate(plan.getRate());
        plan1.setTime(plan.getTime());
        plan1.setDescription(plan.getDescription());
        save(plan1);
    }



    @Override
    public Plan findById(long id) {
        Plan foundPlan = planRepository.findById(id).get();
        return foundPlan;
    }



    @Override
    public List<Plan> lookAll(User user) {
        List<Plan> foundListOfPlans = planRepository.findByUser(user);
        return foundListOfPlans;
    }

    @Override
    public Plan findByIdAndUser(long id, User user) {
        return planRepository.findByIdAndUser(id,user);
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

    public List<Plan> findByNameIsContainingIgnoreCaseOrDescriptionIsContainingIgnoreCase(String description, String name, User user){
        return planRepository.findByNameIsContainingIgnoreCaseOrDescriptionIsContainingIgnoreCase(description, name, user );
    }

    public List<Plan> sortPLans(User user, String criteri){
        switch (criteri){
            case "rate":
                return planRepository.findByUserOrderByRateDesc(user);
            default:
                return planRepository.findAll();
        }
    }
}
