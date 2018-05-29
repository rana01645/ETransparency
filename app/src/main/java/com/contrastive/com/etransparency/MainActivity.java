package com.contrastive.com.etransparency;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    Button monthly;
    TextView billText;
    TextView unitText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        billText = (TextView) findViewById(R.id.bill);
        unitText = (TextView) findViewById(R.id.unit);
        monthly = (Button) findViewById(R.id.monthly);

        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,monthlyUsage.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent j = new Intent(MainActivity.this,contact.class);
            startActivity(j);
            }
        });
        mFirebaseInstance = FirebaseDatabase.getInstance();
        updateData();
    }

    private void updateData() {
        mFirebaseInstance.getReference("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                bill b = dataSnapshot.getValue(bill.class);
                billText.setText(new DecimalFormat("##.##").format(Float.parseFloat(b.getBill()))+" Tk");
                unitText.setText(new DecimalFormat("##.##").format(Float.parseFloat(b.getUnit()))+" unit");
//                Log.d(TAG, "User name: " + b.getBill() + ", email " + b.getUnit());
//                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
          //  return true;
        //}

        //return super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.action_settings){
            Intent j = new Intent(MainActivity.this,Meter.class);
            startActivity(j);
            return true;
        }
        else if(item.getItemId()==R.id.contact){
            Intent j = new Intent(MainActivity.this,EmergencyContacts.class);
            startActivity(j);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
