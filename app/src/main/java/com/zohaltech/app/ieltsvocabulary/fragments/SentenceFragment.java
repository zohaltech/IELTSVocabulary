package com.zohaltech.app.ieltsvocabulary.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zohaltech.app.ieltsvocabulary.R;
import com.zohaltech.app.ieltsvocabulary.adapters.SentenceAdapter;
import com.zohaltech.app.ieltsvocabulary.data.Sentences;
import com.zohaltech.app.ieltsvocabulary.entities.Sentence;

import java.util.ArrayList;

public class SentenceFragment extends Fragment {
    public static final String VOCAB_ID = "VOCAB_ID";

    public static SentenceFragment newInstance(int vocabId) {
        Bundle args = new Bundle();
        args.putInt(VOCAB_ID, vocabId);
        SentenceFragment fragment = new SentenceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sentence, container, false);
        RecyclerView recyclerExamples = (RecyclerView) view.findViewById(R.id.recyclerExamples);
        recyclerExamples.setHasFixedSize(true);
        recyclerExamples.setLayoutManager(new LinearLayoutManager(getActivity()));
        int vocabId = getArguments().getInt(VOCAB_ID);
        ArrayList<Sentence> sentences = Sentences.getVocabSentences(vocabId);
        SentenceAdapter adapter = new SentenceAdapter(getActivity(), sentences);
        recyclerExamples.setAdapter(adapter);
        return view;
    }
}
