package com.example.movieapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Models.Rates;
import com.example.movieapp.R;

import java.util.List;

public class RatesRecyclerAdapter  extends RecyclerView.Adapter<RatesViewHolder>{


    Context context;
    List<Rates> list;

    public RatesRecyclerAdapter(Context context, List<Rates> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RatesViewHolder(LayoutInflater.from(context).inflate(R.layout.rating_list, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RatesViewHolder holder, int position) {
        holder.textView_source.setText(list.get(position).getSource());
        holder.textView_rate.setText(list.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class RatesViewHolder extends RecyclerView.ViewHolder{
TextView textView_source,textView_rate;


    public RatesViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_rate=itemView.findViewById(R.id.textView_rate);
        textView_source=itemView.findViewById(R.id.textView_source);

    }
}