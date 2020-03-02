package com.example.crud;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
//import androidx.appcompat.widget.SearchView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Date;
import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Employee> Dataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view);
        SearchView sr = (SearchView) findViewById(R.id.searchV);
        final Context con = this.getApplicationContext();
        sr.setIconified(false);
        sr.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s == "")
                {
                    Dataset = new EmployeeHandler(con).retrieve();

                }
                else
                {
                    Dataset = new EmployeeHandler(con).retrieve();
                    ArrayList<Employee> newDataset = new ArrayList<Employee>();
                    for (int i=0;i<Dataset.size();++i)
                    {
                        if(Dataset.get(i).name.indexOf(s)!=-1)
                        {
                            newDataset.add(Dataset.get(i));
                        }
                    }
                    Dataset = newDataset;

                }
                ((recycleAdapter)mAdapter).changeData(Dataset);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                Toast.makeText(con, s, Toast.LENGTH_SHORT).show();
                if(s == "")
                {
                    Dataset = new EmployeeHandler(con).retrieve();

                }
                else
                {
                    Dataset = new EmployeeHandler(con).retrieve();
                    ArrayList<Employee> newDataset = new ArrayList<Employee>();
                    for (int i=0;i<Dataset.size();++i)
                    {
                        if(Dataset.get(i).name.indexOf(s)!=-1)
                        {
                            newDataset.add(Dataset.get(i));
                        }
                    }
                    Dataset = newDataset;

                }
                ((recycleAdapter)mAdapter).changeData(Dataset);
                return false;
            }
        });

        try {
            recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            recyclerView.setHasFixedSize(true);

            // use a linear layout manager
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            // specify an adapter (see also next example)
            Dataset = new EmployeeHandler(this.getApplicationContext()).retrieve();
            //        Dataset.add("First");
            //        Dataset.add("Second");
            //        String[] myDataset = new String[Dataset.size()];
            //        myDataset = Dataset.toArray(myDataset);
            mAdapter = new recycleAdapter(Dataset,this.getApplicationContext(),findViewById(android.R.id.content));
            recyclerView.setAdapter(mAdapter);
            View vl = findViewById(android.R.id.content);
        }catch (Exception e) {
            Snackbar.make(findViewById(android.R.id.content), e.getMessage(), Snackbar.LENGTH_LONG)
                    .setActionTextColor(Color.RED)

                    .show();
        }
    }

    public void search(View view) {

        ((recycleAdapter)mAdapter).changeData(Dataset);
        mAdapter.notifyDataSetChanged();
    }


//    sr.setOnQueryTextListner(new SearchView.OnQueryTextListener(){
//        @Override
//        public boolean onQueryTextSubmit(String q)
//        {
//            Toast.makeText(this, "searched", Toast.LENGTH_SHORT).show();
//        }
//    });

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);
    }
}
