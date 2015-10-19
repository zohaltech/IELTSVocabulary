package com.zohaltech.app.ieltsvocabulary.entities;

public class Example {
    private int    id;
    private int    vocabularyId;
    private int    ordinal;
    private String english;
    private String persian;

    public Example(int id, int vocabularyId, int ordinal, String english, String persian) {
        this(vocabularyId, ordinal, english, persian);
        this.id = id;
    }

    public Example(int vocabularyId, int ordinal, String english, String persian) {
        setVocabularyId(vocabularyId);
        setOrdinal(ordinal);
        setEnglish(english);
        setPersian(persian);
    }

    public Example(int vocabularyId, String english, String persian) {
        setVocabularyId(vocabularyId);
        setEnglish(english);
        setPersian(persian);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(int vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getPersian() {
        return persian;
    }

    public void setPersian(String persian) {
        this.persian = persian;
    }
}
