package com.worked.money_movement.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.worked.money_movement.R;

public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);

        setVisibility(TextUtils.isEmpty(getText()) ? View.GONE : View.VISIBLE);
    }

    @Override
    public void setEnabled(boolean isEnabled) {
        super.setEnabled(isEnabled);

        int enabled = getResources().getColor(R.color.title);

        int disabled = getResources().getColor(R.color.title_disabled);

        setTextColor(isEnabled ? enabled : disabled);
    }
}
