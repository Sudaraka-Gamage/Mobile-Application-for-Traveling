package com.example.savindu.ayalenew1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.savindu.ayalenew1.Expences.mSQLiteHelper;

public class AddExpense extends AppCompatActivity {

    EditText editAmount, editCat, editDes;
    Button buttonadd, buttocancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add New Expense");

        buttonadd = findViewById(R.id.button_add);
        buttocancel = findViewById(R.id.button_cancel);
        editAmount = findViewById(R.id.editAmount);
        editCat = findViewById(R.id.editCat);
        editDes = findViewById(R.id.editDes);

        //add record to sqlite
        buttonadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try{
                    mSQLiteHelper.insertData(
                            editAmount.getText().toString().trim(),
                            editCat.getText().toString().trim(),
                            editDes.getText().toString().trim()
                    );
                    Toast.makeText( AddExpense.this, "Expense added successfully!", Toast.LENGTH_SHORT).show();

                    //reset views
                    editAmount.setText("");
                    editCat.setText("");
                    editDes.setText("");
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                //show expenses activiry
                openExpense();
            }
        });

        //show expenses activiry
        buttocancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openExpense();
            }
        });
    }

    public void openExpense() {
        Intent intent = new Intent(this, Expences.class);
        startActivity(intent);
    }
}