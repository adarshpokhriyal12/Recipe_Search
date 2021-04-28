package com.example.recipe_search.ALL_FORMS;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recipe_search.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AllFormAdapter extends RecyclerView.Adapter<AllFormAdapter.FormViewHolder> {
    List<String> AllForms = new ArrayList<>();

    public AllFormAdapter(List<String> allForms) {
        this.AllForms = allForms;
    }

    @NonNull
    @Override
    public FormViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_form_items,parent,false);
        FormViewHolder formViewHolder = new FormViewHolder(view);
        return formViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FormViewHolder holder, int position) {
        holder.formtv.setText(AllForms.get(position));
    }

    @Override
    public int getItemCount() {
        return AllForms.size();
    }

    public static class FormViewHolder extends RecyclerView.ViewHolder{

        TextView formtv;
        public FormViewHolder(@NonNull View itemView) {
            super(itemView);
            formtv = itemView.findViewById(R.id.forms);
        }
    }
}
