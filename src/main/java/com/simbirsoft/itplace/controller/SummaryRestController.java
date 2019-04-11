package com.simbirsoft.itplace.controller;

import com.simbirsoft.itplace.entity.PersonalData;
import com.simbirsoft.itplace.repository.PersonalDataRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Denis on 11.04.2019.
 */
@RestController
@RequestMapping(value="/rsummary")
public class SummaryRestController {
    @Autowired
    private PersonalDataRepository repository;
    @GetMapping("{id}")
    @ApiOperation(value = "Get summary by id.")
    public PersonalData getSummary(@PathVariable Integer id){
        return repository.getOne(id);
    }
}
