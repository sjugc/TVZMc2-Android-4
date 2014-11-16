package co.infinum.workshop_4;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * @author Koc
 *         ivan.kocijan@infinum.hr
 * @since 16.11.14.
 */
public class WorkshopApplication extends Application {

    @Override
    public void onCreate () {
        super.onCreate();

        ActiveAndroid.initialize(this);
    }
}
