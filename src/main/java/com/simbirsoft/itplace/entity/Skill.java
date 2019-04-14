package com.simbirsoft.itplace.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Denis on 08.04.2019.
 */
@Entity
@SequenceGenerator(name="seqSkill", initialValue=6, allocationSize=100)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Skill implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqSkill")
    private Long id;

    private String skill;

    private Integer value;

    @JsonBackReference
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

    public Long getId() { return id; }
}
