package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Displays the contact's information in detail, it also allows for the editing and removal of contacts
 *
 * @author Michael
 */
public class DetailViewActivity extends Activity {

    private EditText nameField, addressField, busNumField;
    private Spinner provSpin, busSpin;
    private Contact receivedPersonInfo;
    private TextView userNote;
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

        userNote = (TextView) findViewById(R.id.userNote);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            busNumField.setText(receivedPersonInfo.businessNumber);
            addressField.setText(receivedPersonInfo.address);
            provSpin.setSelection(provIndex(receivedPersonInfo.province));
            busSpin.setSelection(busIndex(receivedPersonInfo.business));
        }
    }

    /**
     * Updates the contact stored in FireBase once the user presses the "Update Date" button
     * @param v The View for activity_detail_view
     */
    public void updateContact(View v){
        String personID = receivedPersonInfo.uid;
        String name = nameField.getText().toString();
        String busNum = busNumField.getText().toString();
        String address = addressField.getText().toString();
        String province = provSpin.getSelectedItem().toString();
        String business = busSpin.getSelectedItem().toString();

        if(CreateContactActivity.checkValues(name, busNum, address)) {
            Contact person = new Contact(personID, busNum, name, business, address, province);

            appState.firebaseReference.child(personID).setValue(person);

            finish();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            userNote.setText("Please try again!");
            userNote.setTextColor(Color.RED);
        }
    }

    /**
     * Removes the contact stored in FireBase once the user presses the "Erase Contact" button
     * @param v The View for activity_detail_view
     */
    public void eraseContact(View v)
    {
        String personID = receivedPersonInfo.uid;
        appState.firebaseReference.child(personID).removeValue();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Determines the index that would represent the province within its StringArray
     *
     * @param prov The province that is being checked
     * @return The index determined by the method
     */
    private int provIndex(String prov) {
        int index;

        switch (prov) {
            case "AB":
                index = 1;
                break;
            case "BC":
                index = 2;
                break;
            case "MB":
                index = 3;
                break;
            case "NB":
                index = 4;
                break;
            case "NL":
                index = 5;
                break;
            case "NS":
                index = 6;
                break;
            case "NT":
                index = 7;
                break;
            case "NU":
                index = 8;
                break;
            case "ON":
                index = 9;
                break;
            case "PE":
                index = 10;
                break;
            case "QC":
                index = 11;
                break;
            case "SK":
                index = 12;
                break;
            case "YT":
                index = 13;
                break;
            default:
                index = 0;
                break;
        }

        return index;
    }

    /**
     * Determines the index that would represent the business within its StringArray
     *
     * @param business The business that is being checked
     * @return The index determined by the method
     */
    private int busIndex(String business) {
        int index;

        switch (business) {
            case "Fisher":
                index = 0;
                break;
            case "Distributor":
                index = 1;
                break;
            case "Processor":
                index = 2;
                break;
            default:
                index = 3;
                break;
        }

        return index;
    }
}
