package com.simbirsoft.itplace.spring.service;

import com.simbirsoft.itplace.entity.PersonalData;

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
}
