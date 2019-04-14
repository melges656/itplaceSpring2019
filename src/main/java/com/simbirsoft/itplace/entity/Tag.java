package com.simbirsoft.itplace.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Denis on 14.04.2019.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "name")
public class Tag implements Serializable {
    @Id
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "TAG_PERSONAL_DATA_LIST",
            joinColumns = @JoinColumn(name="TAGS_NAME", referencedColumnName="name"),
            inverseJoinColumns = @JoinColumn(name="PERSONAL_DATA_LIST_ID", referencedColumnName="id"))
    private List<PersonalData> personalDataList;

    @Transient
    private Integer weight;

    public Tag(){}

    public Tag(String name){
        this.name = name;
    }

    public Tag(String name, Integer weight){

        this.name = name;
        this.weight = weight;
    }

    public String getName() { return name; }

    public void setName( String name ) { this.name = name; }

    @JsonIgnore
    @JsonProperty(value = "personalDataList")
    public List<PersonalData> getPersonalDataList() { return personalDataList; }

    public  void setPersonalDataList( List<PersonalData> personalDataList) {
        this.personalDataList = personalDataList;
    }

    public Integer getWeight() { return weight; }

    public void setWeight(Integer weight) { this.weight = weight; }
}
