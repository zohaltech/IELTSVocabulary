package com.zohaltech.app.ieltsvocabulary.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zohaltech.app.ieltsvocabulary.classes.MyRuntimeException;
import com.zohaltech.app.ieltsvocabulary.entities.Note;

import java.util.ArrayList;

public class Notes {
    static final String TableName    = "Notes";
    static final String Id           = "Id";
    static final String VocabularyId = "VocabularyId";
    static final String Ordinal      = "Ordinal";
    static final String Description  = "Description";

    static final String CreateTable = "CREATE TABLE " + TableName + " ( " +
            Id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            VocabularyId + " INTEGER , " +
            Ordinal + " INTEGER , " +
            Description + " VARCHAR(50));";
    static final String DropTable   = "Drop Table If Exists " + TableName;


    private static ArrayList<Note> cache = null;

    public static ArrayList<Note> getCache() {
        if (cache == null) {
            cache = select("", null);
        }
        return cache;
    }

    private static ArrayList<Note> select(String whereClause, String[] selectionArgs) {
        ArrayList<Note> noteList = new ArrayList<>();
        DataAccess da = new DataAccess();
        SQLiteDatabase db = da.getReadableDB();
        Cursor cursor = null;

        try {
            String query = "Select * From " + TableName + " " + whereClause;
            cursor = db.rawQuery(query, selectionArgs);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Note note = new Note(cursor.getInt(cursor.getColumnIndex(Id)),
                            cursor.getInt(cursor.getColumnIndex(VocabularyId)),
                            cursor.getInt(cursor.getColumnIndex(Ordinal)),
                            cursor.getString(cursor.getColumnIndex(Description)).replace('|', '\n'));

                    noteList.add(note);
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
        return noteList;
    }

    public static ArrayList<Note> select() {
        return select("", null);
    }

    public static long insert(Note note) {
        DataAccess da = new DataAccess();
        return da.insert(TableName, getContentValues(note));
    }

    public static long update(Note note) {
        DataAccess da = new DataAccess();
        return da.update(TableName, getContentValues(note), Id + " =? ", new String[]{String.valueOf(note.getId())});
    }

    public static ContentValues getContentValues(Note note) {
        ContentValues values = new ContentValues();

        values.put(VocabularyId, note.getVocabularyId());
        values.put(Ordinal, note.getOrdinal());
        values.put(Description, note.getDescription());

        return values;
    }

    public static ArrayList<Note> getNotes(int vocabId) {
        String whereClause = " WHERE " + VocabularyId + " = " + vocabId;
        return select(whereClause, null);
    }
}
