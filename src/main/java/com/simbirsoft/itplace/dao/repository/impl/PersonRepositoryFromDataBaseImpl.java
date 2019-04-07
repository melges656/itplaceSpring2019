package com.simbirsoft.itplace.dao.repository.impl;

import com.simbirsoft.itplace.dao.repository.PersonRepository;
import com.simbirsoft.itplace.domain.entity.PersonalData;
import com.simbirsoft.itplace.repository.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by Denis on 08.04.2019.
 */
@Service
@Primary
public class PersonRepositoryFromDataBaseImpl implements PersonRepository {
    @Autowired
    PersonalDataRepository repository;
    @Override
    public PersonalData getPersonalData() {
        return repository.getOne(1);
    }
}
