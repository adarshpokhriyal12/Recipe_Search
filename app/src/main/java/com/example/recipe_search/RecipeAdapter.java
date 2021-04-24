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

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    Context mContext;
    List<RecipeData> mData;
    private RecyclerViewClickListener listener;
    public RecipeAdapter(Context mContext, List<RecipeData> mData,RecyclerViewClickListener listener) {
        this.mContext = mContext;
        this.mData = mData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.recipe_items,parent,false);

        return new RecipeViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        // binding data

        // adding animation

        holder.title.setText(mData.get(position).getTitle());
        //holder.content.setText(mData.get(position).getContent());
        //holder.servings.setText(mData.get(position).getServing());
        holder.img.setImageResource(mData.get(position).getRecipeImg());

       // holder.img.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));

        holder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;//,content,servings;
        ImageView img;
        RelativeLayout relativeLayout;
        public RecipeViewHolder(@NonNull View itemView) {

            super(itemView);
            relativeLayout = itemView.findViewById(R.id.action_container);
            title = itemView.findViewById(R.id.recipe_title);
            //content = itemView.findViewById(R.id.recipe_desc);
            //servings = itemView.findViewById(R.id.recipe_servings);
            img = itemView.findViewById(R.id.img_recipe);
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
