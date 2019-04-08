package com.simbirsoft.itplace.spring.service.impl;

import com.simbirsoft.itplace.spring.service.PersonService;
import com.simbirsoft.itplace.entity.PersonalData;
import com.simbirsoft.itplace.repository.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by Denis on 08.04.2019.
 */
@Service
@Primary
public class PersonServiceFromDataBaseImpl implements PersonService {
    @Autowired
    PersonalDataRepository repository;
    @Override
    public PersonalData getPersonalData() {
        return repository.getOne(1);
    }
}
