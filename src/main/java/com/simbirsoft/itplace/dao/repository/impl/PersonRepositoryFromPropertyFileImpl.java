package com.simbirsoft.itplace.dao.repository.impl;

import com.simbirsoft.itplace.common.constants.PersonPropertyKeys;
import com.simbirsoft.itplace.dao.repository.PersonRepository;
import com.simbirsoft.itplace.dao.repository.threads.PropertyReader;
import com.simbirsoft.itplace.domain.entity.PersonalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Реализация репозитория @see {@link PersonRepository}
 *
 * @author an.stratonov
 * @version 1.0
 */
@Service
public class PersonRepositoryFromPropertyFileImpl implements PersonRepository {

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
    public PersonRepositoryFromPropertyFileImpl(
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

    private List<String> parseAssociatedListDataString(String associatedListData){
        ArrayList<String> list = new ArrayList<>();
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
            StringBuilder tmp = new StringBuilder("Опыт работы с ");
            list.add(tmp.append(entry.getKey()).append(" в месяцах: ").append(entry.getValue()).toString());
        }
        return list;
    }

    private List<String> parseListDataString(String listData){
        return Arrays.asList(listData.split(";"));
    }

    /**
     * @see PersonRepository
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
                    parseListDataString(personDataFile.getProperty(PersonPropertyKeys.EDUCATIONS)),
                    personDataFile.getProperty(PersonPropertyKeys.ADDITIONAL_EDUCATIONS),
                    parseAssociatedListDataString(personDataFile.getProperty(PersonPropertyKeys.SKILLS)),
                    personDataFile.getProperty(PersonPropertyKeys.EXAMPLES_CODE)
            );
        }
        return personalData;
    }
}
