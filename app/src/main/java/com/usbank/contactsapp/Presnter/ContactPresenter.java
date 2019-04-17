package com.usbank.contactsapp.Presnter;

import android.util.Log;

import com.usbank.contactsapp.IContactView;
import com.usbank.contactsapp.model.Contact;
import com.usbank.contactsapp.model.ContactResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by madhu on 4/9/19.
 */

public class ContactPresenter implements ContactPresenterInterface {


    private IContactView iContactView;

    public ContactPresenter(IContactView iContactView){
        this.iContactView = iContactView;


    }

    @Override
    public void getcontacts() {
        getObservable().subscribeWith(getObserver());
    }

    public Observable<ContactResponse> getObservable(){
      return APIService.getRetrofit().create(IAPIInterface.class)
              .getContacts()
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread());
    }



    public DisposableObserver<ContactResponse> getObserver(){
        return new DisposableObserver<ContactResponse>() {

            @Override
            public void onNext(@NonNull ContactResponse contactResponse) {

                iContactView.dispayData(contactResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("MADHU","Error"+e);
                e.printStackTrace();

            }

            @Override
            public void onComplete() {
                Log.d("madhu","Completed");
                //iContactView.hideProgressBar();
            }
        };
    }




}
