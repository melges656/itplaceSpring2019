package com.simbirsoft.itplace.spring.service.impl;

import com.simbirsoft.itplace.Exception.ElementNotFoundException;
import com.simbirsoft.itplace.entity.Tag;
import com.simbirsoft.itplace.repository.TagRepository;
import com.simbirsoft.itplace.spring.service.PersonService;
import com.simbirsoft.itplace.entity.PersonalData;
import com.simbirsoft.itplace.repository.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Denis on 08.04.2019.
 */
@Service
@Primary
public class PersonServiceFromDataBaseImpl implements PersonService {
    @Autowired
    private PersonalDataRepository repository;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public PersonalData getPersonalData() {
        return getPersonalDataById(1l);
    }

    @Override
    public List<PersonalData> getAllPersonalDatas() {
        return repository.findAll();
    }

    @Override
    public List<PersonalData> getPersonalDataByTag(String tag) {
        return repository.findByTags(tagRepository.getOne(tag));
    }

    @Override
    public PersonalData getPersonalDataById(Long id) {
        Optional<PersonalData> optionalPersonalData = repository.findById(id);

        if (!optionalPersonalData.isPresent())
            throw new ElementNotFoundException();
        PersonalData personalData = optionalPersonalData.get();
        List<Tag> tags = personalData.getTags();
        if(tags!=null) {
            StringBuilder tagsStringBuilder = new StringBuilder(tags.get(0).getName());
            for (int i = 1; i < tags.size(); i++)
                tagsStringBuilder.append(",").append(tags.get(i).getName());
            personalData.setTagsString(tagsStringBuilder.toString());
        }
        return personalData;
    }

    @Override
    public void deletePersonalData(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void addPersonalData(PersonalData personalData) {

        String[] stringTags = personalData.getTagsString().replaceAll(" ", "").split(",");
        List<Tag> tags = new ArrayList<>();
        for(String tag: stringTags) {
            tags.add(new Tag(tag));
        }
        personalData.setTags(tags);

        repository.save(personalData);

        List<Tag> savedTags = personalData.getTags();
        for(int i = 0; i< savedTags.size(); i++){
            List<PersonalData> personalDatas = savedTags.get(i).getPersonalDataList();
            if(personalDatas == null)
                personalDatas = new ArrayList<>();
            personalDatas.add(personalData);
            savedTags.get(i).setPersonalDataList(personalDatas);
            tagRepository.save(savedTags.get(i));
        }

    }

    @Override
    public void updatePersonalData(Long id, PersonalData personalData) {
        PersonalData updatingPersonalData = repository.getOne(id);
        updatingPersonalData.setAdditionalEducations(personalData.getAdditionalEducations());
        updatingPersonalData.setAvatarPath(personalData.getAvatar());
        updatingPersonalData.setDOB(personalData.getDOB());
        updatingPersonalData.setEducations(personalData.getEducations());
        updatingPersonalData.setEmail(personalData.getEmail());
        updatingPersonalData.setExamplesCode(personalData.getExamplesCode());
        updatingPersonalData.setExperiences(personalData.getExperiences());
        updatingPersonalData.setFIO(personalData.getFIO());
        updatingPersonalData.setPhone(personalData.getPhone());
        updatingPersonalData.setSkills(personalData.getSkills());
        updatingPersonalData.setSkype(personalData.getSkype());
        updatingPersonalData.setTarget(personalData.getTarget());

        String[] stringTags = personalData.getTagsString().replaceAll(" ", "").split(",");
        List<Tag> tags = new ArrayList<>();
        for(String tagName: stringTags) {
            if(tagRepository.existsById(tagName)){
                tags.add(tagRepository.getOne(tagName));
            }
            else{
                tags.add(new Tag(tagName));
            }
        }
        updatingPersonalData.setTags(tags);
        repository.save(updatingPersonalData);

        List<Tag> savedTags = updatingPersonalData.getTags();
        for(int i = 0; i< savedTags.size(); i++){
            List<PersonalData> personalDatas = savedTags.get( i ).getPersonalDataList();
            if( personalDatas == null )
                personalDatas = new ArrayList<>();
            if( !personalDatas.contains( updatingPersonalData ) ) {
                personalDatas.add( updatingPersonalData );
                savedTags.get(i).setPersonalDataList( personalDatas );
                tagRepository.save( savedTags.get(i) );
            }
        }
    }
}
