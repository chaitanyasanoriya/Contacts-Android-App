package com.lambton.projects.contact_chaitanya_c0777253_android.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lambton.projects.contact_chaitanya_c0777253_android.models.Contact;
import com.lambton.projects.contact_chaitanya_c0777253_android.repositories.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel
{
    private ContactRepository mRepository;
    private LiveData<List<Contact>> mAllContacts;

    public ContactViewModel(@NonNull Application application)
    {
        super(application);
        System.out.println(1);
        mRepository = new ContactRepository(application);
        System.out.println(2);
        mAllContacts = mRepository.getAllContacts();
        System.out.println(3);
    }

    public void insert(Contact contact)
    {
        mRepository.insert(contact);
    }

    public void update(Contact contact)
    {
        mRepository.update(contact);
    }

    public void delete(Contact contact)
    {
        mRepository.delete(contact);
    }


    public LiveData<List<Contact>> getAllContacts()
    {
        return mAllContacts;
    }
}
