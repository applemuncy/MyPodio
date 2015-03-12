package com.just4me.apple.testing;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;

import com.podio.sdk.Session;
import com.podio.sdk.Request.ResultListener;

public class MainActivity extends ActionBarActivity {
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getName();
        Log.d(TAG, "onCreate working");
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate after setContentView");

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.main_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            FragmentSplash firstFragment = new FragmentSplash();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
//            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, firstFragment).commit();
            LoginFragment lf = new LoginFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, lf).commit();

        }

    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            Log.d(TAG, "onCreateOptionMenu working");
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            Log.d(TAG, "onOptionsItemSelected working");
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            } else if (id == R.id.action_add_item) {
                Toaster t = new Toaster(getApplicationContext());
                t.make(R.string.add_item);

                //    Fragment f =
//                        getFragmentManager().beginTransaction().

                return true;
            } else if (id == R.id.action_settings) {
                //do toast
                Toaster t = new Toaster(getApplicationContext());
                t.make(R.string.settings_pressed);
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    }
