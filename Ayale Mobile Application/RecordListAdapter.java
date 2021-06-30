package com.example.savindu.ayalenew1;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecordListAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private ArrayList<ExpenceModel> recordList;

    public RecordListAdapter(Context context, int layout, ArrayList<ExpenceModel> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView txtAmount, txtCategory, txtDescription;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null){
            LayoutInflater inflater= (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            row = inflater.inflate(layout, null);
            holder.txtAmount = row.findViewById(R.id.txtAmount);
            holder.txtCategory = row.findViewById(R.id.txtCategory);
            holder.txtDescription = row.findViewById(R.id.txtDescription);
            row.setTag( holder );
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        ExpenceModel expenceModel = recordList.get(i);

        holder.txtAmount.setText(expenceModel.getAmount());
        holder.txtCategory.setText(expenceModel.getCategory());
        holder.txtDescription.setText(expenceModel.getDescription());

        return row;
    }
}
