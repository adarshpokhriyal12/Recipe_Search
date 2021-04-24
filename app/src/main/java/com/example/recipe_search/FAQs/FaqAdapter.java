package com.example.recipe_search.FAQs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recipe_search.R;
import com.example.recipe_search.RecipeAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.MyViewHolder> {

    Context context;

    private List<String> question,answer;
    RecyclerViewClickListener listener;

    public FaqAdapter(Context context,List<String> question,List<String> answer, RecyclerViewClickListener listener)
    {
        this.context = context;
        this.question = question;
        this.answer = answer;
        this.listener = listener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.faq_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView.setText(question.get(position));
        holder.linearLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

    }

    @Override
    public int getItemCount() {
        return question.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.action_container);
            textView = itemView.findViewById(R.id.question);
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

