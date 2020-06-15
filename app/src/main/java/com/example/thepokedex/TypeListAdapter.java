package com.example.thepokedex;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TypeListAdapter extends RecyclerView.Adapter<TypeListAdapter.TypeViewHolder> {
    Context context;

    PokeType pokeType;

    public TypeListAdapter(Context context, PokeType pokeType) {
        this.context = context;
        this.pokeType = pokeType;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.type_item, parent, false);
        return new TypeListAdapter.TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, final int position) {
        holder.typeItem.setText(pokeType.getResults().get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TypeDetailActivity.class);
                intent.putExtra("typename",pokeType.getResults().get(position).getName().toUpperCase());
                intent.putExtra("id",position+1);
                context.startActivity(intent);
            }
        });
//        final String name = types.get(position).getName().toUpperCase();
//        holder.type.setChipText(types.get(position).getName().toUpperCase());
//        int color = DetailTypeAdapter.getColorByType(types.get(position).getName());
//        holder.type.changeBackgroundColor(color);
//        holder.chipBg.setBackgroundColor(color);
//        holder.type.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, TypeDetailActivity.class);
//                intent.putExtra("typename",name);
//                intent.putExtra("id",position+1);
//                context.startActivity(intent);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return pokeType.getResults().size();
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder {
        TextView typeItem;

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            typeItem = itemView.findViewById(R.id.typeItem);
        }

    }

}