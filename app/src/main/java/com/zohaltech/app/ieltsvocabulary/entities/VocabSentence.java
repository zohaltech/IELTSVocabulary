package com.zohaltech.app.ieltsvocabulary.entities;


public class VocabSentence {
    private int id;
    private int vocabId;
    private int sentenceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVocabId() {
        return vocabId;
    }

    public void setVocabId(int vocabId) {
        this.vocabId = vocabId;
    }

    public int getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(int sentenceId) {
        this.sentenceId = sentenceId;
    }
}
