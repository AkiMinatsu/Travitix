package com.travitix.travitix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Scan extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        //pag navigate sa bottomnavigationview
        bottomNavigationView = findViewById(R.id.bottomnavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    highlightMenuItem(item);
                    Intent activityLogIntent = new Intent(Scan.this, Homepage.class);
                    startActivity(activityLogIntent);

                } else if (item.getItemId() == R.id.activitylog) {
                    highlightMenuItem(item);
                    Intent activityLogIntent = new Intent(Scan.this, ActivityLog.class);
                    startActivity(activityLogIntent);

                } else if (item.getItemId() == R.id.ticket) {
                    highlightMenuItem(item);
                    Intent receiptIntent = new Intent(Scan.this, Ticket.class);
                    startActivity(receiptIntent);

                } else if (item.getItemId() == R.id.profile) {
                    highlightMenuItem(item);
                    Intent profileIntent = new Intent(Scan.this, Profile.class);
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
                Intent intent = new Intent(Scan.this, Scan.class);
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

    private void highlightMenuItem(MenuItem selectedItem) {
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            menuItem.setChecked(menuItem == selectedItem);
        }
    }

    //pabalik sa nakaraan module
    public void goBack(View view) {
        finish();
    }

    public void qrCode(View view){
        Intent intent = new Intent(this, violatorsinformation.class);
        startActivity(intent);
    }

    public void RFID(View view){
        Intent intent = new Intent(this, RFIDconnector.class);
        startActivity(intent);
    }
}