package com.example.user.internproject.mydatabase;

import com.example.user.internproject.model.PersonModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

// EmployeeDao for LocalFragment

@Dao
public interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(PersonModel personModel);

    @Query("SELECT * FROM employee")
    LiveData<List<PersonModel>>getAll();


}
