package org.example.controllers;


import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.ui.Model;
import org.example.model.Plan;
import org.example.services.PlanServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ToDo")
public class PlanController {
    @Autowired
    private final PlanServicesImpl planServices;
    @Autowired
    public PlanController(PlanServicesImpl planServices) {
        this.planServices = planServices;
    }

    @PostMapping()
    public String addPlan(@ModelAttribute("plan") Plan plan, Model model){
        planServices.save(plan);
        model.addAttribute("plans", planServices.lookAll());
        System.out.println(planServices.lookAll());
        return "ToDo";
    }

    @GetMapping()
    public String start(Model model) {
        model.addAttribute("plans", planServices.lookAll());
        return "ToDo";
    }



//    @GetMapping("/{id}")
//    public String showPlan(@PathVariable("id") int id, Model model){
//        model.addAttribute("showOnePlan", planServices.findById(id));
//        return "look";
//    }

}
