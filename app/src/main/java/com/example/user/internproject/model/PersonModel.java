package com.example.user.internproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// PersonModel for LocalFragment

@Entity(tableName = "employee")
public class PersonModel {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "employee_name")
    private String name;
    @ColumnInfo(name = "employee_designation")
    private String designation;
    @ColumnInfo(name = "employee_team")
    private String team;
    @ColumnInfo(name = "employee_image")
    private String image;

    public  PersonModel() {

    }

    public PersonModel(String name, String designation, String team, String image) {
        this.name = name;
        this.designation = designation;
        this.team = team;
        this.image = image;
    }

    public int getId() { return id;}

    public void setId(int id) {this.id = id;}
    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getTeam() {
        return team;
    }

    public String getImage(){return image;}

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
