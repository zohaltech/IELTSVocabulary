package com.zohaltech.app.ieltsvocabulary.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zohaltech.app.ieltsvocabulary.R;
import com.zohaltech.app.ieltsvocabulary.activities.VocabularyDetailsActivity;
import com.zohaltech.app.ieltsvocabulary.classes.App;
import com.zohaltech.app.ieltsvocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.ViewHolder> {
    Context               context;
    ArrayList<Vocabulary> vocabularies;
    Boolean               showExtra;

    public VocabularyAdapter(Context context, ArrayList<Vocabulary> vocabularies, Boolean showExtra) {
        this.context = context;
        this.vocabularies = vocabularies;
        this.showExtra = showExtra;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_vocabulary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Vocabulary vocabulary = vocabularies.get(position);
        if (showExtra && position % 6 == 0) {
            holder.txtSection.setVisibility(View.VISIBLE);
            holder.txtSection.setText("DAY " + vocabulary.getDay());
        } else {
            holder.txtSection.setVisibility(View.GONE);
        }

        if (vocabulary.getLearned()) {
            holder.imgLearned.setVisibility(View.VISIBLE);
        } else {
            holder.imgLearned.setVisibility(View.GONE);
        }

        holder.txtVocabulary.setText(vocabulary.getVocabulary());
        holder.layoutVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.currentActivity, VocabularyDetailsActivity.class);
                intent.putExtra(VocabularyDetailsActivity.VOCAB_ID, vocabulary.getId());
                intent.putExtra(VocabularyDetailsActivity.INIT_MODE_KEY, VocabularyDetailsActivity.MODE_VIEW);
                App.currentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vocabularies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView  txtSection;
        public TextView  txtVocabulary;
        public ImageView imgLearned;
        LinearLayout layoutVocabulary;

        public ViewHolder(View view) {
            super(view);
            txtSection = (TextView) view.findViewById(R.id.txtSection);
            layoutVocabulary = (LinearLayout) view.findViewById(R.id.layoutVocabulary);
            txtVocabulary = (TextView) view.findViewById(R.id.txtVocabulary);
            imgLearned = (ImageView) view.findViewById(R.id.imgLearned);
        }
    }
}
