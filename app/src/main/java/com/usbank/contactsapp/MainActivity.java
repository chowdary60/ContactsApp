package com.usbank.contactsapp;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.support.v7.widget.SearchView;

import com.usbank.contactsapp.Adapter.ContactsAdapter;
import com.usbank.contactsapp.Presnter.ContactPresenter;
import com.usbank.contactsapp.model.Contact;
import com.usbank.contactsapp.model.ContactResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IContactView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SearchView searchView;
    ContactsAdapter contactsAdapter;
    ContactPresenter contactPresenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contacts");

        recyclerView = findViewById(R.id.recycleView);
      //  progressBar = findViewById(R.id.progressBar);
        contactPresenter = new ContactPresenter(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getContacts();


    }

    private void getContacts() {
        contactPresenter.getcontacts();
    }

    @Override
    public void displayProgress(boolean display) {
        if(display){
           // progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
           // progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void dispayData(ContactResponse contactResponse) {
        if(contactResponse!=null) {
          //  Log.d("Madhu",contactResponse.getResults().get(1).getAddress());
            contactsAdapter = new ContactsAdapter(contactResponse.getResults(), MainActivity.this);
            recyclerView.setAdapter(contactsAdapter);
        }else{
            Log.d("madhu","Movies response null");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);



        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                contactsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                contactsAdapter.getFilter().filter(query);
                return false;
            }
        });

      return true;
    }
}
