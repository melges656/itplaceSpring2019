package com.simbirsoft.itplace.controller;

import com.simbirsoft.itplace.Exception.ElementNotFoundException;
import com.simbirsoft.itplace.entity.PersonalData;
import com.simbirsoft.itplace.repository.PersonalDataRepository;
import com.simbirsoft.itplace.repository.TagRepository;
import com.simbirsoft.itplace.spring.service.impl.PersonServiceFromDataBaseImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Denis on 11.04.2019.
 */
@RestController
@RequestMapping(value="/rest/summary")
public class SummaryRestController {

    @Autowired
    private PersonServiceFromDataBaseImpl service;

    @GetMapping
    @ApiOperation(value = "Get all summary.")
    public List<PersonalData> getAllSummary(){
        return service.getAllPersonalDatas();
    }

    @GetMapping("/tag/{name}")
    @ApiOperation(value = "Get all summary by tag.")
    public List<PersonalData> getAllSummaryByTag(@PathVariable String name){
        return service.getPersonalDataByTag(name);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get summary by id.")
    public PersonalData getSummary(@PathVariable Long id){
        return service.getPersonalDataById(id);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete summary by id.")
    public void deleteSummary(@PathVariable Long id) {
        service.deletePersonalData(id);
    }

    @PostMapping
    @ApiOperation(value = "Create new summary.")
    public void addSummary(@RequestBody PersonalData summary) {
        service.addPersonalData(summary);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Update summary by id.")
    public void updateStudent(@PathVariable Long id, @RequestBody PersonalData summary ) {
        service.updatePersonalData(id, summary);
    }

}
