package com.example.weather.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.R;

import java.util.ArrayList;

import butterknife.BindView;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private ArrayList<Model> model ;
    private OnItemClickL listner;

    public interface OnItemClickL{
        void onItemDelete(int position);
    }

    public void setOnItemClickL(OnItemClickL onItemClickL) {
        listner = onItemClickL;
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView name;
        TextView direction;
        TextView speed;
        TextView temperature;
        Button delete;

        public Holder(@NonNull final View itemView, final OnItemClickL listner) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            direction = itemView.findViewById(R.id.direction);
            speed = itemView.findViewById(R.id.speed);
            temperature = itemView.findViewById(R.id.temperature);
            delete = itemView.findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listner.onItemDelete(position);
                        }
                    }
                }
            });
        }
        }

    public Adapter(ArrayList<Model> model){
        this.model = model;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter, viewGroup, false);

        return new Holder(v,listner);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

        Model model1 = model.get(i);

        holder.name.setText(model1.getCityName());
        holder.temperature.setText(model1.getTemperature());
        holder.speed.setText(model1.getWindSpeed());
        holder.direction.setText(model1.getWindDirection());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }
}
