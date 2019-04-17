package com.usbank.contactsapp.Presnter;

import com.usbank.contactsapp.model.Contact;
import com.usbank.contactsapp.model.ContactResponse;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by madhu on 4/9/19.
 */

public interface IAPIInterface {

    @GET("/contacts")
    Observable<ContactResponse> getContacts();

}
