package com.worked.money_movement.view;

import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.worked.money_movement.R;
import com.worked.money_movement.databinding.RowBinding;
import com.worked.money_movement.view_model.RowModel;
import com.worked.money_movement.view_model_controller.HubRow;

public class Hub_Fragment extends Fragment {

    public static final String TAG = Hub_Fragment.class.getSimpleName();

    private LinearLayout root;

    /**
     * Factory Method : create a new instance
     *
     * @return a new instance of fragment {@link Hub_Fragment}
     */
    public static Hub_Fragment newInstance() {
        return new Hub_Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hub_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        root = (LinearLayout) view.findViewById(R.id.hub_container);

        setupMoneyMovement(root);
    }

    /**
     * Sets up the Money Movement Component
     *
     * @param root
     */
    private void setupMoneyMovement(@NonNull LinearLayout root) {
        // add rows to hub
        for (final RowModel row : HubRow.getRows()) {
            RowBinding binding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.row, root, true);

            binding.setRow(row);

            binding.rowContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Spoke_Activity.class);

                    HubRow.setCurrent(row); // very important to set this...

                    startActivity(intent);
                }
            });
        }
    }
}
