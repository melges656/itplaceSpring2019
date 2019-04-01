package com.simbirsoft.itplace.service.impl;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Files;


public class SummaryServiceImplTest {
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();
    private  SummaryServiceImpl summaryService;
    @Before
    public void setUp() throws Exception {
        summaryService = new SummaryServiceImpl();
        summaryService.initSummaryService("person.properties", "summary.properties");
    }

    @After
    public void tearDown() throws Exception {
        summaryService = null;
    }

    @Test
    public void testCreateHtmlFile() throws Exception {
        final File expected = new File("./src/main/webapp/summary.html");
        final File output = folder.newFile("actualSummary.html");
        summaryService.createHtmlFile(output.getPath());
        Assert.assertEquals(new String(Files.readAllBytes(expected.toPath())), new String(Files.readAllBytes(output.toPath())));
    }

}