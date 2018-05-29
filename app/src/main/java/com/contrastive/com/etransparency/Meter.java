package com.contrastive.com.etransparency;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class Meter extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    static float b = 38,unit = 10;
    ImageView fan;
    ImageView light;
    boolean fanOn = false;
    boolean lightOn = false;
    TextView fantext;
    TextView lightText;
    TextView billview;
    TextView unitView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter);
        lightText = (TextView) findViewById(R.id.lightoff);
        fantext = (TextView) findViewById(R.id.fanoff) ;
        fan = (ImageView) findViewById(R.id.fn);
        billview = (TextView) findViewById(R.id.bill);
        unitView = (TextView) findViewById(R.id.unit);
        setTitle("Virtual Meter");
        counter();

            fan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!fanOn) {
                        Glide.with(Meter.this).load(R.drawable.fan).crossFade().into(fan);
                        fanOn = true;
                        counter();
                        fantext.setText("Fan is On");
                    }else {
                        Glide.with(Meter.this).load(R.drawable.fn).crossFade().into(fan);
                        fanOn = false;
                        fantext.setText("Fan is off");
                        counter();
                    }
                }
            });

        light = (ImageView) findViewById(R.id.light);


            light.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!lightOn) {
                        light.setBackgroundResource(R.drawable.loghton);
                        lightOn = true;
                        lightText.setText("Light is on");
                        counter();
                    }else {
                        light.setBackgroundResource(R.drawable.light);
                        lightOn = false;
                        lightText.setText("Light is off");
                        counter();
                    }
                }
            });

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseInstance.getReference("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                bill bill = dataSnapshot.getValue(bill.class);
                b = Float.parseFloat(bill.getBill());
                unit = Float.parseFloat(bill.getUnit());
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateBill();

            }
        }, 0, 3000);

        updateview();
    }

    private void updateview() {
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                billview.setText(new DecimalFormat("##.##").format(b)+" Tk");
                                unitView.setText(new DecimalFormat("##.##").format(unit)+" unit");
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    private void counter() {
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    if (fanOn){
                        unit += 0.00833;
                        b += 0.031;
                    }

                }
            }, 0, 2000);


            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (lightOn) {
                        unit += 0.00833;
                        b += 0.031;
                    }
                }
            }, 0, 2000);

    }

    private void updateBill() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        bill bill = new bill(b+"",unit+"");

        mFirebaseInstance.getReference("users").setValue(bill);
    }


}
