package com.example.asus_pc.myproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;


    private Button button;
    private Button button2;
    private Button button3;

    Button button_viewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myDb = new DataBaseHelper(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.button_insert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });





        button_viewAll = (Button)findViewById(R.id.button_mainView);
        ViewAll();

       button3 = findViewById(R.id.button_update);
       button3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openActivity4();
           }
       });



    }

   public void ViewAll(){

        button_viewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0){
                            //show message
                            showMessage("Error", "Nothing Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){

                            buffer.append("Id :" +res.getString(0)+"\n");
                            buffer.append("name:" +res.getString(1)+"\n");
                            buffer.append("date :" +res.getString(2)+"\n");
                            buffer.append("detail :" +res.getString(3)+"\n");
                            buffer.append("summary :" +res.getString(4)+"\n");
                            buffer.append("\n");
                            buffer.append("\n");

                            }

                            //show all data
                            showMessage("Data", buffer.toString());

                    }

                }


        );


    }

    public void showMessage(String title, String Message){

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }






    public void openActivity3(){
        Intent intent = new Intent(this, insert.class);
        startActivity(intent);
    }

    public void openActivity4(){
        Intent intent = new Intent(this,update.class);
        startActivity(intent);
    }


}
