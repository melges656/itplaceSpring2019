package com.simbirsoft.itplace.repository;

import com.simbirsoft.itplace.domain.entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Denis on 08.04.2019.
 */
public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
}
