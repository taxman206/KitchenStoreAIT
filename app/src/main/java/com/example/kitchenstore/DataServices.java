package com.example.kitchenstore;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaRouter;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class DataServices {
    private static final FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference mRef=database.getReference();
    private StorageReference mStorageRef;






    public int getProductCount() {
        final ArrayList<Product> productList = new ArrayList<>();
        mRef = mRef.child("OnlineStore").child("StoreStocking");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Product product = new Product();
                    product.setAmount(dataSnapshot.child("amount").getValue(Integer.class));
                    product.setExpiry(dataSnapshot.child("expiryDays").getValue(Integer.class));
                    product.setName(dataSnapshot.child("name").getValue(String.class));
                    product.setPrice(dataSnapshot.child("price").getValue(Double.class));
                    productList.add(product);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return productList.size();

    }


}
