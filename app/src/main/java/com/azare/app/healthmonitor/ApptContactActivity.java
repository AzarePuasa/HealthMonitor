package com.azare.app.healthmonitor;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.azare.app.healthmonitor.model.Contact;

import java.util.ArrayList;

public class ApptContactActivity extends AppCompatActivity {

    Button btnCommit;
    EditText etRelation;
    TextView tvChoice;
    ContentResolver cr;
    ListView lv;
    ArrayList<Contact> al;
    ArrayAdapter<Contact> aa;

    Uri CONTACT_CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
    String CONTACT_ID = ContactsContract.Contacts._ID;
    String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

    Uri PHONE_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    String PHONE_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
    String PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

    Uri DATA_CONTENT_URI = ContactsContract.Data.CONTENT_URI;
    String NOTE = ContactsContract.CommonDataKinds.Note.NOTE;
    String DATA_CONTACT_ID = ContactsContract.Data.CONTACT_ID;
    String DATA_MIME_TYPE = ContactsContract.Data.MIMETYPE;
    String DATA_NOTE = ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appt_contact);

        checkPermission();

        btnCommit = (Button) findViewById(R.id.btnCommit);
        etRelation = (EditText) findViewById(R.id.etRelation);
        tvChoice = (TextView) findViewById(R.id.tvChoice);
        lv = (ListView) findViewById(R.id.list);
        cr = getApplicationContext().getContentResolver();
        al = new ArrayList<Contact>();
        aa = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        btnCommit.setOnClickListener(btnConfirmClicked);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                btnCommit.setEnabled(true);
                Contact clicked = aa.getItem(pos);
                tvChoice.setText("Selected ID:" + clicked.getId());
                etRelation.setText(clicked.getRelation());
            }
        });

        getContacts();

    }

    private View.OnClickListener btnConfirmClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // Get what user wrote as notes and update the note data.
            String id = tvChoice.getText().toString().split(":")[1];
            String note = etRelation.getText().toString();
            ContentValues values = new ContentValues();
            values.put(NOTE, note);
            String where = DATA_CONTACT_ID + " = ? AND " + DATA_MIME_TYPE + " = ?";
            int status = cr.update(DATA_CONTENT_URI, values, where, new String[]{id, DATA_NOTE});
            if (status == 0) {
                values.put(ContactsContract.Data.RAW_CONTACT_ID, id);
                values.put(DATA_MIME_TYPE, DATA_NOTE);
                cr.insert(DATA_CONTENT_URI, values);
            }
            getContacts();
        }
    };

    private void getContacts() {
        al.clear();
        btnCommit.setEnabled(false);
        etRelation.setText("");
        String[] cols = new String[]{CONTACT_ID, DISPLAY_NAME, HAS_PHONE_NUMBER};
        Cursor cursor = cr.query(CONTACT_CONTENT_URI, cols, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String contactId = cursor.getString(cursor.getColumnIndex(CONTACT_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                String note = "";
                String phoneNum = "";

                //Retrieving the number from the phone number table
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));


                if (hasPhoneNumber > 0) {
                    //at least a phone number is present, getting the first number
                    Cursor cursor2 = cr.query(PHONE_CONTENT_URI, null, PHONE_CONTACT_ID
                            + " = ?", new String[]{contactId}, null);

                    if (cursor2 != null && cursor2.moveToFirst()) {
                        phoneNum = cursor2.getString(cursor2.getColumnIndex(PHONE_NUMBER));
                    }
                    cursor2.close();
                } else {
                    //no phone number present
                    phoneNum = "N.A.";
                }

                //Retrieving the note from the note table
                Cursor cursor3 = cr.query(DATA_CONTENT_URI, null, DATA_CONTACT_ID
                        + " = ? AND " + DATA_MIME_TYPE
                        + " = ?", new String[]{contactId, DATA_NOTE}, null);

                if (cursor3 != null && cursor3.moveToFirst()) {
                    note = cursor3.getString(cursor3.getColumnIndex(NOTE));
                }
                cursor3.close();
                Contact tmp = new Contact(contactId, name, phoneNum, note);
                al.add(tmp);

            } while (cursor.moveToNext());
            aa.notifyDataSetChanged();
        }
    }

    private void checkPermission() {
        int permissionReadContacts = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS);

        int permissionWriteContacts = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CONTACTS);

        if (permissionReadContacts != PackageManager.PERMISSION_GRANTED
                && permissionWriteContacts != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "Please grant the permissions and restart the app",
                    Toast.LENGTH_LONG).show();
            String[] permissionNeeded = new String[]{Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS};

            ActivityCompat.requestPermissions(this, permissionNeeded, 1);

            finish();
        }
    }
}
