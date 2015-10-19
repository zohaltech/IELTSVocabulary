package com.zohaltech.app.ieltsvocabulary.entities;

public class Vocabulary {
    private int     id;
    private int     themeId;
    private String  vocabulary;
    private String  vocabEnglishDef;
    private Boolean learned;
    private Boolean bookmarked;


    public Vocabulary(int themeId, String vocabulary, String vocabEnglishDef, Boolean learned, Boolean bookmarked
                     ) {
        setThemeId(themeId);
        setVocabulary(vocabulary);
        setVocabEnglishDef(vocabEnglishDef);
        setLearned(learned);
        setBookmarked(bookmarked);
    }

    public Vocabulary(int id, int themeId, String vocabulary, String vocabEnglishDef, Boolean learned, Boolean bookmarked) {
        this(themeId, vocabulary, vocabEnglishDef, learned, bookmarked);
        this.id = id;
    }

    public Vocabulary(int id, String vocabulary, String vocabEnglishDef) {
        setId(id);
        setVocabulary(vocabulary);
        setVocabEnglishDef(vocabEnglishDef);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }


    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getVocabEnglishDef() {
        return vocabEnglishDef;
    }

    public void setVocabEnglishDef(String vocabEnglishDef) {
        this.vocabEnglishDef = vocabEnglishDef;
    }

    public Boolean getLearned() {
        return learned;
    }

    public void setLearned(Boolean learned) {
        this.learned = learned;
    }

    public Boolean getBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(Boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

}
