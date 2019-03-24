package com.simbirsoft.itplace;

import com.simbirsoft.itplace.dao.repository.impl.PersonRepositoryFromPropertyFileImplTest;
import com.simbirsoft.itplace.service.impl.SummaryServiceImplTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Unit test for simple App.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SummaryServiceImplTest.class,
        PersonRepositoryFromPropertyFileImplTest.class
})
public class AppTest {

}
