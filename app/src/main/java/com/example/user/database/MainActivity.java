package com.example.user.database;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button create,show;
    DatabaseReference firebase,email1,firebase2;
    EditText name;
    EditText email;
    TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView) findViewById(R.id.textView2);
        t2=(TextView) findViewById(R.id.textView4);

        name=(EditText) findViewById(R.id.name1);
        email=(EditText) findViewById(R.id.emailField);

        email1=FirebaseDatabase.getInstance().getReference().child("User").child("Email") ;
        firebase= FirebaseDatabase.getInstance().getReference();
        firebase2=FirebaseDatabase.getInstance().getReference().child("User").child("Name");
        create=(Button) findViewById(R.id.button2);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namef=name.getText().toString();
                String emailf=email.getText().toString();
                HashMap<String, String> DataBase= new HashMap<String, String>();
                DataBase.put("Name", namef);
                DataBase.put("Email", emailf);
                firebase.child("User").setValue(DataBase).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this,"Stored", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Not Stored", Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });
        show=(Button) findViewById(R.id.button3);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent i=new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);*/
                email1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String email2=dataSnapshot.getValue().toString();
                        t2.setText("Email:" + email2);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                /*firebase2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String name3=dataSnapshot.getValue().toString();
                        t1.setText("Name:" + name3);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/

            }
        });

    }
}
