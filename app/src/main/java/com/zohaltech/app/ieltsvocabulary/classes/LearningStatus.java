package com.zohaltech.app.ieltsvocabulary.classes;


import com.zohaltech.app.ieltsvocabulary.data.Vocabularies;
import com.zohaltech.app.ieltsvocabulary.entities.Vocabulary;
import com.zohaltech.app.ieltsvocabulary.serializables.Reminder;
import com.zohaltech.app.ieltsvocabulary.serializables.ReminderSettings;

import java.util.ArrayList;

public class LearningStatus {
    private int vocabIndex;
    private int progress;
    private int dayIndex;
    private int dayCount;
    private int vocabCount;

    public static LearningStatus getLearningStatusByTheme(int themeId) {
        LearningStatus learningStatus = new LearningStatus();
        ReminderSettings settings = ReminderManager.getReminderSettings();

        if (settings.getStatus() != ReminderSettings.Status.STOP) {
            ArrayList<Vocabulary> vocabularies = Vocabularies.selectByTheme(themeId);
            int vocabCount = vocabularies.size();
            Reminder reminder = ReminderManager.getLastReminder();
            if (reminder != null) {

                int currentVocabId = reminder.getVocabularyId();

                //        if (currentVocabId == 0)
                //            return null;
                Vocabulary currentVocab = Vocabularies.select(currentVocabId);
                assert currentVocab != null;

                if (currentVocab.getThemeId() > themeId) {


                }

                // int vocabIndex = vocabularies.indexOf(currentVocab) + 1;
                int vocabIndex = indexOf(currentVocab, vocabularies) + 1;

                if (settings.getStatus() == ReminderSettings.Status.FINISHED ||
                    currentVocab.getThemeId() > themeId) {
                    learningStatus.setProgress(100);
                    learningStatus.setDayIndex(vocabCount / 6);
                    learningStatus.setDayCount(vocabCount / 6);
                    learningStatus.setVocabIndex(vocabCount);
                    learningStatus.setVocabCount(vocabCount);
                } else {
                    learningStatus.setProgress(vocabIndex * 100 / vocabCount);
                    learningStatus.setDayIndex(currentVocab.getDay());
                    learningStatus.setDayCount(vocabCount / 6);
                    learningStatus.setVocabIndex(vocabIndex);
                    learningStatus.setVocabCount(vocabCount);
                }
            } else {
                learningStatus.setProgress(0);
                learningStatus.setDayIndex(0);
                learningStatus.setDayCount(vocabCount / 6);
                learningStatus.setVocabIndex(0);
                learningStatus.setVocabCount(vocabCount);
            }
        } else {
            return null;
        }

        return learningStatus;
    }

    private static int indexOf(Vocabulary vocabulary, ArrayList<Vocabulary> elementData) {
        if (vocabulary == null) {
            for (int i = 0; i < elementData.size(); i++)
                if (elementData.get(i) == null)
                    return i;
        } else {
            for (int i = 0; i < elementData.size(); i++)
                if (vocabulary.getId() == (elementData.get(i).getId()))
                    return i;
        }
        return -1;
    }

    public int getVocabIndex() {
        return vocabIndex;
    }

    private void setVocabIndex(int vocabIndex) {
        this.vocabIndex = vocabIndex;
    }

    public int getProgress() {
        return progress;
    }

    private void setProgress(int progress) {
        this.progress = progress;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    private void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    public int getDayCount() {
        return dayCount;
    }

    private void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public int getVocabCount() {
        return vocabCount;
    }

    private void setVocabCount(int vocabCount) {
        this.vocabCount = vocabCount;
    }
}
