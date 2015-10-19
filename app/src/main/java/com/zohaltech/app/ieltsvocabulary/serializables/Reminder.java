package com.zohaltech.app.ieltsvocabulary.serializables;

import java.io.Serializable;
import java.util.Date;

public class Reminder implements Serializable {
    private int     vocabularyId;
    private Date    time;
    private String  title;
    private String  message;
    private boolean triggerNext;

    public Reminder(int vocabularyId, Date time, String title, String message, boolean triggerNext) {
        setVocabularyId(vocabularyId);
        setTime(time);
        setTitle(title);
        setMessage(message);
        setTriggerNext(triggerNext);
    }

    public int getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(int vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean doesTriggersNext() {
        return triggerNext;
    }

    public void setTriggerNext(boolean triggerNext) {
        this.triggerNext = triggerNext;
    }
}
