package com.example.cc.railwaysapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Viewholder> {


    Context context;
    List<Modelclass> modelclass;


    public MyAdapter(Context context, List<Modelclass> modelclass) {
        this.context = context;
        this.modelclass = modelclass;

    }

    @NonNull
    @Override
    public MyAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items, viewGroup, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.Viewholder viewholder, int i) {
        viewholder.tv1.setText(modelclass.get(i).getTv1());
        viewholder.tv2.setText(modelclass.get(i).getTv2());
        viewholder.tv3.setText(modelclass.get(i).getTv3());

    }

    @Override
    public int getItemCount() {
        return modelclass.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView tv1;
        TextView tv2;
        TextView tv3;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.textview1);
            tv2 = (TextView) itemView.findViewById(R.id.textview2);
            tv3 = (TextView) itemView.findViewById(R.id.textview3);

        }
    }
}
