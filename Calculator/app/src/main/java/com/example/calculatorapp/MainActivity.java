package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import android.util.Log;

import com.google.android.material.snackbar.Snackbar;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.one);
        EditText ed = findViewById(R.id.input);
        ed.setSelection(0);

    }

    public void numPressed(View view) {
        EditText ed = findViewById(R.id.input);
//        Toast.makeText(this, ((Button)view).getText().toString(), Toast.LENGTH_SHORT).show();
//        ed.setText(ed.getText().toString()+((Button)view).getText().toString());
       int cur = ed.getSelectionStart();
       String current = ed.getText().toString();
       String newStr = current.substring(0,cur)+((Button)view).getText().toString()+current.substring((cur),current.length());
       ed.setText(newStr);
        ed.setSelection(cur+1);
    }

    public void reset(View view) {
        EditText ed = findViewById(R.id.input);
        ed.setText("");
        ed.setSelection(ed.getText().length());
    }

    public void calculate(View view) {
        EditText resultTextView = findViewById(R.id.input);
        String currentText = resultTextView.getText().toString();
//        boolean valid = checkForValidity();
        double result=0;
        if(true){
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                try{
                result = (double)engine.eval(currentText);


                    currentText = Double.toString(result);

                    if(result%1==0)
                    { currentText = String.format("%.0f", result);}
                    else  currentText = String.format("%.3f", result);
                    resultTextView.setText(currentText);
            }catch(Exception e){
//                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
//                    ((EditText)findViewById(R.id.input)).setText("Invalid Expression");
                    Snackbar.make((View)findViewById(R.id.coordinator),"Invalid Expression",Snackbar.LENGTH_LONG).setActionTextColor(Color.MAGENTA).setDuration(2000).show();
            }

            resultTextView.setSelection(resultTextView.getText().length());

        }

    }

    public void backsp(View view) {
        EditText ed = findViewById(R.id.input);
//        Toast.makeText(this, ((Button)view).getText().toString(), Toast.LENGTH_SHORT).show();
//        ed.setText(ed.getText().toString()+((Button)view).getText().toString());
        try {
            int cur = ed.getSelectionStart();
            String current = ed.getText().toString();
            String newStr = current.substring(0, cur-1) + current.substring((cur ));
            ed.setText(newStr);
            ed.setSelection(cur-1);
        }
        catch (Exception e)
        {
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
//

    }
}
