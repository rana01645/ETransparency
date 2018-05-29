package com.contrastive.com.etransparency;


import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rana on 4/19/2017.
 */


@IgnoreExtraProperties
public class bill {

    public String bill;
    public  String unit;

    public String getBill() {
        return bill;
    }

    public String getUnit() {
        return unit;
    }


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public bill() {
    }

    public bill(String bill, String unit) {
        this.bill = bill;
        this.unit = unit;
    }
}
