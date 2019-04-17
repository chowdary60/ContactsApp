package com.usbank.contactsapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.usbank.contactsapp.R;
import com.usbank.contactsapp.model.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by madhu on 4/9/19.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> implements Filterable {

    List<Contact> list;
    Context context;
    private List<Contact> listFiltered;

    public ContactsAdapter(List<Contact> list,Context context){
        this.list = list;
        this.context = context;
        this.listFiltered = list;

    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_item, viewGroup,false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        Collections.sort(list,
                (o1, o2) -> o1.getName().compareTo(o2.getName()));
        Contact contact = listFiltered.get(position);
        Log.i("madhu","contact list  "+contact.getName());
        holder.name.setText(contact.getName());
        holder.email.setText(contact.getEmail());
        holder.address.setText(contact.getAddress());




    }

    @Override
    public int getItemCount() {
        Log.i("madhu","contact size "+listFiltered.size());
        return listFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();
                if(charString.isEmpty()){
                    listFiltered = list;
                }else{
                    List<Contact> filteredList = new ArrayList<>();
                    for(Contact row :list){
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    listFiltered = filteredList;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;
                return  filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
              listFiltered = (ArrayList<Contact>) filterResults.values;
              notifyDataSetChanged();
            }
        };
    }
}
