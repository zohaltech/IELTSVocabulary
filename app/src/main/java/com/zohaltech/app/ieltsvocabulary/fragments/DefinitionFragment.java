package com.zohaltech.app.ieltsvocabulary.fragments;


import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zohaltech.app.ieltsvocabulary.R;
import com.zohaltech.app.ieltsvocabulary.data.Vocabularies;
import com.zohaltech.app.ieltsvocabulary.entities.Vocabulary;

import java.util.Locale;

public class DefinitionFragment extends Fragment implements
                                                      TextToSpeech.OnInitListener {
    public static final String VOCAB_ID = "VOCAB_ID";
    TextView txtVocabEnglishDefinition;
     private Vocabulary vocabulary;
    private TextToSpeech textToSpeech;


    public static DefinitionFragment newInstance(int vocabId) {
        Bundle args = new Bundle();
        args.putInt(VOCAB_ID, vocabId);
        DefinitionFragment fragment = new DefinitionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_definition, container, false);

        txtVocabEnglishDefinition = (TextView) view.findViewById(R.id.txtVocabEnglishDefinition);
        Button btnSpeechUS = (Button) view.findViewById(R.id.btnSpeechUS);
        Button btnSpeechUK = (Button) view.findViewById(R.id.btnSpeechUK);

        int vocabId = getArguments().getInt(VOCAB_ID);
        vocabulary = Vocabularies.select(vocabId);

        textToSpeech = new TextToSpeech(getActivity(), this);

        assert vocabulary != null;
        txtVocabEnglishDefinition.setText(vocabulary.getVocabEnglishDef());

        btnSpeechUK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.setLanguage(Locale.UK);
                speakOut();
            }
        });

        btnSpeechUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.setLanguage(Locale.US);
                speakOut();
            }
        });

        return view;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = textToSpeech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }
            //else {
            //    speakOut();
            //}

        } else {
            Log.e("TTS", " Failed!");
        }
    }

    private void speakOut() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(vocabulary.getVocabulary(), TextToSpeech.QUEUE_FLUSH, null);
        } else{
            textToSpeech.speak(vocabulary.getVocabulary(), TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }
}
