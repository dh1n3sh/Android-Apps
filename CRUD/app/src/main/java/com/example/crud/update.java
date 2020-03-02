package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class update extends AppCompatActivity {
    String id;
    EmployeeHandler eh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Snackbar.make(findViewById(android.R.id.content), "Going to update "+id, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED)

                .show()
        ;
        TextView tx = findViewById(R.id.idf);
        tx.setText("ID : "+id);

        eh = new EmployeeHandler(this.getApplicationContext());
        Employee emp = eh.find(id);
        EditText e1 = findViewById(R.id.name);
        EditText e2 = findViewById(R.id.phone);
        EditText e3 = findViewById(R.id.address);
        EditText e4 = findViewById(R.id.email);
        e1.setText(emp.name);
        e2.setText(emp.phone);
        e3.setText(emp.address);
        e4.setText(emp.email);

    }

    public void update(View view) {

        EditText name = findViewById(R.id.name);
        EditText phone = findViewById(R.id.phone);
        EditText address = findViewById(R.id.address);
        EditText email = findViewById(R.id.email);
        Employee emp = new Employee();
        emp.id = Integer.parseInt(id);
        emp.name = name.getText().toString();
        emp.phone = phone.getText().toString();
        emp.address = address.getText().toString();
        emp.email = email.getText().toString();

        Boolean createSuccessful = eh.update(emp);
        name.setText("");
        phone.setText("");
        email.setText("");
        address.setText("");
        if(createSuccessful){
            Snackbar.make(findViewById(android.R.id.content), "Employee Info was updated in the table "+emp.name, Snackbar.LENGTH_LONG)
                    .setActionTextColor(Color.RED)


                    .setDuration(BaseTransientBottomBar.LENGTH_LONG)
                    .show();
//            Toast.makeText(this.getApplicationContext(), "Employee information was saved."+emp.toString()+emp.name, Toast.LENGTH_SHORT).show();
        }else{
            Snackbar.make(findViewById(android.R.id.content), "Employee Info was not updated in the table ", Snackbar.LENGTH_LONG)
                    .setActionTextColor(Color.RED)

                    .show()
            ;
//            Toast.makeText(this.getApplicationContext(), "Unable to save Employee information.", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);    }
}
