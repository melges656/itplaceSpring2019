package com.simbirsoft.itplace.controller;

import com.simbirsoft.itplace.entity.Education;
import com.simbirsoft.itplace.entity.Skill;
import com.simbirsoft.itplace.spring.service.PersonService;
import com.simbirsoft.itplace.entity.PersonalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 07.04.2019.
 */
@Controller
public class SummaryController {
    @Autowired
    PersonService service;

    @GetMapping("/summary")
    public String getAllSummary(Model model) {
//        PersonalData personalData = service.getPersonalData();
//        if (personalData == null) {
//            model.addAttribute("message", "Something goes wrong:(");
//            return "message";
//        }
//        model.addAttribute("personalData", personalData);
        return "summaryList";
    }

    @GetMapping("/summary/{id}")
    public String getSummaryById(Model model, @PathVariable Long id) {
        PersonalData personalData = service.getPersonalDataById(id);
        if (personalData == null) {
            model.addAttribute("message", "Something goes wrong:(");
            return "message";
        }
//        model.addAttribute("personalData", personalData);
        return "summary";
    }

    @GetMapping("/summary/new")
    public String getSummaryCreateForm(Model model) {
        PersonalData personalData = new PersonalData();
        List<Education> educations = new ArrayList<>(2);
        for(int i = 0; i<2; i++)
            educations.add(new Education());
        List<Skill> skills = new ArrayList<>(5);
        for(int i = 0; i<5; i++)
            skills.add(new Skill());
        personalData.setEducations(educations);
        personalData.setSkills(skills);
        model.addAttribute("personalData", personalData);
        return "summaryNew";
    }

    @PostMapping("/summary/save")
    public String save(@ModelAttribute PersonalData personalData) {
        service.addPersonalData(personalData);
        return "redirect:/summary";
    }

    @GetMapping("/summary/edit/{id}")
    public String getSummaryEditForm(Model model, @PathVariable Long id) {
        PersonalData personalData = service.getPersonalDataById(id);
        if (personalData == null) {
            model.addAttribute("message", "Something goes wrong:(");
            return "message";
        }
        model.addAttribute("personalData", personalData);
        return "summaryEdit";
    }

    @PostMapping("/summary/edit/{id}")
    public String updateSummary(@ModelAttribute PersonalData personalData, @PathVariable Long id) {
        service.updatePersonalData(id, personalData);
        return "redirect:/summary";
    }
}
