package com.simbirsoft.itplace.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Denis on 08.04.2019.
 */
@Entity
public class Skill implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private String skill;

    private Integer value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PERSONAL_DATA_ID")
    private PersonalData personalData;

    public Skill(){}

    public Skill(String skill, Integer value){
        this.skill=skill;
        this.value=value;
    }

    public void setSkill(String skill){this.skill = skill;}

    public String getSkill(){return skill;}

    public void setPersonalData(PersonalData personalData){this.personalData=personalData;}

    public PersonalData getPersonalData(){return personalData;}

    public void setValue(Integer value){this.value=value;}

    public Integer getValue(){return value;}
}
