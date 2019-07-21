package com.example.user.internproject.viewmodel;

import android.app.Application;

import com.example.user.internproject.model.JsonRepository;
import com.example.user.internproject.model.PersonModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class LocalFragmentViewModel  extends AndroidViewModel {
    private JsonRepository jsonRepository;
    private LiveData<List<PersonModel>> allperson;
    public LocalFragmentViewModel(@NonNull Application application) {
        super(application);
        jsonRepository = new JsonRepository(application);
        allperson = jsonRepository.getAll();
    }
    public void insert(PersonModel personModel){
        jsonRepository.insert(personModel);
    }
    public LiveData<List<PersonModel>> getAllperson(){
        return allperson;
    }
}
