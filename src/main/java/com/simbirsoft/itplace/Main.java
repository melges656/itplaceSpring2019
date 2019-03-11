package com.simbirsoft.itplace;

import com.simbirsoft.itplace.service.api.SummaryService;
import com.simbirsoft.itplace.service.impl.SummaryServiceImpl;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Точка входа в программу
 *
 * @author a.stratonov
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        SummaryService summaryService = new SummaryServiceImpl("person.properties");
        try {
            File destFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            String destPath = (destFile.isDirectory()?destFile.getPath():destFile.getParent())+File.separator;
            System.out.println(destPath + "summary.html");
            summaryService.createHtmlFile(destPath + "summary.html");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
