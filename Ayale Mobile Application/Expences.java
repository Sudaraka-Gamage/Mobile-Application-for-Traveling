package com.example.savindu.ayalenew1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Expences extends AppCompatActivity {

    public static SQLiteHelper mSQLiteHelper;

    Button buttonAdd, buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses);

        buttonView = findViewById(R.id.button_view_expense);
        buttonAdd = findViewById(R.id.button_add_expense);

        //creating database
        mSQLiteHelper = new SQLiteHelper( this,"RECORDDB.sqlite", null,1 );

        //creating table in database
        mSQLiteHelper.queryData( "CREATE TABLE IF NOT EXISTS expenses( id INTEGER PRIMARY KEY AUTOINCREMENT, amount VARCHAR, category VARCHAR, description VARCHAR)" );

        //open add expences
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddExpense();
            }
        });

        //open view expences
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewExpense();
            }
        });
    }

    public void openViewExpense(){
        Intent intent = new Intent(this, ViewExpenses.class);
        startActivity(intent);
    }

    public void openAddExpense(){
        Intent intent = new Intent(this, AddExpense.class);
        startActivity(intent);
    }
}
