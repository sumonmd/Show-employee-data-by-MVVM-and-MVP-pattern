package com.example.user.internproject.mydatabase;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.example.user.internproject.model.PersonModel;
import com.example.user.internproject.view.LocalFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import static android.content.Context.MODE_PRIVATE;
import static com.parse.Parse.getApplicationContext;

@Database(entities = {PersonModel.class},version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase instance;
    public abstract EmployeeDao employeeDao();


    public static synchronized MyDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"MyDatabase").allowMainThreadQueries().fallbackToDestructiveMigration().addCallback(callback).build();
        }
        return instance;
    }
    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsynTask(instance).execute();
        }
    };
    private static class PopulateDbAsynTask extends AsyncTask<Void,Void,Void>{
        private EmployeeDao employeeDao;
        private PopulateDbAsynTask(MyDatabase db){
            employeeDao = db.employeeDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {


            String json;
            try{
                InputStream is = getApplicationContext().getAssets().open("app.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer,"UTF-8");
                JSONArray jsonArray = new JSONArray(json);
                for(int i = 0; i< jsonArray.length();i++){

                    JSONObject employeeObject = jsonArray.getJSONObject(i);

                    employeeDao.addUser(new PersonModel(employeeObject.getString("name"),employeeObject.getString("designation"),employeeObject.getString("team"),employeeObject.getString("image")));


                }
//                MainActivity.myDatabase.employeeDao().addUser(employeeList);
               /* SharedPreferences.Editor editor = getActivity().getSharedPreferences("firebase", MODE_PRIVATE).edit();
                editor.putBoolean("name", true);
                editor.apply();*/


            } catch (IOException e) {
                e.printStackTrace();
            }catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }





        }

}
