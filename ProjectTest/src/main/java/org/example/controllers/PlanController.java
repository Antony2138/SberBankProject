package org.example.controllers;


import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.ui.Model;
import org.example.model.Plan;
import org.example.services.PlanServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ToDo")
public class PlanController {
    @Autowired
    private final PlanServicesImpl planServices;
    @Autowired
    public PlanController(PlanServicesImpl planServices) {
        this.planServices = planServices;
    }

    @GetMapping()
    public String homeSweetHome(Model model) {
        model.addAttribute("message", "Hello");
        return "look";
    }



//    @GetMapping("/{id}")
//    public String showPlan(@PathVariable("id") int id, Model model){
//        model.addAttribute("showOnePlan", planServices.findById(id));
//        return "look";
//    }

}
