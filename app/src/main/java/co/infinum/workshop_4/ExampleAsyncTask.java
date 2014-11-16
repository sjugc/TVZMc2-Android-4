package co.infinum.workshop_4;

import android.os.AsyncTask;

/**
 * @author Koc
 *         ivan.kocijan@infinum.hr
 * @since 16.11.14.
 */
public class ExampleAsyncTask extends AsyncTask<String, Integer, Double> {

    @Override
    protected void onPreExecute () {
        super.onPreExecute();
        //on main thread
    }

    @Override
    protected Double doInBackground (String... params) {

        //on background thread
        return null;
    }

    @Override
    protected void onPostExecute (Double aDouble) {
        super.onPostExecute(aDouble);
        //on main thread
    }

    @Override
    protected void onProgressUpdate (Integer... values) {
        super.onProgressUpdate(values);
        //on main thread
    }
}


