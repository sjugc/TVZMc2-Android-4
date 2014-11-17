package co.infinum.workshop_4.activites;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import co.infinum.workshop_4.R;
import co.infinum.workshop_4.database.DBExampleContract;
import co.infinum.workshop_4.database.DBHelper;
import co.infinum.workshop_4.modules.User;


public class AddDataToDbActivity extends ActionBarActivity {

    private EditText name;
    private EditText surname;
    private EditText description;
    private Button insertRow;
    private TextView rowNumber;

    private MainActivity.DbType dbType;

    private DBHelper mDbHelper;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_db);

        name = (EditText) findViewById(R.id.edit_text_name);
        surname = (EditText) findViewById(R.id.edit_text_surname);
        description = (EditText) findViewById(R.id.edit_text_description);
        insertRow = (Button) findViewById(R.id.button_insert_into_db);
        rowNumber = (TextView) findViewById(R.id.text_view_row_id);

        mDbHelper = new DBHelper(this);

        dbType = (MainActivity.DbType) getIntent().getExtras().getSerializable(MainActivity.DB_TYPE_EXTRA);

        setInsertRowOnClickListener(dbType);


    }

    private void setInsertRowOnClickListener (final MainActivity.DbType dbType) {

        if (dbType == null) {
            return;
        }

        insertRow.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick (View v) {
                switch (dbType) {
                    case NORMAL:
                        setNormalClickListener();
                        break;

                    case ACTIVE_ANDROID:

                        setActiveAndroidClickListener();
                        break;

                }

            }

        });


    }

    private void setNormalClickListener () {

        if (!checkText()) {
            return;
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBExampleContract.DBEntry.COLUMN_USER_NAME, name.getText().toString());
        values.put(DBExampleContract.DBEntry.COLUMN_USER_SURNAME, surname.getText().toString());
        values.put(DBExampleContract.DBEntry.USER_DESCRIPTION, description.getText().toString());

        // nullColumnHack provides the name of nullable column name to explicitly insert a NULL into
        // in the case where your values is empty.
        long newRowId;

        if (db != null) {

            newRowId = db.insert(DBExampleContract.DBEntry.TABLE_NAME, null, values);

            rowNumber.setText(String.valueOf(newRowId));

        }
    }


    private void setActiveAndroidClickListener () {

        if (!checkText()) {
            return;
        }


        User user = new User();
        user.setName(name.getText().toString());
        user.setSurname(surname.getText().toString());
        user.setDescription(description.getText().toString());
        user.save();

        rowNumber.setText(String.valueOf(user.getId()));

    }


    private boolean checkText () {

        if (name.getText().toString().isEmpty() || surname.getText().toString().isEmpty() || description.getText().toString().isEmpty()) {
            Toast.makeText(AddDataToDbActivity.this, getString(R.string.enter_all_Data), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}
