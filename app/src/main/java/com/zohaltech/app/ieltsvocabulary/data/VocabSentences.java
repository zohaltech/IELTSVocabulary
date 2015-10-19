package com.zohaltech.app.ieltsvocabulary.data;


public class VocabSentences {
    static final String TableName  = "VocabSentences";
    static final String Id         = "Id";
    static final String VocabId    = "VocabId";
    static final String SentenceId = "SentenceId";

    static final String CreateTable = "CREATE TABLE " + TableName + " ( " +
                                      Id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                      VocabId + " INTEGER REFERENCES " + Vocabularies.TableName + " (" + Vocabularies.Id + "), " +
                                      SentenceId + " INTEGER REFERENCES " + Sentences.TableName + " (" + Sentences.Id + ")); ";
    static final String DropTable   = "Drop Table If Exists " + TableName;
}
