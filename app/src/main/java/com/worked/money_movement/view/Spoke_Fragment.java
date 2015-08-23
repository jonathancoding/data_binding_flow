package com.worked.money_movement.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.worked.money_movement.R;
import com.worked.money_movement.view_model_controller.HubRow;

public class Spoke_Fragment extends Fragment {

    public static final String TAG = Spoke_Fragment.class.getSimpleName();

    private int currentValue = 0;

    /**
     * Use this factory method to create a new instance of this fragment.
     *
     * @return A new instance of fragment Spoke_Fragment.
     */
    public static Spoke_Fragment newInstance() {
        return new Spoke_Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.spoke_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupDatePicker();

        setupCloseButton();

        setupResetButton();
    }

    private void setupDatePicker() {
        NumberPicker picker = (NumberPicker) getView().findViewById(R.id.number_picker);

        picker.setMinValue(0);

        picker.setMaxValue(5);

        // get current value
        if (isInteger(HubRow.getCurrent().getSubtitle())) {
            currentValue = Integer.valueOf(HubRow.getCurrent().getSubtitle());
        }

        picker.setValue(currentValue);

        // set current value
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                currentValue = newVal;
            }
        });
    }

    private void setupCloseButton() {
        Button back = (Button) getView().findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HubRow.getCurrent().setSubtitle(String.valueOf(currentValue));

                HubRow.unlockNext();

                getActivity().finish();
            }
        });
    }

    private void setupResetButton() {
        Button reset = (Button) getView().findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HubRow.resetHub(HubRow.getCurrent().getIndex());

                getActivity().finish();
            }
        });
    }

    /**
     * Helper : string represents an integer
     *
     * @param input string
     * @return
     */
    protected boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
