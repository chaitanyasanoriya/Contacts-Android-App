package com.lambton.projects.contact_chaitanya_c0777253_android.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private String email;

    @NonNull
    private String phoneNumber;

    @Nullable
    private String Address;

    public Contact()
    {

    }

    public Contact(@NonNull String firstName, @Nullable String lastName, @Nullable String email, @NonNull String phoneNumber, @Nullable String address)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        Address = address;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address)
    {
        Address = address;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getAddress()
    {
        return Address;
    }
}
