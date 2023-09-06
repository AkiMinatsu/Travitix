package com.travitix.travitix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityLog extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        //pag navigate sa bottomnavigationview
        bottomNavigationView = findViewById(R.id.bottomnavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    Intent activityLogIntent = new Intent(ActivityLog.this, Homepage.class);
                    startActivity(activityLogIntent);

                }
                else if (item.getItemId() == R.id.activitylog) {
                    Intent activityLogIntent = new Intent(ActivityLog.this, ActivityLog.class);
                    startActivity(activityLogIntent);

                }
                else if (item.getItemId() == R.id.ticket) {
                    Intent receiptIntent = new Intent(ActivityLog.this, Ticket.class);
                    startActivity(receiptIntent);

                }
                else if (item.getItemId() == R.id.profile) {
                    Intent profileIntent = new Intent(ActivityLog.this, Profile.class);
                    startActivity(profileIntent);

                }
                return true;
            }
        });


        // pag pinindot ang scan
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionButton.setPressed(true);
                Intent intent = new Intent(ActivityLog.this, Scan.class);
                startActivity(intent);
            }
        });

        floatingActionButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    floatingActionButton.setPressed(false); // Mag-set ng estado ng pressed bilang false para ibalik sa normal na hitsura
                }
                return false;
            }
        });
    }

    //pabalik sa nakaraan module
    public void goBack(View view) {
        finish();
    }
}
