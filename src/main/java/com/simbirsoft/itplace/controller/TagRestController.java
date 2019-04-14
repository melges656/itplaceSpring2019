package com.simbirsoft.itplace.controller;

import com.simbirsoft.itplace.entity.Tag;
import com.simbirsoft.itplace.spring.service.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Denis on 14.04.2019.
 */
@RestController
@RequestMapping(value="/rest/tags")
public class TagRestController {
    @Autowired
    private TagService service;

    @GetMapping
    @ApiOperation(value = "Get all tags with weights.")
    public List<Tag> getAllTags(){
        return service.getTags();
    }
}
