package com.simbirsoft.itplace;

import com.simbirsoft.itplace.service.api.SummaryService;
import com.simbirsoft.itplace.service.impl.SummaryServiceImpl;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Точка входа в программу
 *
 * @author a.stratonov
 * @version 1.0
 */
@SpringBootApplication(scanBasePackages={
        "com.simbirsoft.itplace"})
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        SummaryService summaryService = context.getBean("summaryServiceImpl", SummaryServiceImpl.class);
        summaryService.initSummaryService("person.properties", "summary.properties");
        try {
            URL urlJar = new URL(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath().split("!/")[0]);
            File destFile = new File(urlJar.toURI().getPath());
            String destPath = (destFile.isDirectory()?destFile.getPath():destFile.getParent())+File.separator;
            System.out.println(destPath + "summary.html");
            summaryService.createHtmlFile(destPath + "summary.html");
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
