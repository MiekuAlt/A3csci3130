package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase database. This is converted to a JSON format
 *
 * @author Juliano
 * @author Michael
 */
public class Contact implements Serializable {

    public String uid;
    public String businessNumber;
    public String name;
    public String business;
    public String address;
    public String province;

    /**
     * Default constructor required for calls to DataSnapshot.getValue
     */
    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * This is the constructor that is most typically used
     *
     * @param uid The primary key for the contact
     * @param businessNumber The contact's business registration number
     * @param name The contact's name
     * @param business The type of business the contact is
     * @param address The address for the contact
     * @param province The province the contact is in
     */
    public Contact(String uid, String businessNumber, String name, String business, String address, String province){
        this.uid = uid;
        this.businessNumber = businessNumber;
        this.name = name;
        this.business = business;
        this.address = address;
        this.province = province;
    }

    /**
     * Generates a HashMap
     *
     * @return The generated HashMap
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("businessNumber", businessNumber);
        result.put("name", name);
        result.put("business", business);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
