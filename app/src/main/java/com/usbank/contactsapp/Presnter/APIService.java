package com.usbank.contactsapp.Presnter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.usbank.contactsapp.IContactView;
import com.usbank.contactsapp.model.Contact;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by madhu on 4/9/19.
 */

public class APIService {

    public static Retrofit retrofit;

    public static Retrofit getRetrofit(){

        if(retrofit == null){
            Gson gson = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder()
                         .baseUrl("https://api.androidhive.info").
                    addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();


        }

        return retrofit;

    }

//    private IAPIInterface getApiInterface(){
//
//        return getRetrofit().create(IAPIInterface.class);
//
//    }
//
//    public Observable<List<Contact[]>> getContactsData(){
//        return getApiInterface().getApiData().map(contactList -> contactList);
//    }
}
