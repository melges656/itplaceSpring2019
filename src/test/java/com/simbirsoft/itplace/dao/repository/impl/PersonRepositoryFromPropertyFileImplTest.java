package com.simbirsoft.itplace.dao.repository.impl;

import com.simbirsoft.itplace.common.constants.PersonPropertyKeys;
import com.simbirsoft.itplace.domain.entity.PersonalData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PersonRepositoryFromPropertyFileImplTest {

    private PersonalData personalData;
    private final String expectedFio = "Шалаев Денис Олегович";
    private final String expectedSkype = "melges656";

    @Before
    public void setUp() throws Exception {
        personalData = new PersonRepositoryFromPropertyFileImpl(
                "person.properties", "summary.properties").getPersonalData();
    }

    @After
    public void tearDown() throws Exception {
        personalData = null;
    }

    @Test
    public void testGetPersonalData() throws Exception {
        Assert.assertEquals(expectedFio, personalData.getFIO());
        Assert.assertEquals(expectedSkype, personalData.getSkype());
    }

}