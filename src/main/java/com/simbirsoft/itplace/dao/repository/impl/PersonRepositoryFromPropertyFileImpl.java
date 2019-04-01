package com.simbirsoft.itplace.dao.repository.impl;

import com.simbirsoft.itplace.common.constants.PersonPropertyKeys;
import com.simbirsoft.itplace.dao.repository.PersonRepository;
import com.simbirsoft.itplace.dao.repository.threads.PropertyReader;
import com.simbirsoft.itplace.domain.entity.PersonalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Реализация репозитория @see {@link PersonRepository}
 *
 * @author an.stratonov
 * @version 1.0
 */
@Component
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
    public PersonRepositoryFromPropertyFileImpl(){

    }
    public PersonRepositoryFromPropertyFileImpl(String personConfigFileInput, String summaryConfigFileInput){
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
                    personDataFile.getProperty(PersonPropertyKeys.EDUCATIONS),
                    personDataFile.getProperty(PersonPropertyKeys.ADDITIONAL_EDUCATIONS),
                    personDataFile.getProperty(PersonPropertyKeys.SKILLS),
                    personDataFile.getProperty(PersonPropertyKeys.EXAMPLES_CODE)
            );
        }
        return personalData;
    }
}
