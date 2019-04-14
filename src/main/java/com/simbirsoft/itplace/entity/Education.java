package com.simbirsoft.itplace.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Denis on 08.04.2019.
 */
@Entity
@SequenceGenerator(name="seqEdu", initialValue=2, allocationSize=500)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Education implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqEdu")
    private Long id;

    private String education;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PERSONAL_DATA_ID")
    private PersonalData personalData;

    public Education(){}

    public Education(String education){
        this.education=education;
    }

    public void setEducation(String education) { this.education = education; }

    public String getEducation() { return education; }

    public void setPersonalData(PersonalData personalData) { this.personalData=personalData; }

    public PersonalData getPersonalData() { return personalData; }

    public Long getId() { return id; }
}
