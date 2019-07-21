package com.example.user.internproject.model;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.example.user.internproject.mydatabase.EmployeeDao;
import com.example.user.internproject.mydatabase.MyDatabase;
import com.example.user.internproject.view.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import static android.content.Context.MODE_PRIVATE;

public class JsonRepository {
    EmployeeDao employeeDao;
    Application application;
    String personModels;
    LiveData<List<PersonModel>> mutableLiveData;
    List<PersonModel> employeeList = new ArrayList<PersonModel>();

    public JsonRepository(Application application) {
        MyDatabase myDatabase = MyDatabase.getInstance(application);
        employeeDao = myDatabase.employeeDao();
        mutableLiveData = employeeDao.getAll();
        this.application = application;
    }



    public void insert(PersonModel personModel){
        new InsertPersonAsyntask(employeeDao).execute(personModel);
    }
    public LiveData<List<PersonModel>> getAll(){
        return mutableLiveData;
    }
    private static class InsertPersonAsyntask extends AsyncTask<PersonModel,Void,Void>{
        private EmployeeDao employeeDao;

        private InsertPersonAsyntask(EmployeeDao employeeDao){
            this.employeeDao = employeeDao;

        }
        @Override
        protected Void doInBackground(PersonModel... personModels) {
            employeeDao.addUser((personModels[0]));
            return null;
        }
    }


}
