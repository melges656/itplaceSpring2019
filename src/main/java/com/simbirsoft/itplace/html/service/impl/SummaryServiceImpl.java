package com.simbirsoft.itplace.html.service.impl;

import com.simbirsoft.itplace.entity.Education;
import com.simbirsoft.itplace.entity.Skill;
import com.simbirsoft.itplace.html.service.api.SummaryService;
import com.simbirsoft.itplace.spring.service.PersonService;
import com.simbirsoft.itplace.spring.service.impl.PersonServiceFromPropertyFileImpl;
import com.simbirsoft.itplace.entity.PersonalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Реализация интерфейса @see {@link SummaryService}
 */
@Component
public class SummaryServiceImpl implements SummaryService {

    /**
     * @see PersonalData
     */
    private PersonalData personalData;
    @Autowired
    @Qualifier("personServiceFromPropertyFileImpl")
    private PersonService personService;
    @Autowired
    public SummaryServiceImpl(){}
    /**
     * Метод для подключения .properties файла
     * @param personPropertyFilePath - путь к .properties файлу персональных данных
     * @param summaryPropertyFilePath - путь к .properties файлу данных резюме
     */
    public void initSummaryService(String personPropertyFilePath, String summaryPropertyFilePath){
        personService = new PersonServiceFromPropertyFileImpl(
                personPropertyFilePath, summaryPropertyFilePath
        );
        this.personalData = personService.getPersonalData();
        personService = null;
    }

    private String getHtmlListFromSkill(List<Skill> skills){
        StringBuilder htmlList = new StringBuilder();
        for(Skill skill : skills)
            htmlList.append("<li>").append("Опыт работы с ").append(skill.getSkill())
                    .append(" в месяцах: ").append(skill.getValue()).append("</li>");
        return htmlList.toString();
    }

    private String getHtmlListFromEducations(List<Education> educations){
        StringBuilder htmlList = new StringBuilder();
        for(Education education: educations)
            htmlList.append("<li>").append(education.getEducation()).append("</li>");
        return htmlList.toString();
    }

    @Override
    public void createHtmlFile(String pathHtmlFile) {
        if (this.personalData != null){
            String html = "<!DOCTYPE html>\n" +
                    "<html lang=\"ru\">\n" +
                    "<head>\n" +
                    "    <title>Резюме</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"\n" +
                    "          integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\"\n" +
                    "          crossorigin=\"anonymous\">\n" +
                    "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"\n" +
                    "            integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\"\n" +
                    "            crossorigin=\"anonymous\"></script>\n" +
                    "    <style>\n" +
                    "        body{background: darkgray; padding: 10px 0;}\n" +
                    "        .container { max-width: 50%; background: white; padding: 10px;}\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <h4 class=\"text-center\"><strong>Резюме</strong><p>на должность Java-стажер</p></h4>\n" +
                    "        <div class=\"row\">\n" +
                    "            <div class=\"col-xs-8 col-md-8\">\n" +
                    "                <dl class=\"dl-horizontal\">\n" +
                    "                    <dt>ФИО:</dt><dd>" + personalData.getFIO() + "</dd>\n" +
                    "                    <dt>Дата рождения:</dt><dd>" + personalData.getDOB() + "</dd>\n" +
                    "                    <dt>Телефон:</dt><dd>" + personalData.getPhone() + "/dd>\n" +
                    "                    <dt>e-mail:</dt><dd>" + personalData.getEmail() + "</dd>\n" +
                    "                    <dt>Skype:</dt><dd>" + personalData.getSkype() + "</dd>\n" +
                    "                </dl></div>\n" +
                    "            <div class=\"col-xs-4 col-md-4\">\n" +
                    "                <img src=\"" + personalData.getAvatar() + "\" width=\"100%\">\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div class=\"card card-block\">\n" +
                    "            <h4 class=\"card-title\"><strong>Цель:</strong></h4>\n" +
                    "            <p class=\"card-text\">" + personalData.getTarget() + "</p>\n" +
                    "        </div>\n" +
                    "        <div class=\"card card-block\">\n" +
                    "            <h4 class=\"card-title\"><strong>Опыт работы:</strong></h4>\n" +
                    "            <p class=\"card-text\">" + personalData.getExperiences() + "</p>\n" +
                    "        </div>\n" +
                    "        <div class=\"card card-block\">\n" +
                    "            <h4 class=\"card-title\"><strong>Образование:</strong></h4>\n" +
                    "            <ol class=\"card-text\">\n"
                                    + getHtmlListFromEducations(personalData.getEducations()) +
                    "            </ol>\n" +
                    "        </div>\n" +
                    "        <div class=\"card card-block\">\n" +
                    "            <h4 class=\"card-title\"><strong>Доп. образ. и курсы:</strong></h4>\n" +
                    "            <p class=\"card-text\">" + personalData.getAdditionalEducations() + "</p>\n" +
                    "        </div>\n" +
                    "        <div class=\"card card-block\">\n" +
                    "            <h4 class=\"card-title\"><strong>Навыки:</strong></h4>\n" +
                    "            <ol class=\"card-text\">\n"
                                    + getHtmlListFromSkill(personalData.getSkills()) +
                    "            </ol>\n" +
                    "        </div>\n" +
                    "        <div class=\"card card-block\">\n" +
                    "            <h4 class=\"card-title\"><strong>Примеры моего кода:</strong></h4>\n" +
                    "            <div class=\"card-text\">\n"
                                    + personalData.getExamplesCode() +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>";
            try {
                File file = new File(pathHtmlFile);
                if(!file.exists()){
                    file.createNewFile();
                }
                FileWriter writer = new FileWriter(pathHtmlFile, false);

                writer.write(html);
                writer.flush();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
