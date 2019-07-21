package com.example.user.internproject.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.internproject.R;
import com.example.user.internproject.adapter.MyAdapter;
import com.example.user.internproject.databinding.FragmentLocalBinding;
import com.example.user.internproject.model.PersonModel;
import com.example.user.internproject.mydatabase.MyDatabase;
import com.example.user.internproject.viewmodel.LocalFragmentViewModel;
import com.google.android.gms.common.util.DataUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

/*LocalFragment work with PersonModel,EmployeeDao for room, Myadapter*/

public class LocalFragment extends Fragment {
   private LocalFragmentViewModel localFragmentViewModel;

    @Nullable
    ArrayList<PersonModel> employeeList = new ArrayList<PersonModel>();
    ArrayList<PersonModel> showList = new ArrayList<>();

    FragmentLocalBinding fragmentLocalBinding;

    RecyclerView recyclerView;


    //MyAdapter myAdapter;
    String [] names,designations;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentLocalBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_local,null,false);
        View view = fragmentLocalBinding.getRoot();

        localFragmentViewModel = ViewModelProviders.of(this).get(LocalFragmentViewModel.class);
        fragmentLocalBinding.recyclerViewId.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentLocalBinding.recyclerViewId.setHasFixedSize(true);

       final MyAdapter adapter = new MyAdapter();

       fragmentLocalBinding.recyclerViewId.setAdapter(adapter);
       // data-binding




        localFragmentViewModel.getAllperson().observe(this, new Observer<List<PersonModel>>() {
            @Override
            public void onChanged(List<PersonModel> personModels) {
                Toast.makeText(getContext(),"Welcome to Local Fragment",Toast.LENGTH_LONG).show();
                adapter.setPersonModels(personModels);
                //recyclerView.setAdapter(adapter);
            }
        });

       return view;

    }


    public LocalFragment() {

    }
}

