package com.simbirsoft.itplace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@SequenceGenerator(name="seqSummary", initialValue=1, allocationSize=100)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PersonalData implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqSummary")
    private Long id;
    /**
     * Свойство - Фамилия Имя Отчество
     */
    private String FIO;

    /**
     * Свойство - дата рождения
     */
    private String DOB;

    /**
     * Свойство - телефон
     */
    private String phone;

    /**
     * Свойство - электронная почта
     */
    private String email;

    /**
     * Свойство - скайп
     */
    private String skype;

    /**
     * Свойство - ссылка на аватарку
     */
    private String avatar;

    /**
     * Свойство - цель
     */
    private String target;

    /**
     * Свойство - опыт работы
     */
    private String experiences;

    /**
     * Свойство - образование
     */
    @JsonManagedReference
    @OneToMany(cascade=ALL, mappedBy="personalData")
    private List<Education> educations;

    /**
     * Свойство - дополнительное образование
     */
    private String additionalEducations;

    /**
     * Свойство - скилы
     */
    @JsonManagedReference
    @OneToMany(cascade=ALL, mappedBy="personalData")
    @OrderBy("value DESC")
    private List<Skill> skills;

    /**
     * Свойство - примеры кода
     */
    private String examplesCode;

    @ManyToMany(cascade=ALL, fetch = FetchType.LAZY, mappedBy="personalDataList")
    private List<Tag> tags;

    @Transient
    private String tagsString;

    public PersonalData(){}

    public PersonalData(
            String FIO,
            String DOB,
            String phone,
            String email,
            String skype,
            String avatar,
            String target,
            String experiences,
            List<Education> educations,
            String additionalEducations,
            List<Skill> skills,
            String examplesCode) {
        this.FIO = FIO;
        this.DOB = DOB;
        this.phone = phone;
        this.email = email;
        this.skype = skype;
        this.avatar = avatar;
        this.target = target;
        this.experiences = experiences;
        this.educations = educations;
        this.additionalEducations = additionalEducations;
        this.skills = skills;
        this.examplesCode = examplesCode;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatarPath(String avatar) {
        this.avatar = avatar;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public String getAdditionalEducations() {
        return additionalEducations;
    }

    public void setAdditionalEducations(String additionalEducations) {
        this.additionalEducations = additionalEducations;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public String getExamplesCode() {
        return examplesCode;
    }

    public void setExamplesCode(String examplesCode) {
        this.examplesCode = examplesCode;
    }

    public Long getId() { return id; }

    public void setTags( List<Tag> tags) { this.tags = tags; }

    public List<Tag> getTags () { return tags; }

    @JsonIgnore
    @JsonProperty(value = "tagsString")
    public String getTagsString() { return tagsString; }

    public void setTagsString( String tagsString ) { this.tagsString = tagsString; }

    public void setAvatar( String avatar ) { this.avatar = avatar; }
}
