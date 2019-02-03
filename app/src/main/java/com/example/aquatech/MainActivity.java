package com.example.aquatech;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class MainActivity extends AppCompatActivity {

    TextView a,v;
    TextView name;
    int x=0;
    int max=0;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = (TextView) findViewById(R.id.a);
        v = (TextView) findViewById(R.id.v);
        name = (TextView) findViewById(R.id.name);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.child("analog").getValue().toString();
                a.setText(s);
                String d = dataSnapshot.child("voltage").getValue().toString();
                v.setText(d);
                int m =  Integer.valueOf(s);
                if(max < m) {
                    max = m;
                }
               ++x;
               if(x>5) {

                   if (max >= 600 && max <= 800) {
                       name.setText("Potable");
                   }
                   else if(max>800 && max<2000 || max <600 && max>200 ){
                       name.setText("Not Potable");
                   }
                   Toast.makeText(MainActivity.this , max+ " ",Toast.LENGTH_LONG).show();
                   x=0;
                   max=0;
                   }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}

