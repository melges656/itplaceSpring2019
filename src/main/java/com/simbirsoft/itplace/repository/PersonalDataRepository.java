package com.simbirsoft.itplace.repository;

import com.simbirsoft.itplace.entity.PersonalData;
import com.simbirsoft.itplace.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Denis on 08.04.2019.
 */
public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
    List<PersonalData> findByTags(Tag tag);
}
