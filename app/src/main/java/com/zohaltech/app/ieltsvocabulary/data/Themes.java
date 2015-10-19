package com.zohaltech.app.ieltsvocabulary.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zohaltech.app.ieltsvocabulary.classes.MyRuntimeException;
import com.zohaltech.app.ieltsvocabulary.entities.Theme;

import java.util.ArrayList;

public class Themes {
    static final String TableName = "Themes";
    static final String Id        = "Id";
    static final String Level     = "Level";
    static final String Name      = "Name";
    static final String IconName  = "IconName";

    static final String CreateTable = "CREATE TABLE " + TableName + " ( " +
                                      Id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                      Level + " INTEGER , " +
                                      Name + " VARCHAR(50) ," +
                                      IconName + " VARCHAR(50));";
    static final String DropTable   = "Drop Table If Exists " + TableName;

    private static ArrayList<Theme> select(String whereClause, String[] selectionArgs) {
        ArrayList<Theme> themeList = new ArrayList<>();
        DataAccess da = new DataAccess();
        SQLiteDatabase db = da.getReadableDB();
        Cursor cursor = null;

        try {
            String query = "Select * From " + TableName + " " + whereClause;
            cursor = db.rawQuery(query, selectionArgs);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Theme theme = new Theme(cursor.getInt(cursor.getColumnIndex(Id)),
                                            cursor.getInt(cursor.getColumnIndex(Level)),
                                            cursor.getString(cursor.getColumnIndex(Name)),
                                            cursor.getString(cursor.getColumnIndex(IconName)));

                    themeList.add(theme);
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
        return themeList;
    }

    public static ArrayList<Theme> select() {
        return select("", null);
    }

    public static Theme select(long themeId) {
        ArrayList<Theme> themes = select("Where " + Id + " = ? ", new String[]{String.valueOf(themeId)});
        if (themes.size() == 1) {
            return themes.get(0);
        } else {
            return null;
        }
    }

    public static long insert(Theme theme) {
        DataAccess da = new DataAccess();
        return da.insert(TableName, getContentValues(theme));
    }

    public static long update(Theme theme) {
        DataAccess da = new DataAccess();
        return da.update(TableName, getContentValues(theme), Id + " =? ", new String[]{String.valueOf(theme.getId())});
    }

    public static ContentValues getContentValues(Theme theme) {
        ContentValues values = new ContentValues();

        values.put(Level, theme.getLevel());
        values.put(Name, theme.getName());
        values.put(IconName, theme.getIconName());

        return values;
    }
}
