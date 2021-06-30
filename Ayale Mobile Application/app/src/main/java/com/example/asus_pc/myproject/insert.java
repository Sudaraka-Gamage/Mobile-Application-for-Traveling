package com.example.asus_pc.myproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insert extends AppCompatActivity {


    DataBaseHelper myDb;

    EditText  editDes_Name, editDetails, editDate, editSummary;
    Button button_insert;
    Button button_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        myDb = new DataBaseHelper(this);


        editDes_Name = (EditText) findViewById(R.id.editText_name);
        editDetails = (EditText) findViewById(R.id.editText_details);
        editDate = (EditText) findViewById(R.id.editText_date);
        editSummary = (EditText) findViewById(R.id.editText_summary);
        button_insert = (Button)findViewById(R.id.btnAddData);
        button_view  = (Button)findViewById(R.id.btnviewdata);

        AddData();
        viewAll();


    }
    //data inserting method----------------------------------------------------------------
    public void AddData() {

        button_insert.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v){


                        boolean isInserted = myDb.insertData(editDes_Name.getText().toString(),
                                editDetails.getText().toString(),
                                editDate.getText().toString(),
                                editSummary.getText().toString());

                        if (isInserted = true)
                            Toast.makeText(insert.this,"Data Inserted ", Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(insert.this,"Data not Inserted", Toast.LENGTH_LONG).show();



                    }

                }


        );


   }
   //-----------------------------------------------------------------------------------------------------------------



    //data retrive method---------------------------------------------------------------------------------------------

    public void viewAll(){

        button_view.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0){
                            //show message
                            showMessage("Error", "Nothing Found");
                            return;
                        }
                        StringBuffer buffer= new StringBuffer();
                        while(res.moveToNext()){

                           //buffer.append("Destination Id :" + res.getString(1)+"\n");
                           // buffer.append("Destination Name :" + res.getString(1)+"\n");
                            //buffer.append("Destination Details :" + res.getString(3)+"\n");
                            //buffer.append("Date :" + res.getString(4)+"\n");
                            //buffer.append("Summary :" + res.getString(5)+"\n\n");




                        }
                        //show all data
                        showMessage("All Details Of Destinations", buffer.toString());
                    }
                }


        );

    }
    //message for view
    public void showMessage(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }





    //------------------------------------------------------------------------------------------------------------





}
