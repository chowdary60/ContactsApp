package com.usbank.contactsapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.usbank.contactsapp.R;

/**
 * Created by madhu on 4/9/19.
 */

public class ContactsViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public   TextView email;
    public TextView address;

    public ContactsViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        email = itemView.findViewById(R.id.email);
        address = itemView.findViewById(R.id.address);


    }

}
