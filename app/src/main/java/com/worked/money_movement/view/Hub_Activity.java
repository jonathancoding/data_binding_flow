package com.worked.money_movement.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.worked.money_movement.R;
import com.worked.money_movement.utils.ParseUtils;
import com.worked.money_movement.utils.shared.Constants;

public class Hub_Activity extends Activity {

    private static final String TAG = Hub_Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_layout);

        // set up once
        if (savedInstanceState == null) {
            setupData();

            setUpFragment();
        }
    }

    /**
     * Set Up Hub Data
     */
    private void setupData() {
        ParseUtils.parseJsonData(this.getBaseContext(), Constants.MONEY_MOVEMENT_JSON);
    }

    /**
     * Set Up Fragment
     */
    private void setUpFragment() {
        Hub_Fragment hub_fragment = Hub_Fragment.newInstance();

        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        if (fm.findFragmentByTag(Hub_Fragment.TAG) == null) {
            ft.add(R.id.container, hub_fragment, Hub_Fragment.TAG);

            ft.commit();
        }
    }
}
