package com.simbirsoft.itplace.spring.service;

import com.simbirsoft.itplace.entity.PersonalData;

import java.util.List;

/**
 * Репозиторий для получения персональных данных. Прочитайте про Data Access Layer
 *
 * @author a.stratonov
 * @version 1.0
 */
public interface PersonService {

    /**
     * получение персональных данных из хранилища
     * @return {@link PersonalData}
     */
    PersonalData getPersonalData();
    List<PersonalData> getAllPersonalDatas();
    PersonalData getPersonalDataById(Long id);
    void deletePersonalData(Long id);
    void addPersonalData(PersonalData personalData);
    void updatePersonalData(Long id, PersonalData personalData);
}
