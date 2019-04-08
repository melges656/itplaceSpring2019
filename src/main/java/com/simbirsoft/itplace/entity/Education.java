package com.simbirsoft.itplace.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Denis on 08.04.2019.
 */
@Entity
public class Education implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private String education;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PERSONAL_DATA_ID")
    private PersonalData personalData;

    public Education(){}

    public Education(String education){
        this.education=education;
    }

    public void setEducation(String education){this.education = education;}

    public String getEducation(){return education;}

    public void setPersonalData(PersonalData personalData){this.personalData=personalData;}

    public PersonalData getPersonalData(){return personalData;}
}
