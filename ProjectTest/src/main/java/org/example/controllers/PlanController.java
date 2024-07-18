package org.example.controllers;


import jakarta.validation.Valid;
import org.example.model.ArhivPlan;
import org.example.model.User;
import org.example.services.ArhivPlanImpl;
import org.example.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.example.model.Plan;
import org.example.services.PlanServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ToDo")
public class PlanController {
    @Autowired
    private final PlanServicesImpl planServices;
    private final UserService userService;

    private final ArhivPlanImpl arhivPlan;
    @Autowired
    public PlanController(PlanServicesImpl planServices, UserService userService, ArhivPlanImpl arhivPlan) {
        this.planServices = planServices;
        this.userService = userService;
        this.arhivPlan = arhivPlan;
    }

    @PostMapping()
    public String addPlan(@Valid @ModelAttribute("plan")Plan plan, BindingResult bind, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (bind.hasErrors()) {
            model.addAttribute("username", userDetails.getUsername());
            return "ToDo";
        }
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        plan.setUser(user);
        planServices.save(plan);
        return "redirect:/ToDo";
    }

    @GetMapping()
    public String showPlan(Model model, @AuthenticationPrincipal UserDetails userDetails){
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("plans", planServices.lookAll(user));
        model.addAttribute("arhivPlans", arhivPlan.lookAll(user));
        model.addAttribute("plan", new Plan());
        return "ToDo";
    }

    @GetMapping("/{id}")
    public  String showOnePlan(@PathVariable("id") long id, Model model){
        model.addAttribute("onePlan", planServices.findById(id));
        return "look";
    }
    @GetMapping("/arhiv/{id}")
    public  String showOnePlanArhiv(@PathVariable("id") long id, Model model){
        model.addAttribute("onePlan", arhivPlan.findById(id));
        return "lookArhiv";
    }


    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id")long id){
        planServices.delete(id);
        return "redirect:/ToDo";
    }
    @PostMapping("/{id}/arhiv")
    public String inArhiv(@PathVariable("id")long id, @AuthenticationPrincipal UserDetails userDetails){
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        arhivPlan.save(planServices.findById(id));
        planServices.delete(id);
        return "redirect:/ToDo";
    }
    @PostMapping("/{id}/backActive")
    public String backActive(@PathVariable("id")long id, @AuthenticationPrincipal UserDetails userDetails){
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        arhivPlan.findById(id).setUser(user);
        planServices.save(arhivPlan.findById(id));
        arhivPlan.delete(id);
        return "redirect:/ToDo";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id")long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Plan plan = planServices.findByIdAndUser(id, user);
        model.addAttribute("editplan", plan);
        return "edit";
    }
    @PostMapping("/{id}/edit")
    public String update(@PathVariable("id")long id, @ModelAttribute("editplan") Plan plan) {
        plan.setUser(planServices.findById(id).getUser());
        planServices.update(plan,id);
        return "redirect:/ToDo";
    }
    @GetMapping("/findByKeyWord")
    public String findByWord(Model model,@RequestParam(required = false) String name, @AuthenticationPrincipal UserDetails userDetails){
        model.addAttribute("plan", new Plan());
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        model.addAttribute("username", userDetails.getUsername());

        model.addAttribute("plans",planServices.findByNameIsContainingIgnoreCaseOrDescriptionIsContainingIgnoreCase(name, name, user));
        model.addAttribute("arhivPlans", arhivPlan.findByNameIsContainingIgnoreCaseOrDescriptionIsContainingIgnoreCase(name, name, user));
        return "ToDo";

    }

    @GetMapping("/sort")
    public String sortPlansShow(Model model, @RequestParam String criteri, @AuthenticationPrincipal UserDetails userDetails){
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        model.addAttribute("plan", new Plan());
        model.addAttribute("plans", planServices.sortPLans(user, criteri));
        model.addAttribute("username", userDetails.getUsername());
        return "ToDo";
    }





}
