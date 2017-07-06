package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, addressField, busNumField;
    private Spinner provSpin, busSpin;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        busNumField = (EditText) findViewById(R.id.businessNumber);
        addressField = (EditText) findViewById(R.id.address);
        provSpin = (Spinner) findViewById(R.id.province);
        busSpin = (Spinner) findViewById(R.id.business);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String busNum = busNumField.getText().toString();
        String address = addressField.getText().toString();
        String province = provSpin.getSelectedItem().toString();
        String business = busSpin.getSelectedItem().toString();

        Contact person = new Contact(personID, busNum, name, business, address, province);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
