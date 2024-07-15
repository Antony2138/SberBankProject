package org.example.controllers;

import org.example.model.Plan;
import org.example.services.PlanServicesImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlanController {
    PlanServicesImpl planServices;

    public PlanController(PlanServicesImpl planServices) {
        this.planServices = planServices;
    }

    @PostMapping()
    public String addPlan(Plan plan){
        planServices.save(plan);
        return "ToDo";
    }
}
