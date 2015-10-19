package com.zohaltech.app.ieltsvocabulary.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zohaltech.app.ieltsvocabulary.R;
import com.zohaltech.app.ieltsvocabulary.adapters.ExampleAdapter;
import com.zohaltech.app.ieltsvocabulary.data.Examples;
import com.zohaltech.app.ieltsvocabulary.entities.Example;

import java.util.ArrayList;

public class ExamplesFragment extends Fragment {
    public static final String VOCAB_ID = "VOCAB_ID";

    public static ExamplesFragment newInstance(int vocabId) {
        Bundle args = new Bundle();
        args.putInt(VOCAB_ID, vocabId);
        ExamplesFragment fragment = new ExamplesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_examples, container, false);
        RecyclerView recyclerExamples = (RecyclerView) view.findViewById(R.id.recyclerExamples);
        recyclerExamples.setHasFixedSize(true);
        recyclerExamples.setLayoutManager(new LinearLayoutManager(getActivity()));
        int vocabId = getArguments().getInt(VOCAB_ID);
        ArrayList<Example> examples = Examples.getExamples(vocabId);
        ExampleAdapter adapter = new ExampleAdapter(getActivity(), examples);
        recyclerExamples.setAdapter(adapter);
        return view;
    }
}
