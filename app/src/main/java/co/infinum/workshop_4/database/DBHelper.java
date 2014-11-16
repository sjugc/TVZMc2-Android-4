package co.infinum.workshop_4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ivankocijan on 17.03.2014..
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "EgDatabase.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBExampleContract.DBEntry.TABLE_NAME + " (" +
                    DBExampleContract.DBEntry.COLUMN_USER_NAME + TEXT_TYPE + COMMA_SEP +
                    DBExampleContract.DBEntry.COLUMN_USER_SURNAME + TEXT_TYPE + COMMA_SEP +
                    DBExampleContract.DBEntry.USER_DESCRIPTION + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBExampleContract.DBEntry.TABLE_NAME;

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void onCreate (SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }


}
