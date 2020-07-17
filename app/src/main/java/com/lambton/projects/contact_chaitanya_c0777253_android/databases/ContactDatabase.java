package com.lambton.projects.contact_chaitanya_c0777253_android.databases;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lambton.projects.contact_chaitanya_c0777253_android.dao.ContactDao;
import com.lambton.projects.contact_chaitanya_c0777253_android.models.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase
{
    private static ContactDatabase mInstance;
    public abstract ContactDao mContactDao();

    public static synchronized ContactDatabase getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDatabase.class, "car_inventory_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }
}
