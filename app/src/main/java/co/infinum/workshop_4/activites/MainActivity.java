package co.infinum.workshop_4.activites;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import co.infinum.workshop_4.R;
import co.infinum.workshop_4.database.DBExampleContract;
import co.infinum.workshop_4.database.DBHelper;


public class MainActivity extends Activity {

    public enum DbType {

        NORMAL, ACTIVE_ANDROID

    }

    public static final String DB_TYPE_EXTRA = "dbType";

    private Button addToDb;
    private Button readFromDb;
    private Button deleteFromDb;

    private Button addToDbAA;
    private Button readFromDbAA;
    private Button deleteFromDbAA;

    private DBHelper dbhelper;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbhelper = new DBHelper(this);

        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBExampleContract.DBEntry.COLUMN_USER_NAME, "Jopa");
        values.put(DBExampleContract.DBEntry.COLUMN_USER_SURNAME, "Zeleni");
        values.put(DBExampleContract.DBEntry.USER_DESCRIPTION, "Jopa je zelen");

        long id = sqLiteDatabase.insert(DBExampleContract.DBEntry.TABLE_NAME, null, values);

        Log.d("koc", "id = " + id);

        addToDb = (Button) findViewById(R.id.add_to_db);
        readFromDb = (Button) findViewById(R.id.read_from_db);
        deleteFromDb = (Button) findViewById(R.id.delete_from_db);

        addToDbAA = (Button) findViewById(R.id.add_to_db_aa);
        readFromDbAA = (Button) findViewById(R.id.read_from_db_aa);
        deleteFromDbAA = (Button) findViewById(R.id.delete_from_db_aa);

        addToDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                Intent intent = new Intent(MainActivity.this, AddDataToDbActivity.class);
                intent.putExtra(DB_TYPE_EXTRA, DbType.NORMAL);
                startActivity(intent);
            }
        });

        readFromDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                Intent intent = new Intent(MainActivity.this, ReadFromDbActivity.class);
                intent.putExtra(DB_TYPE_EXTRA, DbType.NORMAL);
                startActivity(intent);


            }
        });

        deleteFromDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                Intent intent = new Intent(MainActivity.this, DeleteFromDbActivity.class);
                intent.putExtra(DB_TYPE_EXTRA, DbType.NORMAL);
                startActivity(intent);


            }
        });

        addToDbAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                Intent intent = new Intent(MainActivity.this, AddDataToDbActivity.class);
                intent.putExtra(DB_TYPE_EXTRA, DbType.ACTIVE_ANDROID);
                startActivity(intent);
            }
        });

        readFromDbAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                Intent intent = new Intent(MainActivity.this, ReadFromDbActivity.class);
                intent.putExtra(DB_TYPE_EXTRA, DbType.ACTIVE_ANDROID);
                startActivity(intent);


            }
        });

        deleteFromDbAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                Intent intent = new Intent(MainActivity.this, DeleteFromDbActivity.class);
                intent.putExtra(DB_TYPE_EXTRA, DbType.ACTIVE_ANDROID);
                startActivity(intent);

            }
        });

    }


}
