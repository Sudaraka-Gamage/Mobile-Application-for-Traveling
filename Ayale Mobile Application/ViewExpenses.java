package com.example.savindu.ayalenew1;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewExpenses extends AppCompatActivity {

    ListView mListView;
    ArrayList<ExpenceModel> mList;
    RecordListAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Expenses");

        mListView = findViewById( R.id.listView );
        mList = new ArrayList<>();
        mAdapter = new RecordListAdapter( this, R.layout.row, mList );
        mListView.setAdapter(mAdapter);

        //get all data from sqlite
        Cursor cursor = Expences.mSQLiteHelper.getData( "SELECT * FROM expenses" );
        mList.clear();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String amount = cursor.getString(1);
            String category = cursor.getString(2);
            String description = cursor.getString(3);

            //add to list
            mList.add(new ExpenceModel(id, amount, category, description ));
        }
        mAdapter.notifyDataSetChanged();

        if(mList.size() == 0){
            //listview is empty (no records in the databse)
            Toast.makeText( this,"No records found!", Toast.LENGTH_SHORT ).show();

        }

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                //alert dialog to display options of update delete
                CharSequence[] items = {"Update","Delete"};

                AlertDialog.Builder dialog = new AlertDialog.Builder( ViewExpenses.this );

                dialog.setTitle("Please select an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            //update
                            Cursor c = Expences.mSQLiteHelper.getData( "SELECT id FROM expenses" );
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }

                            //show update dialog
                            showDialogUpdate(ViewExpenses.this, arrID.get(position));
                        }
                        if (i == 1){
                            //delete
                            Cursor c = Expences.mSQLiteHelper.getData( "SELECT id FROM expenses" );

                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position) );
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

    }

    private void showDialogDelete(final int idRecord) {
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder( ViewExpenses.this );
        dialogDelete.setTitle("Warning!");
        dialogDelete.setMessage("Are you sure to delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try{
                    Expences.mSQLiteHelper.deleteData(idRecord);
                    Toast.makeText( ViewExpenses.this,"Expense deleted successfully", Toast.LENGTH_SHORT ).show();
                }
                catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateRecordList();
            }
        });
        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_dialog);
        dialog.setTitle("Update");

        final EditText txtAmout = dialog.findViewById(R.id.edtAmount);
        final EditText txtCategory = dialog.findViewById(R.id.edtCategory);
        final EditText txtDescription = dialog.findViewById(R.id.edtDescription);
        final Button btnUpdate = dialog.findViewById(R.id.btnUpdate);

        //set width of dialog
        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.95);

        //set height of dialog
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels*0.7);

        //mList.clear();
        Cursor cursor = Expences.mSQLiteHelper.getData("SELECT * FROM expenses WHERE id= " + position );

        if (cursor.moveToNext()){

            txtAmout.setText(cursor.getString(cursor.getColumnIndex("amount")));
            txtCategory.setText(cursor.getString(cursor.getColumnIndex("category")));
            txtDescription.setText(cursor.getString(cursor.getColumnIndex("description")));
        }

        dialog.getWindow().setLayout(width, height);
        dialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            try{
                Expences.mSQLiteHelper.updateData(
                        txtAmout.getText().toString().trim(),
                        txtCategory.getText().toString().trim(),
                        txtDescription.getText().toString().trim(),
                        position
                );
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Expense updated Successfully", Toast.LENGTH_SHORT).show();
            }
            catch (Exception error){
                Log.e("Update error", error.getMessage());
            }
            updateRecordList();
            }
        });
    }

    private void updateRecordList() {

        //get all data from sqlite
        Cursor cursor = Expences.mSQLiteHelper.getData("SELECT * FROM expenses");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String amount = cursor.getString(1);
            String category = cursor.getString(2);
            String description = cursor.getString(3);

            mList.add(new ExpenceModel(id, amount, category, description));
        }
        mAdapter.notifyDataSetChanged();
    }
}
