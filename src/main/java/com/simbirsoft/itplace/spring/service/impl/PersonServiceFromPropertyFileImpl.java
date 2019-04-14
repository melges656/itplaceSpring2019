package com.simbirsoft.itplace.spring.service.impl;

import com.simbirsoft.itplace.common.constants.PersonPropertyKeys;
import com.simbirsoft.itplace.entity.Education;
import com.simbirsoft.itplace.entity.Skill;
import com.simbirsoft.itplace.spring.service.PersonService;
import com.simbirsoft.itplace.spring.service.threads.PropertyReader;
import com.simbirsoft.itplace.entity.PersonalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Реализация репозитория @see {@link PersonService}
 *
 * @author an.stratonov
 * @version 1.0
 */
@Service
public class PersonServiceFromPropertyFileImpl implements PersonService {

    /**
     * Свойство - опыт работы
     */
    private Properties personDataFile;
    @Autowired
    @Qualifier("propertyReader")
    private Thread personReader;
    @Autowired
    @Qualifier("propertyReader")
    private Thread summaryReader;

    @Autowired
    public PersonServiceFromPropertyFileImpl(
            @Value("person.properties")String personConfigFileInput,
            @Value("summary.properties")String summaryConfigFileInput){
        this.personDataFile = getProperties(personConfigFileInput, summaryConfigFileInput);
    }

    /**
     * Возвращает объект файла найстроек
     *
     * @param personConfigFileInput - путь к файлу настроек персональных данных
     * @param summaryConfigFileInput - путь к файлу настроек данных резюме
     * @return - объект Properties
     */
    private Properties getProperties(String personConfigFileInput, String summaryConfigFileInput) {
        Properties property = new Properties();
        personReader = new PropertyReader(personConfigFileInput, property);
        personReader.start();
        summaryReader = new PropertyReader(summaryConfigFileInput, property);
        summaryReader.start();
        try {
            personReader.join();
            summaryReader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return property;
    }

    private List<Skill> parseAssociatedListSkillString(String associatedListData){
        ArrayList<Skill> list = new ArrayList<>();
        HashMap<String, Integer> associatedData = new HashMap<>();
        for(String keyValue: associatedListData.split(";")){
            String[] tmp = keyValue.split(":");
            associatedData.put(tmp[0], Integer.parseInt(tmp[1]));
        }
        Map<String, Integer>  sorted = associatedData
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        for (Map.Entry<String, Integer> entry: sorted.entrySet()){
            list.add(new Skill(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    private List<Education> parseListEducationString(String listData){
        ArrayList<Education> educations = new ArrayList<>();
        for(String education : Arrays.asList(listData.split(";")))
            educations.add(new Education(education));
        return educations;
    }

    /**
     * @see PersonService
     */
    @Override
    public PersonalData getPersonalData() {
        PersonalData personalData = null;
        if(this.personDataFile != null){
            personalData = new PersonalData(
                    personDataFile.getProperty(PersonPropertyKeys.FIO),
                    personDataFile.getProperty(PersonPropertyKeys.DOB),
                    personDataFile.getProperty(PersonPropertyKeys.PHONE),
                    personDataFile.getProperty(PersonPropertyKeys.EMAIL),
                    personDataFile.getProperty(PersonPropertyKeys.SKYPE),
                    personDataFile.getProperty(PersonPropertyKeys.AVATAR),
                    personDataFile.getProperty(PersonPropertyKeys.TARGET),
                    personDataFile.getProperty(PersonPropertyKeys.EXPERIENCES),
                    parseListEducationString(personDataFile.getProperty(PersonPropertyKeys.EDUCATIONS)),
                    personDataFile.getProperty(PersonPropertyKeys.ADDITIONAL_EDUCATIONS),
                    parseAssociatedListSkillString(personDataFile.getProperty(PersonPropertyKeys.SKILLS)),
                    personDataFile.getProperty(PersonPropertyKeys.EXAMPLES_CODE)
            );
        }
        return personalData;
    }

    @Override
    public List<PersonalData> getAllPersonalDatas() {
        return null;
    }

    @Override
    public List<PersonalData> getPersonalDataByTag(String tag) {
        return null;
    }

    @Override
    public PersonalData getPersonalDataById(Long id) {
        return null;
    }

    @Override
    public void deletePersonalData(Long id) {

    }

    @Override
    public void addPersonalData(PersonalData personalData) {

    }

    @Override
    public void updatePersonalData(Long id, PersonalData personalData) {

    }
}
