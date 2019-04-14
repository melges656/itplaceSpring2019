package com.simbirsoft.itplace.repository;

import com.simbirsoft.itplace.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Denis on 14.04.2019.
 */

public interface TagRepository extends JpaRepository<Tag, String> {
}
