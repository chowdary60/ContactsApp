package com.usbank.contactsapp;

import com.usbank.contactsapp.model.Contact;
import com.usbank.contactsapp.model.ContactResponse;

import java.util.List;

/**
 * Created by madhu on 4/9/19.
 */

public interface IContactView {
   void displayProgress(boolean display);
   void dispayData(ContactResponse contactResponse);
}
