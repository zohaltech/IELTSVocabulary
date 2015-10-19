package com.zohaltech.app.ieltsvocabulary.data;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zohaltech.app.ieltsvocabulary.classes.MyRuntimeException;
import com.zohaltech.app.ieltsvocabulary.entities.Sentence;

import java.util.ArrayList;

public class Sentences {
    static final String TableName = "Sentences";
    static final String Id        = "Id";
    static final String Text      = "Text";

    static final String CreateTable = "CREATE TABLE " + TableName + " ( " +
                                      Id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                      Text + " VARCHAR(500) );";
    static final String DropTable   = "Drop Table If Exists " + TableName;

    private static ArrayList<Sentence> select(String whereClause, String[] selectionArgs) {
        ArrayList<Sentence> sentences = new ArrayList<>();
        DataAccess da = new DataAccess();
        SQLiteDatabase db = da.getReadableDB();
        Cursor cursor = null;

        try {
            String query = "Select * From " + TableName + " " + whereClause;
            cursor = db.rawQuery(query, selectionArgs);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Sentence sentence = new Sentence(cursor.getInt(cursor.getColumnIndex(Id)),
                                                     cursor.getString(cursor.getColumnIndex(Text)));


                    sentences.add(sentence);
                } while (cursor.moveToNext());
            }
        } catch (MyRuntimeException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
            if (db != null && db.isOpen())
                db.close();
        }
        return sentences;
    }

    public static ArrayList<Sentence> select() {
        return select("", null);
    }

    public static Sentence select(long themeId) {
        ArrayList<Sentence> sentences = select("Where " + Id + " = ? ", new String[]{String.valueOf(themeId)});
        if (sentences.size() == 1) {
            return sentences.get(0);
        } else {
            return null;
        }
    }
}
