package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase database. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public String uid;
    public String businessNumber;
    public String name;
    public String business;
    public String address;
    public String province;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(String uid, String businessNumber, String name, String business, String address, String province){
        this.uid = uid;
        this.businessNumber = businessNumber;
        this.name = name;
        this.business = business;
        this.address = address;
        this.province = province;
    }

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
