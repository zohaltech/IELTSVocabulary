package com.zohaltech.app.ieltsvocabulary.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zohaltech.app.ieltsvocabulary.R;
import com.zohaltech.app.ieltsvocabulary.entities.Example;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder> {
    Context            context;
    ArrayList<Example> examples;

    public ExampleAdapter(Context context, ArrayList<Example> examples) {
        this.context = context;
        this.examples = examples;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_example, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Example example = examples.get(position);
        holder.txtExample.setText(example.getEnglish());
        holder.txtExamplePersian.setText(example.getPersian());
//        holder.txtExample.setText(example.getEncEnglish());
//        holder.txtExamplePersian.setText(example.getEncPersian());
    }

    @Override
    public int getItemCount() {
        return examples.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtExample;
        public TextView txtExamplePersian;

        public ViewHolder(View view) {
            super(view);
            txtExample = (TextView) view.findViewById(R.id.txtExample);
            txtExamplePersian = (TextView) view.findViewById(R.id.txtExamplePersian);
        }
    }
}
