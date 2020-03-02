package com.example.crud;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder> {
    ArrayList<Employee> mDataset;
    Context con;
    Employee justDeleted;
    static View lr;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case

        public TextView name;
        public TextView phone;
        public TextView email;
        public TextView address;
        public TextView id;
        public Button delete;
        public Button update;
        Context con;
        public MyViewHolder(View v) {
            super(v);
//            textView = v;
            con = v.getContext();
            name = itemView.findViewById(R.id.name);

            phone = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
            email =itemView.findViewById(R.id.email);
            id = itemView.findViewById(R.id.id);
            delete = itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.update);
            lr = itemView.findViewById(R.id.my_recycler_view);
//            lr.setLayoutManager(new LinearLayoutManager(con));
//            lr = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public recycleAdapter(ArrayList<Employee> myDataset, Context context, View l) {
        mDataset = myDataset;
        con = context;
        lr = l;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public recycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {


        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.emp, parent, false);

        // Return a new holder instance
        MyViewHolder viewHolder = new MyViewHolder(contactView);
        return viewHolder;
        // create a new view

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.textView.setText(mDataset[position]);
//        Contact contact = mContacts.get(position);
        Employee emp = mDataset.get(position);
        // Set item views based on your views and data model
        holder.name.setText(emp.name);

        holder.phone.setText("Phone : "+ emp.phone);

        holder.email.setText("Email : "+emp.email);

        holder.address.setText("Address : "+emp.address);

        holder.id.setText("ID : "+Integer.toString(emp.id));

        Button delete = holder.delete;
        Button update = holder.update;

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                del(position);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upd(position);
            }


        });
    }
    public  void upd(int pos){
        Intent intent = new Intent(con, update.class);
        String message = String.valueOf(mDataset.get(pos).id);
        intent.putExtra("id", message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        con.startActivity(intent);
    }

    public  void changeData(ArrayList<Employee> myDataset){
        mDataset = myDataset;
        notifyDataSetChanged();
    }
    private void del(int pos) {
        final int p = pos;
        justDeleted = new EmployeeHandler(con).delete(mDataset.get(pos).id);
        mDataset.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos, mDataset.size());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}