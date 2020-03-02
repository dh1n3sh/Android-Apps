package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tx = findViewById(R.id.recordCount);

        EmployeeHandler emp = new EmployeeHandler(this.getApplicationContext());
        tx.setText("Records found : "+emp.getCount());

    }

    public void insert(View view) {
        Intent intent = new Intent(this,InsertActivity.class);

        startActivity(intent);
    }

    public void view(View view) {
        startActivity( new Intent(this,ViewActivity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        TextView tx = findViewById(R.id.recordCount);
        EmployeeHandler emp = new EmployeeHandler(this.getApplicationContext());
        tx.setText("Records found : "+emp.getCount());
    }

    public void delete(View view) {
        startActivity( new Intent(this,ViewActivity.class));

    }

    public void update(View view) {
        startActivity( new Intent(this,ViewActivity.class));
    }


}
