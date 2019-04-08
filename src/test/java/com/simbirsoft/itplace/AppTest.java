package com.simbirsoft.itplace;

import com.simbirsoft.itplace.spring.service.impl.PersonServiceFromPropertyFileImplTest;
import com.simbirsoft.itplace.html.service.impl.SummaryServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Unit test for simple App.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SummaryServiceImplTest.class,
        PersonServiceFromPropertyFileImplTest.class
})
public class AppTest {

}
