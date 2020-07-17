package com.lambton.projects.contact_chaitanya_c0777253_android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lambton.projects.contact_chaitanya_c0777253_android.R;
import com.lambton.projects.contact_chaitanya_c0777253_android.models.Contact;
import com.lambton.projects.contact_chaitanya_c0777253_android.viewmodels.ContactViewModel;

public class ContactDetailsActivity extends AppCompatActivity
{
    private Button mDeleteButton;
    private EditText mFNameEditText, mLNameEditText, mPhoneEditText, mEmailEditText, mAddressEditText;
    private ContactViewModel mContactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        setMemberVariables();
    }

    private void setMemberVariables()
    {
        mDeleteButton = findViewById(R.id.delete_btn);
        mFNameEditText = findViewById(R.id.first_name_edittext);
        mLNameEditText = findViewById(R.id.last_name_edittext);
        mPhoneEditText = findViewById(R.id.phone_edittext);
        mEmailEditText = findViewById(R.id.email_edittext);
        mAddressEditText = findViewById(R.id.address_edittext);
        mContactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
    }

    public void saveClicked(View view)
    {
        String fName = mFNameEditText.getText().toString();
        String lName = mLNameEditText.getText().toString();
        String phoneNum = mPhoneEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String address = mAddressEditText.getText().toString();
        if(fName.isEmpty())
        {
            mFNameEditText.setError("First is name is required");
            mFNameEditText.requestFocus();
            return;
        }
        if(phoneNum.isEmpty())
        {
            mPhoneEditText.setError("Phone Number is required");
            mPhoneEditText.requestFocus();
            return;
        }
        Contact contact = new Contact(fName,lName,email,phoneNum,address);
        mContactViewModel.insert(contact);
        finish();
    }
}