package co.infinum.workshop_4.database;

import android.provider.BaseColumns;

/**
 * Created by ivankocijan on 17.03.2014..
 */
public final class DBExampleContract {

    public DBExampleContract () {
    }


    public static abstract class DBEntry implements BaseColumns {

        public static final String TABLE_NAME = "user_table";
        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_USER_SURNAME = "user_surname";
        public static final String USER_DESCRIPTION = "user_description";

    }
}
