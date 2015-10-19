package com.zohaltech.app.ieltsvocabulary.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.provider.Settings;

import com.zohaltech.app.ieltsvocabulary.classes.App;
import com.zohaltech.app.ieltsvocabulary.classes.CsvReader;
import com.zohaltech.app.ieltsvocabulary.classes.MyRuntimeException;
import com.zohaltech.app.ieltsvocabulary.entities.Theme;

import java.io.InputStreamReader;

public class DataAccess extends SQLiteOpenHelper {
    public static final String DATABASE_NAME    = "IELTS_VOCABULARIES";
    public static final int    DATABASE_VERSION =4;

    public DataAccess() {
        super(App.context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Themes.CreateTable);
            db.execSQL(Vocabularies.CreateTable);
            db.execSQL(Sentences.CreateTable);
            db.execSQL(VocabSentences.CreateTable);
            db.execSQL(Notes.CreateTable);
            db.execSQL(SystemSettings.CreateTable);


            insertDataFromAsset(db, Themes.TableName, "data/categories.csv", ';');
            insertDataFromAsset(db, Sentences.TableName, "data/sentences.csv", ';');
            insertDataFromAsset(db, Vocabularies.TableName, "data/vocabs.csv", ';');
            insertDataFromAsset(db, VocabSentences.TableName, "data/vacab_sentence.csv", ';');

            ContentValues systemSettingsValues = new ContentValues();
            systemSettingsValues.put(SystemSettings.Installed, 0);
            systemSettingsValues.put(SystemSettings.Premium, 0);
            systemSettingsValues.put(SystemSettings.VibrateInAlarms, 0);
            systemSettingsValues.put(SystemSettings.SoundInAlarms, 0);
            systemSettingsValues.put(SystemSettings.RingingToneUri, Settings.System.DEFAULT_NOTIFICATION_URI.getPath());
            Ringtone ringtone = RingtoneManager.getRingtone(App.context, Settings.System.DEFAULT_NOTIFICATION_URI);
            if (ringtone != null) {
                systemSettingsValues.put(SystemSettings.AlarmRingingTone, ringtone.getTitle(App.context));
            } else {
                systemSettingsValues.put(SystemSettings.AlarmRingingTone, "Default");
            }
            db.insert(SystemSettings.TableName, null, systemSettingsValues);

        } catch (MyRuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        try {
            database.execSQL(Themes.DropTable);
            database.execSQL(Vocabularies.DropTable);
            database.execSQL(Examples.DropTable);
            database.execSQL(Notes.DropTable);
            database.execSQL(SystemSettings.DropTable);

            onCreate(database);
        } catch (MyRuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public SQLiteDatabase getReadableDB() {
        return this.getReadableDatabase();
    }

    public SQLiteDatabase getWritableDB() {
        return this.getWritableDatabase();
    }

    public long insert(String table, ContentValues values) {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDB();
            result = db.insert(table, null, values);
            db.close();
        } catch (MyRuntimeException e) {
            e.printStackTrace();
        }
        return result;
    }

    public long update(String table, ContentValues values, String whereClause, String[] selectionArgs) {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDB();
            result = db.update(table, values, whereClause, selectionArgs);
            db.close();
        } catch (MyRuntimeException e) {
            e.printStackTrace();
        }
        return result;
    }

    public long delete(String table, String whereClause, String[] selectionArgs) {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDB();
            result = db.delete(table, whereClause, selectionArgs);
            db.close();
        } catch (MyRuntimeException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void insertDataFromAsset(SQLiteDatabase db, String tableName, String filePathFromAsset, char delimiter) {
        InputStreamReader isr;
        try {
            isr = new InputStreamReader(App.context.getAssets().open(filePathFromAsset), "UTF-8");

            CsvReader csvReader = new CsvReader(isr, delimiter);
            csvReader.readHeaders();
            while (csvReader.readRecord()) {
                ContentValues values = new ContentValues();
                for (int i = 0; i < csvReader.getHeaderCount(); i++) {
                    values.put(csvReader.getHeader(i), csvReader.get(csvReader.getHeader(i)));
                }
                db.insert(tableName, null, values);
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
