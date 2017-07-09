package com.acme.a3csci3130;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, addressField, busNumField;
    private Spinner provSpin, busSpin;
    private TextView userNote;
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

        userNote = (TextView) findViewById(R.id.userNote);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID

        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String busNum = busNumField.getText().toString();
        String address = addressField.getText().toString();
        String province = provSpin.getSelectedItem().toString();
        String business = busSpin.getSelectedItem().toString();

        if(checkValues(name, busNum, address)) {
            Contact person = new Contact(personID, busNum, name, business, address, province);

            appState.firebaseReference.child(personID).setValue(person);

            finish();
        } else {
            userNote.setText("Please try again!");
            userNote.setTextColor(Color.RED);
        }

    }

    public static boolean checkValues(String name, String busNum, String addres) {
        boolean result = true;
        // Checking name is 2-48 chars
        if(!(name.length() >= 2 && name.length() <= 48)) { result = false; }
        // Checking busNum is a 9 digit number
        if(Integer.parseInt(busNum) == 0 || busNum.length() != 9 ) { result = false; }
        // Checking address is < 50 chars
        if(addres.length() >= 50) { result = false; }

        return result;
    }
}
