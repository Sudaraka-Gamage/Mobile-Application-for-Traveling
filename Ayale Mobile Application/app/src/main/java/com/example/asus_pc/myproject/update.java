package com.example.asus_pc.myproject;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {

    DataBaseHelper myDb;

    Button button_updateview;
    Button button_Update;
    Button button_Delete;

    EditText editText_id, editText_name, editText_details, editText_date, editText_summary;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        myDb = new DataBaseHelper(this);

        button_Update = (Button)findViewById(R.id.btnUpdataData);
        button_updateview = (Button)findViewById(R.id.btnupdateview);
        button_Delete = (Button)findViewById(R.id.btndelete);

        viewAllUpdate();
        UpdateData();
        DeleteData();

      }


    public void viewAllUpdate(){

        button_updateview.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0){
                            //show Message
                            showMessage("Error", "Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){

                            buffer.append("Id :" + res.getString(0)+"\n");
                            buffer.append("Name :" + res.getString(1)+"\n");
                            buffer.append("Details :" + res.getString(2)+"\n");
                            buffer.append("Date :" + res.getString(3)+"\n");
                            buffer.append("Summary :" + res.getString(4)+"\n");




                        }

                        //show all data
                        showMessage("Data", buffer.toString());
                    }
                }

        );


    }

    public void showMessage(String title, String Message){

      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setCancelable(true);
       builder.setTitle(title);
       builder.setMessage(Message);
       builder.show();

       }

       public void UpdateData(){

        button_Update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editText_id.getText().toString(),
                                editText_name.getText().toString(),editText_details.getText().toString(),editText_date.getText().toString()
                                ,editText_summary.getText().toString()  );

                            if(isUpdate == true) {

                                Toast.makeText(update.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            }
                             else{
                                 Toast.makeText(update.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                            }

                    }
                }
        );
    }

    public void DeleteData(){

        button_Delete.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Integer deleteRows = myDb.deleteData(editText_id.getText().toString());

                        if(deleteRows > 0)
                            Toast.makeText(update.this,"Data Deleted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(update.this,"Data not Deleted", Toast.LENGTH_LONG).show();

                    }
                }


        );




    }


}
