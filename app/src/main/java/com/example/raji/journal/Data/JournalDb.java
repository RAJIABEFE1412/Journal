package com.example.raji.journal.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.raji.journal.Data.JournalContract.JournalContractSchema;

public class JournalDb extends SQLiteOpenHelper {
    private static final String DB_NAME = "JournalDb";
    private static final int DB_VERSION = 1;
    Context c;


    public static final String LOG_TAG = JournalDb.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "journal.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link JournalDb}.
     *
     * @param context of the app
     */
    public JournalDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_JOURNAL_TABLE =  "CREATE TABLE " + JournalContractSchema.TABLE_NAME + " ("
                + JournalContractSchema._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + JournalContractSchema.TITLES + " TEXT NOT NULL, "
                + JournalContractSchema.NOTE + " TEXT NOT NULL );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_JOURNAL_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
        //db.execSQL( "DROP  TABLE IF EXISTS"+ JournalContractSchema.TABLE_NAME );
    }
    public boolean insert(String title, String note){
        SQLiteDatabase Db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(JournalContractSchema.TITLES,title);
        cv.put(JournalContractSchema.NOTE,note);

        long newRowId = Db.insert(JournalContractSchema.TABLE_NAME,null,cv);
        if (newRowId == -1){

            return false;
        }else {

            return true;
        }

    }
    Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery( "SELECT * FROM "+JournalContractSchema.TABLE_NAME,null );
        return cursor;
    }
//    public Cursor grabFromDb(){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        String[] projection = {
//                JournalContractSchema.NOTE,
//                JournalContractSchema.TITLES
//        };
//
//        Cursor cursor = db.query( JournalContractSchema.TABLE_NAME, projection,
//                null, null, null, null, null
//        );
//        return cursor;
//
//    }

}
