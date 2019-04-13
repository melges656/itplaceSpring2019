package com.simbirsoft.itplace.spring.service.impl;

import com.simbirsoft.itplace.Exception.ElementNotFoundException;
import com.simbirsoft.itplace.spring.service.PersonService;
import com.simbirsoft.itplace.entity.PersonalData;
import com.simbirsoft.itplace.repository.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return getPersonalDataById(1l);
    }

    @Override
    public List<PersonalData> getAllPersonalDatas() {
        return repository.findAll();
    }

    @Override
    public PersonalData getPersonalDataById(Long id) {
        Optional<PersonalData> personalData = repository.findById(id);

        if (!personalData.isPresent())
            throw new ElementNotFoundException();

        return personalData.get();
    }

    @Override
    public void deletePersonalData(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void addPersonalData(PersonalData personalData) {
        repository.save(personalData);
    }

    @Override
    public void updatePersonalData(Long id, PersonalData personalData) {
        PersonalData updatingPersonalData = repository.getOne(id);
        updatingPersonalData.setAdditionalEducations(personalData.getAdditionalEducations());
        updatingPersonalData.setAvatarPath(personalData.getAvatar());
        updatingPersonalData.setDOB(personalData.getDOB());
//        updatingPersonalData.setEducations(personalData.getEducations());
        updatingPersonalData.setEmail(personalData.getEmail());
        updatingPersonalData.setExamplesCode(personalData.getExamplesCode());
        updatingPersonalData.setExperiences(personalData.getExperiences());
        updatingPersonalData.setFIO(personalData.getFIO());
        updatingPersonalData.setPhone(personalData.getPhone());
//        updatingPersonalData.setSkills(personalData.getSkills());
        updatingPersonalData.setSkype(personalData.getSkype());
        updatingPersonalData.setTarget(personalData.getTarget());

        repository.save(updatingPersonalData);

    }
}
