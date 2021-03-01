package com.example.kitchenstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class StoreActicity extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<Product> productList=new ArrayList<>();
    private static final FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference mRef=database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_acticity);
        rv =findViewById(R.id.the_rv);

        mRef= mRef.child("OnlineStore").child("StoreStocking");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Product product=new Product();
                    product.setAmount(dataSnapshot.child("amount").getValue(Integer.class));
                    Log.d("aaa",dataSnapshot.child("amount").getValue(Long.class).toString());
                    product.setExpiry(dataSnapshot.child("expiryDays").getValue(Integer.class));
                    product.setName(dataSnapshot.child("name").getValue(String.class));
                    product.setPrice(dataSnapshot.child("price").getValue(Double.class));
                    productList.add(product);
                }
                RvAdapter adapter=new RvAdapter(StoreActicity.this,productList,productList.size());
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(StoreActicity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}