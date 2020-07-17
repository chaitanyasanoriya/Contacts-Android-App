package com.lambton.projects.contact_chaitanya_c0777253_android.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.lambton.projects.contact_chaitanya_c0777253_android.R;
import com.lambton.projects.contact_chaitanya_c0777253_android.adapters.ContactRecyclerViewAdapter;
import com.lambton.projects.contact_chaitanya_c0777253_android.models.Contact;
import com.lambton.projects.contact_chaitanya_c0777253_android.viewmodels.ContactViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity
{
    private TextView mContactNumTextView;
    private RecyclerView mRecyclerView;
    private TextView mNoContactsTextView;
    private ContactViewModel mContactViewModel;
    private List<Contact> mContactList = null;
    private ContactRecyclerViewAdapter mContactRecyclerViewAdapter;
    private SearchView mSearchView;

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
        mSearchView = findViewById(R.id.searchview);
        mSearchView.setOnQueryTextListener(mOnQueryTextListener);
        mContactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        mContactViewModel.getAllContacts().observe(this, contacts ->
        {
            mContactList = contacts;
            setContacts(contacts);
        });
    }

    private void setupRecyclerView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mContactRecyclerViewAdapter = new ContactRecyclerViewAdapter(mContactList, this);
        mRecyclerView.setAdapter(mContactRecyclerViewAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mSimpleCallBack);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void setContacts(List<Contact> contacts)
    {
        if(contacts != null && contacts.size() > 0)
        {
            mNoContactsTextView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mContactNumTextView.setText(getString(R.string.number_of_contacts)+" "+contacts.size());
        }
        else
        {
            mNoContactsTextView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            mContactNumTextView.setText(getString(R.string.number_of_contacts)+" "+0);
        }
        mContactRecyclerViewAdapter.setContacts(contacts);
    }

    public void addNewContactClicked(View view)
    {
        Intent intent = new Intent(MainActivity.this,ContactDetailsActivity.class);
        startActivity(intent);
    }

    SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener()
    {
        @Override
        public boolean onQueryTextSubmit(String query)
        {
            if(query.isEmpty())
            {
                mContactRecyclerViewAdapter.setContacts(mContactList);
                setNumberOfContacts(mContactList.size());
            }
            else
            {
                List<Contact> contacts = new ArrayList<>();
                for(Contact contact: mContactList)
                {
                    if(contact.getFirstName().toLowerCase().contains(query.toLowerCase()) || contact.getLastName().toLowerCase().contains(query.toLowerCase()))
                    {
                        contacts.add(contact);
                    }
                }
                setNumberOfContacts(contacts.size());
                mContactRecyclerViewAdapter.setContacts(contacts);
            }
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText)
        {
            try
            {
                List<Contact> contacts = new ArrayList<>();
                for(Contact contact: mContactList)
                {
                    if(contact.getFirstName().toLowerCase().contains(newText.toLowerCase()) || contact.getLastName().toLowerCase().contains(newText.toLowerCase()))
                    {
                        contacts.add(contact);
                    }
                }
                setNumberOfContacts(contacts.size());
                mContactRecyclerViewAdapter.setContacts(contacts);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return false;
        }
    };

    private void setNumberOfContacts(int size)
    {
        mContactNumTextView.setText(getString(R.string.number_of_contacts)+" "+size);
    }

    ItemTouchHelper.SimpleCallback mSimpleCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT)
    {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
        {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
        {
            int position = viewHolder.getAdapterPosition();
            if(direction == ItemTouchHelper.LEFT)
            {
                Contact contact = mContactRecyclerViewAdapter.deleteContact(position);
                 mContactViewModel.delete(contact);
                Snackbar.make(mRecyclerView,"Deleted "+contact.getFirstName(),Snackbar.LENGTH_LONG).setAction("Undo", v ->
                {
                    mContactRecyclerViewAdapter.addContact(contact,position);
                    mContactViewModel.insert(contact);
                }).show();
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive)
        {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_white_24)
                    .addSwipeLeftLabel("Delete")
                    .setSwipeLeftLabelColor(Color.WHITE)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
}