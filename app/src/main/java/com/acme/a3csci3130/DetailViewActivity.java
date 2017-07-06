package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class DetailViewActivity extends Activity {

    private EditText nameField, addressField, busNumField;
    private Spinner provSpin, busSpin;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField = (EditText) findViewById(R.id.name);
        busNumField = (EditText) findViewById(R.id.businessNumber);
        addressField = (EditText) findViewById(R.id.address);
        provSpin = (Spinner) findViewById(R.id.province);
        busSpin = (Spinner) findViewById(R.id.business);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            busNumField.setText(receivedPersonInfo.businessNumber);
            addressField.setText(receivedPersonInfo.address);
            provSpin.setSelection(provIndex(receivedPersonInfo.province));
            busSpin.setSelection(busIndex(receivedPersonInfo.business));
        }
    }

    public void updateContact(View v){
        String personID = receivedPersonInfo.uid;
        String name = nameField.getText().toString();
        String busNum = busNumField.getText().toString();
        String address = addressField.getText().toString();
        String province = provSpin.getSelectedItem().toString();
        String business = busSpin.getSelectedItem().toString();

        Contact person = new Contact(personID, busNum, name, business, address, province);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void eraseContact(View v)
    {
        String personID = receivedPersonInfo.uid;
        appState.firebaseReference.child(personID).removeValue();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private int provIndex(String prov) {
        int index;

        if(prov.equals("AB")) {
            index = 1;
        } else if(prov.equals("BC")) {
            index = 2;
        } else if(prov.equals("MB")) {
            index = 3;
        } else if(prov.equals("NB")) {
            index = 4;
        } else if(prov.equals("NL")) {
            index = 5;
        } else if(prov.equals("NS")) {
            index = 6;
        } else if(prov.equals("NT")) {
            index = 7;
        } else if(prov.equals("NU")) {
            index = 8;
        } else if(prov.equals("ON")) {
            index = 9;
        } else if(prov.equals("PE")) {
            index = 10;
        } else if(prov.equals("QC")) {
            index = 11;
        } else if(prov.equals("SK")) {
            index = 12;
        } else if(prov.equals("YT")) {
            index = 13;
        } else {
            index = 0;
        }

        return index;
    }

    private int busIndex(String business) {
        int index;

        if(business.equals("Fisher")) {
            index = 0;
        } else if(business.equals("Distributor")) {
            index = 1;
        } else if(business.equals("Processor")) {
            index = 2;
        } else {
            index = 3;
        }

        return index;
    }
}
