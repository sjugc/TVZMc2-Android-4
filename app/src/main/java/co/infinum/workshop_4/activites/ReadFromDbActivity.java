package co.infinum.workshop_4.activites;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import co.infinum.workshop_4.R;
import co.infinum.workshop_4.adapter.UsersAdapter;
import co.infinum.workshop_4.modules.User;
import co.infinum.workshop_4.threads.ReadFromDb;


public class ReadFromDbActivity extends Activity {

    private ListView dbEntryList;
    private List<User> users = new ArrayList<User>();
    private UsersAdapter adapter;
    private ReadFromDb readFromDb;


    private MainActivity.DbType dbType;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_from_db);

        dbEntryList = (ListView) findViewById(R.id.list_view_android_db);

        adapter = new UsersAdapter(this, users);

        dbEntryList = (ListView) findViewById(R.id.list_view_android_db);

        dbEntryList.setAdapter(adapter);

        dbType = (MainActivity.DbType) getIntent().getExtras().get(MainActivity.DB_TYPE_EXTRA);
        readFromDb(dbType);


    }

    private void readFromDb (MainActivity.DbType dbType) {

        switch (dbType) {
            case NORMAL:

                readFromDb = new ReadFromDb(adapter, users, this);
                readFromDb.execute();
                break;

            case ACTIVE_ANDROID:
                List<User> usersFromAADb = new Select().from(User.class).execute();
                users.addAll(usersFromAADb);
                adapter.notifyDataSetChanged();
                break;

        }

    }

}
