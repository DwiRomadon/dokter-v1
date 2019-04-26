package com.example.drgigi_appv1.img;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {

    // variable name should be same as in the json response from php
    @SerializedName("error")
    boolean success;
    @SerializedName("msg")
    String message;

    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }

}
