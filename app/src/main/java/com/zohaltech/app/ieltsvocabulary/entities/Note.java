package com.zohaltech.app.ieltsvocabulary.entities;

public class Note {
    private int    id;
    private int    vocabularyId;
    private int    ordinal;
    private String Description;

    public Note(int id, int vocabularyId, int ordinal, String description) {
        this(vocabularyId, ordinal, description);
        this.id = id;
    }

    public Note(int vocabularyId, int ordinal, String description) {
        setVocabularyId(vocabularyId);
        setOrdinal(ordinal);
        setDescription(description);
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
