package com.usbank.contactsapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by madhu on 4/12/19.
 */

public class ContactResponse {
    public List<Contact> getResults() {
        return results;
    }

    public void setResults(List<Contact> results) {
        this.results = results;
    }

    public ContactResponse(List<Contact> results) {
        this.results = results;
    }

    @SerializedName("contacts")
    @Expose
    private List<Contact> results = null;
}
