package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View view) {
        EditText name = findViewById(R.id.name);
        EditText phone = findViewById(R.id.phone);
        EditText address = findViewById(R.id.address);
        EditText email = findViewById(R.id.email);

        Employee emp = new Employee();

        emp.name = name.getText().toString();
        emp.phone = phone.getText().toString();
        emp.address = address.getText().toString();
        emp.email = email.getText().toString();
        final EmployeeHandler empHandler = new EmployeeHandler(this.getApplicationContext());
        Boolean createSuccessful = empHandler.insert(emp);
        name.setText("");
        phone.setText("");
        email.setText("");
        address.setText("");
        if(createSuccessful){
            Snackbar.make(findViewById(android.R.id.content), "Employee Info was inserted into the table "+emp.name, Snackbar.LENGTH_LONG)
                    .setActionTextColor(Color.RED)

                    .setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                empHandler.deleteLast();
//                            EmployeeHandler EmpHandle = new EmployeeHandler(this.getApplicationContext());
                        }
                    })
                    .setDuration(BaseTransientBottomBar.LENGTH_LONG)
                    .show();
//            Toast.makeText(this.getApplicationContext(), "Employee information was saved."+emp.toString()+emp.name, Toast.LENGTH_SHORT).show();
        }else{
            Snackbar.make(findViewById(android.R.id.content), "Employee Info was not inserted into the table ", Snackbar.LENGTH_LONG)
                    .setActionTextColor(Color.RED)

                    .show()
                    ;
//            Toast.makeText(this.getApplicationContext(), "Unable to save Employee information.", Toast.LENGTH_SHORT).show();
        }
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


}
