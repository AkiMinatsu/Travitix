package com.travitix.travitix;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Timer;
import java.util.TimerTask;
import androidx.recyclerview.widget.RecyclerView;

public class Homepage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;
    ViewPager viewPager;
    private int currentPage = 0;
    Timer timer;
    private final long DELAY_MS = 500;
    private final long PERIOD_MS = 3000;
    private RecyclerView mRecyclerView;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    ActivityLog activityLog = new ActivityLog();
    Ticket ticket = new Ticket();
    Profile profile = new Profile();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        viewPager = findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setPageMargin(16);
        viewPager.setPadding(40, 40, 40, 40);
        viewPager.setClipToPadding(true);
        setTimer();

        //pag navigate sa bottomnavigationview
        bottomNavigationView = findViewById(R.id.bottomnavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    highlightMenuItem(item);
                    Intent activityLogIntent = new Intent(Homepage.this, Homepage.class);
                    startActivity(activityLogIntent);

               }
                else if (item.getItemId() == R.id.activitylog) {
                    highlightMenuItem(item);
                    Intent activityLogIntent = new Intent(Homepage.this, ActivityLog.class);
                    startActivity(activityLogIntent);

                }
               else if (item.getItemId() == R.id.ticket) {
                    highlightMenuItem(item);
                    Intent receiptIntent = new Intent(Homepage.this, Ticket.class);
                    startActivity(receiptIntent);

               }
              else if (item.getItemId() == R.id.profile) {
                    highlightMenuItem(item);
                   Intent profileIntent = new Intent(Homepage.this, Profile.class);
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
                Intent intent = new Intent(Homepage.this, Scan.class);
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



    //pag kuha ng image sa carousel
    public class ImageAdapter extends PagerAdapter {
        private Context mContext;
        private int[] mImageIds = new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5,
                                            R.drawable.image6, R.drawable.image7, R.drawable.image8, R.drawable.image9, R.drawable.image10,
                                            R.drawable.image11, R.drawable.image12};

        public ImageAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(mImageIds[position]);
            container.addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }
    }

    private void setTimer() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                viewPager.setCurrentItem(currentPage++, true);
                if (currentPage == 12) {
                    currentPage = 0;
                }
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    //pabalik sa nakaraan module
    public void goBack(View view) {
        finish();
    }
}