package com.lambton.projects.contact_chaitanya_c0777253_android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lambton.projects.contact_chaitanya_c0777253_android.R;
import com.lambton.projects.contact_chaitanya_c0777253_android.models.Contact;
import com.lambton.projects.contact_chaitanya_c0777253_android.viewmodels.ContactViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactDetailsActivity extends AppCompatActivity
{
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private Button mDeleteButton;
    private EditText mFNameEditText, mLNameEditText, mPhoneEditText, mEmailEditText, mAddressEditText;
    private TextView mHeadingTextView;
    private ContactViewModel mContactViewModel;
    private Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        setMemberVariables();
        setData();
    }

    private void setMemberVariables()
    {
        mDeleteButton = findViewById(R.id.delete_btn);
        mFNameEditText = findViewById(R.id.first_name_edittext);
        mLNameEditText = findViewById(R.id.last_name_edittext);
        mPhoneEditText = findViewById(R.id.phone_edittext);
        mEmailEditText = findViewById(R.id.email_edittext);
        mAddressEditText = findViewById(R.id.address_edittext);
        mHeadingTextView = findViewById(R.id.heading_textview);
        mContact = (Contact) getIntent().getSerializableExtra("contact");
        mContactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
    }

    private void setData()
    {
        if(mContact == null)
        {
            mDeleteButton.setVisibility(View.GONE);
            return;
        }
        mFNameEditText.setText(mContact.getFirstName());
        mLNameEditText.setText(mContact.getLastName());
        mPhoneEditText.setText(mContact.getPhoneNumber());
        mEmailEditText.setText(mContact.getEmail());
        mAddressEditText.setText(mContact.getAddress());
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
        if(phoneNum.isEmpty() || phoneNum.length() < 7)
        {
            mPhoneEditText.setError("Phone Number is required");
            mPhoneEditText.requestFocus();
            return;
        }
        if(!email.isEmpty() && validate(email))
        {
            mEmailEditText.setError("Enter a valid email address");
            mEmailEditText.requestFocus();
            return;
        }
        if(mContact == null)
        {
            Contact contact = new Contact(fName,lName,email,phoneNum,address);
            mContactViewModel.insert(contact);
        }
        else
        {
            mContact.setFirstName(fName);
            mContact.setLastName(lName);
            mContact.setPhoneNumber(phoneNum);
            mContact.setEmail(email);
            mContact.setAddress(address);
            mContactViewModel.update(mContact);
        }
        finish();
    }

    public void deleteClicked(View view)
    {
        showDeleteContact();
    }

    public void showDeleteContact()
    {
        new AlertDialog.Builder(this)
                .setTitle("Delete Contact")
                .setMessage("Are you sure you want to delete this contact?")
                .setCancelable(true)
                .setPositiveButton("Yes", (dialog, which) -> deleteContact())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .create().show();
    }

    private void deleteContact()
    {
        mContactViewModel.delete(mContact);
        finish();
    }

    public boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}