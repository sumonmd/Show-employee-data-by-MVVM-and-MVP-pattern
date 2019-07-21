package com.example.user.internproject.view;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.internproject.R;
import com.example.user.internproject.adapter.FireAdapter;
import com.example.user.internproject.model.FireBaseModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

/*ServerFragment work with FireBaseModel Model, MyFireAdapter and MyDatabase */

public class ServerFragment extends Fragment {

   private RecyclerView recyclerView;
   private DatabaseReference databaseArtists;
   private ArrayList<FireBaseModel> list;
   RecyclerView.Adapter fireAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_server,container,false);
        recyclerView = view.findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<FireBaseModel>();

        databaseArtists = FirebaseDatabase.getInstance().getReference().child("employee");

        // SharedPreference help to insert data in firebase
        SharedPreferences prefs = getActivity().getSharedPreferences("firebase1", MODE_PRIVATE);
        Boolean restoredText = prefs.getBoolean("name1", false);
        if (!restoredText) {
            MyTask myTask = new MyTask();
            myTask.execute();
        }


    // Retrive data from Firebase asynchronously
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
            {

                FireBaseModel p = dataSnapshot1.getValue(FireBaseModel.class);
                list.add(p);
            }
                fireAdapter = new FireAdapter(list,getContext());
                 recyclerView.setAdapter(fireAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"something is wrong",Toast.LENGTH_LONG);
            }
        });

        return view;

    }

    // AsynTask for ServerFragment
    class MyTask extends AsyncTask<Void,Void,Void>{
        public MyTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String id;
            String name;
            String designation;
            String team;
            String image;
            String json;
            try{
                InputStream is = getActivity().getAssets().open("app.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer,"UTF-8");
                JSONArray jsonArray = new JSONArray(json);
                for(int i = 0; i< jsonArray.length();i++){

                    JSONObject employeeObject = jsonArray.getJSONObject(i);

                    id = databaseArtists.push().getKey();
                    name = employeeObject.get("name").toString().trim();
                    designation=employeeObject.get("designation").toString();
                    team = employeeObject.getString("team");
                    image = employeeObject.getString("image");

                    FireBaseModel artist = new FireBaseModel(id, name, designation,team,image);// FireBaseModel is a model for ServerFragment

                    databaseArtists.child(id).setValue(artist);


                }



            } catch (IOException e) {
                e.printStackTrace();
            }catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            SharedPreferences.Editor editor = getActivity().getSharedPreferences("firebase1", MODE_PRIVATE).edit();
            editor.putBoolean("name1", true);
            editor.apply();
        }
    }

}

