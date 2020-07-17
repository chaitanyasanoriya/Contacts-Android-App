package com.lambton.projects.contact_chaitanya_c0777253_android.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.lambton.projects.contact_chaitanya_c0777253_android.R;
import com.lambton.projects.contact_chaitanya_c0777253_android.models.Contact;

import java.util.List;

public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder>
{
    private List<Contact> mContactList;
    private Context mContext;


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView mImageView;
        public TextView mNameTextView;
        public TextView mPhoneTextView;
        public TextView mEmailTextView;
        public TextView mAddressTextView;
        public ConstraintLayout mMainLayout;
        public ConstraintLayout mAdditionalInfoLayout;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.name_textview);
            mImageView = itemView.findViewById(R.id.imageview);
            mPhoneTextView = itemView.findViewById(R.id.phone_textview);
            mEmailTextView = itemView.findViewById(R.id.email_textview);
            mAddressTextView = itemView.findViewById(R.id.address_textview);
            mMainLayout = itemView.findViewById(R.id.main_layout);
            mAdditionalInfoLayout = itemView.findViewById(R.id.additional_info_layout);
        }
    }

    public ContactRecyclerViewAdapter(List<Contact> contacts, Context context)
    {
        this.mContactList = contacts;
        this.mContext = context;
    }

    public void setContacts(List<Contact> contacts)
    {
        this.mContactList = contacts;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.custom_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactRecyclerViewAdapter.ViewHolder holder, int position)
    {
        Contact contact = mContactList.get(position);
        holder.mPhoneTextView.setText(contact.getPhoneNumber());
        holder.mNameTextView.setText(getName(contact));
        holder.mEmailTextView.setText(contact.getEmail());
        holder.mAddressTextView.setText(contact.getAddress());
        String initials = contact.getFirstName().charAt(0) + String.valueOf(contact.getLastName().charAt(0));
        holder.mImageView.setImageBitmap(createImage(50,50,mContext.getColor(R.color.colorPrimaryDark),initials));
        holder.mMainLayout.setOnClickListener(view ->
        {
            if(holder.mAdditionalInfoLayout.getVisibility() == View.VISIBLE)
            {
                holder.mAdditionalInfoLayout.setVisibility(View.GONE);
            }
            else
            {
                holder.mAdditionalInfoLayout.setVisibility(View.VISIBLE);
            }
        });
        holder.mMainLayout.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                return false;
            }
        });
    }

    public Bitmap createImage(int width, int height, int color, String name) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint2 = new Paint();
        paint2.setColor(color);
        canvas.drawRect(0F, 0F, (float) width, (float) height, paint2);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(72);
        paint.setTextScaleX(1);
        canvas.drawText(name, 75 - 25, 75 + 20, paint);
        return bitmap;
    }

    private String getName(Contact contact)
    {
        return contact.getFirstName() + " " + contact.getLastName();
    }


    @Override
    public int getItemCount()
    {
        if(mContactList == null)
        {
            return 0;
        }
        return mContactList.size();
    }

}