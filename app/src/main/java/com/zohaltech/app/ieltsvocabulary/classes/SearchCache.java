package com.zohaltech.app.ieltsvocabulary.classes;

import com.zohaltech.app.ieltsvocabulary.data.Examples;
import com.zohaltech.app.ieltsvocabulary.data.Notes;
import com.zohaltech.app.ieltsvocabulary.data.Vocabularies;
import com.zohaltech.app.ieltsvocabulary.entities.Example;
import com.zohaltech.app.ieltsvocabulary.entities.Note;
import com.zohaltech.app.ieltsvocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class SearchCache
{
    private static ArrayList<Vocabulary> vocabulariesCache = null;
    private static ArrayList<Example> exampleCache = null;
    private static ArrayList<Note> notesCache = null;

    public static void initialise()
    {
        if (vocabulariesCache == null)
        {
            vocabulariesCache = Vocabularies.select();
        }
        if (exampleCache == null)
        {
            exampleCache = Examples.select();
        }
        if (notesCache == null)
        {
            notesCache = Notes.select();
        }
    }

    public static ArrayList<Vocabulary> searchFromCache(String searchText)
    {
        searchText = searchText.toLowerCase();
        ArrayList<Vocabulary> searchResult = new ArrayList<>();

        for (int i = 0; i < vocabulariesCache.size(); i++)
        {
            Vocabulary vocabulary = vocabulariesCache.get(i);
            if (vocabulary.getVocabulary().toLowerCase().contains(searchText)
                    || vocabulary.getVocabEnglishDef().toLowerCase().contains(searchText))
            {
                searchResult.add(vocabulary);
                continue;
            }

            boolean isInExamples = isInExamples(vocabulary.getId(), searchText);
            if (isInExamples)
            {
                searchResult.add(vocabulary);
                continue;
            }

            boolean isInNotes = isInNotes(vocabulary.getId(), searchText);
            if (isInNotes)
            {
                searchResult.add(vocabulary);
            }
        }

        return searchResult;
    }

    private static boolean isInExamples(int vocabularyId, String searchText)
    {
        for (Example example : exampleCache)
        {
            if (example.getVocabularyId() == vocabularyId && (example.getEnglish().toLowerCase().contains(searchText) || example.getPersian().toLowerCase().contains(searchText)))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isInNotes(int vocabularyId, String searchText)
    {
        for (Note note : notesCache)
        {
            if (note.getVocabularyId() == vocabularyId && note.getDescription().toLowerCase().contains(searchText))
            {
                return true;
            }
        }
        return false;
    }
}
