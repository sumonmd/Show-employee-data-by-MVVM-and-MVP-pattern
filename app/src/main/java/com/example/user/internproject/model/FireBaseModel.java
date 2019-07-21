package com.example.user.internproject.model;

import android.content.Context;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by Belal on 2/26/2017.
 */

//FireBaseModel model for ServerFragment
@IgnoreExtraProperties
public class FireBaseModel {
    private String artistId;
    private String artistName;
    private String artistDesignation;
    private String artistTeam;
    private String artistImage;

    public FireBaseModel(List<FireBaseModel> artists, Context context){
        //this constructor is required
    }

    public FireBaseModel() {
    }

    public FireBaseModel(String artistId, String artistName, String artistDesignation, String artistTeam, String artistImage) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistDesignation = artistDesignation;
        this.artistTeam = artistTeam;
        this.artistImage = artistImage;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setArtistDesignation(String artistDesignation) {
        this.artistDesignation = artistDesignation;
    }

    public void setArtistTeam(String artistTeam) {
        this.artistTeam = artistTeam;
    }

    public void setArtistImage(String artistImage) {
        this.artistImage = artistImage;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistDesignation() {
        return artistDesignation;
    }
    public String getArtistTeam() {
        return artistTeam;
    }
    public String getArtistImage() {
        return artistImage;
    }



}