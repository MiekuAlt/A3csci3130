package com.acme.a3csci3130;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Checks the user's input, if it follows the rules it submits the data to FireBase
 *
 * @author Michael
 */
public class CreateContactActivity extends Activity {

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

        nameField = (EditText) findViewById(R.id.name);
        busNumField = (EditText) findViewById(R.id.businessNumber);
        addressField = (EditText) findViewById(R.id.address);
        provSpin = (Spinner) findViewById(R.id.province);
        busSpin = (Spinner) findViewById(R.id.business);

        userNote = (TextView) findViewById(R.id.userNote);
    }

    /**
     * Submits the user's data to FireBase when the user presses the "Create Contact" button
     *
     * @param v The View for activity_create_contact_activity
     */
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

    /**
     * Checks the values to ensure the data entered correctly follows the rules
     *
     * @param name Checking name is 2-48 chars
     * @param busNum Checking busNum is a 9 digit number
     * @param address Checking address is < 50 chars
     * @return If all the values correctly follow the rules
     */
    public static boolean checkValues(String name, String busNum, String address) {
        boolean result = true;
        // Checking name is 2-48 chars
        if(!(name.length() >= 2 && name.length() <= 48)) { result = false; }
        // Checking busNum is a 9 digit number
        if(Integer.parseInt(busNum) == 0 || busNum.length() != 9 ) { result = false; }
        // Checking address is < 50 chars
        if(address.length() >= 50) { result = false; }

        return result;
    }
}
