package com.zohaltech.app.ieltsvocabulary.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zohaltech.app.ieltsvocabulary.R;
import com.zohaltech.app.ieltsvocabulary.entities.Sentence;

import java.util.ArrayList;

public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.ViewHolder> {
    Context             context;
    ArrayList<Sentence> sentences;

    public SentenceAdapter(Context context, ArrayList<Sentence> sentences) {
        this.context = context;
        this.sentences = sentences;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_sentence, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Sentence sentence = sentences.get(position);
        holder.txtExample.setText(Html.fromHtml(sentence.getText()));
      //  holder.txtExamplePersian.setText(example.getPersian());
//        holder.txtExample.setText(example.getEncEnglish());
//        holder.txtExamplePersian.setText(example.getEncPersian());
    }

    @Override
    public int getItemCount() {
        return sentences.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtExample;
        public TextView txtExamplePersian;

        public ViewHolder(View view) {
            super(view);
            txtExample = (TextView) view.findViewById(R.id.txtExample);
           // txtExamplePersian = (TextView) view.findViewById(R.id.txtExamplePersian);
        }
    }
}
