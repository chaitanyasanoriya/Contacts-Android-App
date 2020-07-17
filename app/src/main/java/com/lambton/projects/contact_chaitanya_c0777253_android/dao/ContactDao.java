package com.lambton.projects.contact_chaitanya_c0777253_android.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lambton.projects.contact_chaitanya_c0777253_android.models.Contact;

import java.util.List;

@Dao
public interface ContactDao
{
    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("Select * from contact_table ORDER BY firstName")
    LiveData<List<Contact>> getAllContacts();
}
