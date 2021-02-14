package com.example.volleyexample;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends BaseAdapter {


    ArrayList<Data> arrayList;
    Context context;

    LayoutInflater layoutInflater;

    public DataAdapter(ArrayList<Data> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (layoutInflater == null){
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
        convertView = layoutInflater.inflate(R.layout.mylist,parent,false);
        }

        MyViewHolder myViewHolder = new MyViewHolder(convertView);
        myViewHolder.name.setText("Name :"+arrayList.get(position).getName());
        myViewHolder.department.setText("Department :"+arrayList.get(position).getDepartment());
        myViewHolder.salary.setText("Salary :"+arrayList.get(position).getSalary());

        return convertView;
    }


    public  class MyViewHolder{
        TextView name,salary,department;
        ImageView imageView;

        public MyViewHolder(View view) {

            name= view.findViewById(R.id.textView2);
            salary = view.findViewById(R.id.textView3);
            department = view.findViewById(R.id.textView4);
            imageView = view.findViewById(R.id.imageView);
        }




    }


}
