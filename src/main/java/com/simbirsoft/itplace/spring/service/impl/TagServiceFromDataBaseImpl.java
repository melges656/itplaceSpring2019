package com.simbirsoft.itplace.spring.service.impl;

import com.simbirsoft.itplace.entity.Tag;
import com.simbirsoft.itplace.repository.TagRepository;
import com.simbirsoft.itplace.spring.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Denis on 14.04.2019.
 */
@Service
public class TagServiceFromDataBaseImpl implements TagService {
    @Autowired
    private TagRepository repository;
    @Override
    public List<Tag> getTags() {
        List<Tag> tags = repository.findAll();
        for(Tag tag : tags){
            tag.setWeight(tag.getPersonalDataList().size());
        }
        return tags;
    }
}
