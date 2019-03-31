package com.simbirsoft.itplace.dao.repository.threads;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Created by Denis on 31.03.2019.
 */
public class PropertyReader extends Thread {
    Properties property;
    String propertyFilePath;
    public PropertyReader(String propertyFilePath, Properties property){
        this.property=property;
        this.propertyFilePath=propertyFilePath;
    }
    @Override
    public void run() {
        Properties addData = new Properties();
        try {
            addData.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(propertyFilePath),
                    Charset.forName("UTF-8")));
            property.putAll(addData);
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл настроек "+propertyFilePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
