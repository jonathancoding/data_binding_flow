package com.worked.money_movement.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.worked.money_movement.R;
import com.worked.money_movement.view_model_controller.HubRow;

public class Spoke_Activity extends Activity {

    private static final String TAG = Spoke_Activity.class.getSimpleName();

    private Spoke_Fragment hub_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_layout);

        // set up once
        if (savedInstanceState == null) {
            setUpFragment();
        }
    }

    /**
     * Set Up Fragment
     */
    private void setUpFragment() {
        hub_fragment = Spoke_Fragment.newInstance();

        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        if (fm.findFragmentByTag(Spoke_Fragment.TAG) == null) {
            ft.add(R.id.container, hub_fragment, Spoke_Fragment.TAG);

            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // reset all rows
        HubRow.resetHub();
    }
}
