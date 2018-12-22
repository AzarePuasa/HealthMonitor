package com.azare.app.healthmonitor;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.azare.app.healthmonitor.db.DAOBPReading;
import com.azare.app.healthmonitor.model.BPReading;

public class InsertBPReading extends AsyncTask<BPReading, Void, String> {

//    ProgressDialog p;

    DAOBPReading daobpReading;
    Context context;

    public InsertBPReading(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(BPReading... lists) {

        int iCount = 0;
        for (BPReading reading : lists) {
            //TODO: get db instance and call insert.
            Log.i("Health Monitor", reading.toString());
            daobpReading.insert(reading);
            iCount++;
        }

        return Integer.toString(iCount);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        daobpReading = new DAOBPReading(this.context);

//        p = new ProgressDialog(this.context);
//        p.setMessage("Inserting Readings...");
//        p.setIndeterminate(false);
//        p.setCancelable(false);
//        p.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Toast.makeText(this.context,"Completed inserting" + s, Toast.LENGTH_LONG).show();
    }
}
