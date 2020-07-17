package com.lambton.projects.contact_chaitanya_c0777253_android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lambton.projects.contact_chaitanya_c0777253_android.R;
import com.lambton.projects.contact_chaitanya_c0777253_android.adapters.ContactRecyclerViewAdapter;
import com.lambton.projects.contact_chaitanya_c0777253_android.models.Contact;
import com.lambton.projects.contact_chaitanya_c0777253_android.viewmodels.ContactViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private TextView mContactNumTextView;
    private RecyclerView mRecyclerView;
    private TextView mNoContactsTextView;
    private ContactViewModel mContactViewModel;
    private List<Contact> mContactList = null;
    private ContactRecyclerViewAdapter mContactRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMemberVariables();
        setupRecyclerView();
    }

    private void setMemberVariables()
    {
        mContactNumTextView = findViewById(R.id.num_contacts_textview);
        mRecyclerView = findViewById(R.id.recyclerview);
        mNoContactsTextView = findViewById(R.id.no_contacts_textview);
        mContactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        mContactViewModel.getAllContacts().observe(this, contacts -> setContacts(contacts));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
//        getContacts();
    }

    private void setupRecyclerView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mContactRecyclerViewAdapter = new ContactRecyclerViewAdapter(mContactList, this);
        mRecyclerView.setAdapter(mContactRecyclerViewAdapter);
    }

    private void setContacts(List<Contact> contacts)
    {
//        mContactList = mContactViewModel.getAllContacts();
        if(contacts != null && contacts.size() > 0)
        {
            mNoContactsTextView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mContactNumTextView.setText(getString(R.string.number_of_contacts)+contacts.size());
        }
        else
        {
            mNoContactsTextView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            mContactNumTextView.setText(getString(R.string.number_of_contacts)+0);
        }
        mContactRecyclerViewAdapter.setContacts(contacts);
    }

    public void addNewContactClicked(View view)
    {
    }
}