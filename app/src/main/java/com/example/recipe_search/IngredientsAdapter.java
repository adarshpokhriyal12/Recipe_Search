package com.example.recipe_search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.RecipeViewHolder> {

    Context mContext;
    List<IngredientsData> mData;
    private RecyclerViewClickListener listener;
    public IngredientsAdapter(Context mContext, List<IngredientsData> mData,RecyclerViewClickListener listener) {
        this.mContext = mContext;
        this.mData = mData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.ingredient_items,parent,false);

        return new RecipeViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        // binding data

        // adding animation

        holder.name.setText(mData.get(position).getName());
        holder.quantity.setText(mData.get(position).getQuantity());
        holder.img.setImageResource(mData.get(position).getIngr_image());

        // holder.img.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));

        holder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name,quantity;
        ImageView img;
        RelativeLayout relativeLayout;
        public RecipeViewHolder(@NonNull View itemView) {

            super(itemView);
            relativeLayout = itemView.findViewById(R.id.rel_layout_container);
            name = itemView.findViewById(R.id.ingr_name);
            quantity = itemView.findViewById(R.id.ingr_quantity);
            img = itemView.findViewById(R.id.ingr_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }
    public interface RecyclerViewClickListener{
        void onClick(View v,int position);
    }
}
