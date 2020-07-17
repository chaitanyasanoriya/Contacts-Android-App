package com.lambton.projects.contact_chaitanya_c0777253_android.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.lambton.projects.contact_chaitanya_c0777253_android.dao.ContactDao;
import com.lambton.projects.contact_chaitanya_c0777253_android.databases.ContactDatabase;
import com.lambton.projects.contact_chaitanya_c0777253_android.models.Contact;

import java.util.List;

public class ContactRepository
{
    private ContactDao mContactDao;
    private LiveData<List<Contact>> mContactList;

    public ContactRepository(Application application)
    {
        System.out.println(4);
        ContactDatabase db = ContactDatabase.getInstance(application);
        System.out.println(5);
        mContactDao = db.mContactDao();
        System.out.println(6);
        mContactList = mContactDao.getAllContacts();
        System.out.println(7);
    }

    public void insert(Contact contact)
    {
        new ContactRepository.InsertCarsAsyncTask(mContactDao).execute(contact);
    }

    public void update(Contact contact)
    {
        new ContactRepository.UpdateCarsAsyncTask(mContactDao).execute(contact);
    }

    public void delete(Contact contact)
    {
        new ContactRepository.DeleteCarsAsyncTask(mContactDao).execute(contact);
    }

    public LiveData<List<Contact>> getAllContacts()
    {
        return mContactList;
    }

    private static class InsertCarsAsyncTask extends AsyncTask<Contact, Void, Void>
    {
        private ContactDao mContactDao;

        private InsertCarsAsyncTask(ContactDao mContactDao)
        {
            this.mContactDao = mContactDao;
        }

        @Override
        protected Void doInBackground(Contact ... contacts)
        {
            mContactDao.insert(contacts[0]);
            return null;
        }
    }

    private static class UpdateCarsAsyncTask extends AsyncTask<Contact, Void, Void>
    {
        private ContactDao mContactDao;

        private UpdateCarsAsyncTask(ContactDao mContactDao)
        {
            this.mContactDao = mContactDao;
        }

        @Override
        protected Void doInBackground(Contact... contact)
        {
            mContactDao.update(contact[0]);
            return null;
        }
    }

    private static class DeleteCarsAsyncTask extends AsyncTask<Contact, Void, Void>
    {
        private ContactDao mContactDao;

        private DeleteCarsAsyncTask(ContactDao mContactDao)
        {
            this.mContactDao = mContactDao;
        }

        @Override
        protected Void doInBackground(Contact... contact)
        {
            mContactDao.delete(contact[0]);
            return null;
        }
    }

}
