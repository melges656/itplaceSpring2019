package com.simbirsoft.itplace.controller;

import com.simbirsoft.itplace.spring.service.PersonService;
import com.simbirsoft.itplace.entity.PersonalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Denis on 07.04.2019.
 */
@Controller
public class SummaryController {
    @Autowired
    PersonService service;

    @GetMapping("/summary")
    public String getSummaryInfo(Model model) {
        PersonalData personalData = service.getPersonalData();
        if (personalData == null) {
            model.addAttribute("message", "Something goes wrong:(");
            return "message";
        }
        model.addAttribute("personalData", personalData);
        return "summary";
    }
}
