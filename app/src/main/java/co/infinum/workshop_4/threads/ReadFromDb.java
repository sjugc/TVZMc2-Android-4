package co.infinum.workshop_4.threads;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.List;

import co.infinum.workshop_4.adapter.UsersAdapter;
import co.infinum.workshop_4.database.DBExampleContract;
import co.infinum.workshop_4.database.DBHelper;
import co.infinum.workshop_4.modules.User;


/**
 * Created by ivankocijan on 25.03.2014..
 */
public class ReadFromDb extends AsyncTask<Integer, Integer, Boolean> {

    private UsersAdapter adapter;
    private List<User> users;
    private DBHelper mDbHelper;

    public ReadFromDb (UsersAdapter adapter, List<User> users, Context ctx) {

        this.adapter = adapter;
        this.users = users;
        mDbHelper = new DBHelper(ctx);

    }

    @Override
    protected void onProgressUpdate (Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute () {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground (Integer... params) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        if (db == null || users == null) {
            return false;
        }


        //Which columns from the database will be used
        String[] projection = {
                DBExampleContract.DBEntry.COLUMN_USER_NAME,
                DBExampleContract.DBEntry.COLUMN_USER_SURNAME,
                DBExampleContract.DBEntry.USER_DESCRIPTION
        };


        Cursor cursor = db.query(
                DBExampleContract.DBEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        cursor.moveToFirst();

        while (cursor.moveToNext()) {

            String userName = cursor.getString(cursor.getColumnIndex(DBExampleContract.DBEntry.COLUMN_USER_NAME));
            String userSurname = cursor.getString(cursor.getColumnIndex(DBExampleContract.DBEntry.COLUMN_USER_SURNAME));
            String description = cursor.getString(cursor.getColumnIndex(DBExampleContract.DBEntry.USER_DESCRIPTION));

            User user = new User(userName, userSurname, description);

            users.add(user);

        }


        return true;
    }


    @Override
    protected void onPostExecute (Boolean isSuccessful) {
        super.onPostExecute(isSuccessful);

        if (isSuccessful) {
            adapter.notifyDataSetChanged();
        }

    }
}
