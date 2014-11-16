package co.infinum.workshop_4.activites;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import co.infinum.workshop_4.R;
import co.infinum.workshop_4.database.DBExampleContract;
import co.infinum.workshop_4.database.DBHelper;
import co.infinum.workshop_4.modules.User;


public class DeleteFromDbActivity extends Activity {

    private EditText userName;
    private Button delete;
    private TextView deleteStatus;
    private DBHelper mDbHelper;

    private MainActivity.DbType dbType;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data_from_db);

        userName = (EditText) findViewById(R.id.edit_text_name_to_delete);
        delete = (Button) findViewById(R.id.button_delete_from_db);
        deleteStatus = (TextView) findViewById(R.id.delete_user_status);

        mDbHelper = new DBHelper(this);

        dbType = (MainActivity.DbType) getIntent().getExtras().get(MainActivity.DB_TYPE_EXTRA);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                switch (dbType) {
                    case NORMAL:
                        deleteFromNormalDb();
                        break;

                    case ACTIVE_ANDROID:
                        deleteFromAADb();
                        break;

                }


            }

        });


    }

    private void deleteFromAADb () {

        if (!checkName()) {
            return;
        }

        User user = new Select().from(User.class).where("name = ?", userName.getText().toString()).executeSingle();

        if (user == null) {

            deleteStatus.setText("User " + userName.getText().toString() + " has not been deleted");

        } else {

            new Delete().from(User.class).where("name = ?", userName.getText().toString()).executeSingle();
            deleteStatus.setText("User " + userName.getText().toString() + " successfully deleted");

        }


    }

    private void deleteFromNormalDb () {

        if (!checkName()) {
            return;
        }

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        if (db == null) {
            return;
        }

        // Where part of query.
        String selection = DBExampleContract.DBEntry.COLUMN_USER_NAME + " LIKE ?";

        String[] selectionArgs = {
                String.valueOf(userName.getText().toString())
        };

        int numberOfRowsAffected = db.delete(DBExampleContract.DBEntry.TABLE_NAME, selection, selectionArgs);

        if (numberOfRowsAffected == 0) {

            deleteStatus.setText("User " + userName.getText().toString() + " has not been deleted");

        } else {

            deleteStatus.setText("User " + userName.getText().toString() + " successfully deleted");

        }

    }

    private boolean checkName () {


        if (userName.getText() == null || userName.getText().toString().isEmpty()) {

            Toast.makeText(DeleteFromDbActivity.this, "Please enter user name", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }


}
