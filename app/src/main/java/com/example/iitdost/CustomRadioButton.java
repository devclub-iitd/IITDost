package com.example.iitdost;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ankurshaswat on 16/3/18.
 */

public class CustomRadioButton extends RelativeLayout implements RadioCheckable {
        // Views
        private TextView mTextView;

        // Constants
        public int DEFAULT_TEXT_COLOR = ResourcesCompat.getColor(getResources(), R.color.blue, null);

        // Attribute Variables
        private String mText;
//        private String mUnit;
        private int mTextColor;
//        private int mUnitTextColor;
        private int mPressedTextColor =ResourcesCompat.getColor(getResources(), R.color.white, null);;

        // Variables
        private Drawable mInitialBackgroundDrawable;
        private OnClickListener mOnClickListener;
        private OnTouchListener mOnTouchListener;
        private boolean mChecked;
        private ArrayList<OnCheckedChangeListener> mOnCheckedChangeListeners = new ArrayList<>();


        //================================================================================
        // Constructors
        //================================================================================

        public CustomRadioButton(Context context) {
            super(context);
            setupView();
        }

        public CustomRadioButton(Context context, AttributeSet attrs) {
            super(context, attrs);
            parseAttributes(attrs);
            setupView();
        }

        @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
        public CustomRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            parseAttributes(attrs);
            setupView();
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public CustomRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            parseAttributes(attrs);
            setupView();
        }

        //================================================================================
        // Init & inflate methods
        //================================================================================

        private void parseAttributes(AttributeSet attrs) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.CustomRadioButton, 0, 0);
            Resources resources = getContext().getResources();
            try {
                mText = a.getString(R.styleable.CustomRadioButton_customButtonText);
                mTextColor = a.getColor(R.styleable.CustomRadioButton_customButtonTextColor, resources.getColor(R.color.blue));
                mPressedTextColor = a.getColor(R.styleable.CustomRadioButton_customButtonPressedTextColor, resources.getColor(R.color.white));
            } finally {
                a.recycle();
            }
        }

        // Template method
        private void setupView() {
            inflateView();
            bindView();
            setCustomTouchListener();
        }

        protected void inflateView() {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            inflater.inflate(R.layout.custom_button_department, this, true);
            mTextView = (TextView) findViewById(R.id.departmentRadio);
//            mUnitTextView = (TextView) findViewById(R.id.text_view_unit);
            mInitialBackgroundDrawable = getBackground();
        }

        protected void bindView() {
            if (mTextColor != DEFAULT_TEXT_COLOR) {
                mTextView.setTextColor(mTextColor);
            }
//            if (mValueTextColor != DEFAULT_TEXT_COLOR) {
//                mValueTextView.setTextColor(mValueTextColor);
//            }
            mTextView.setText(mText);
//            mValueTextView.setText(mValue);
        }

        //================================================================================
        // Overriding default behavior
        //================================================================================

        @Override
        public void setOnClickListener(@Nullable OnClickListener l) {
            mOnClickListener = l;
        }

        private void setCustomTouchListener() {
            super.setOnTouchListener(new TouchListener());
        }

        @Override
        public void setOnTouchListener(OnTouchListener onTouchListener) {
            mOnTouchListener = onTouchListener;
        }

        public OnTouchListener getOnTouchListener() {
            return mOnTouchListener;
        }

        private void onTouchDown(MotionEvent motionEvent) {
            setChecked(true);
        }

        private void onTouchUp(MotionEvent motionEvent) {
            // Handle user defined click listeners
            if (mOnClickListener != null) {
                mOnClickListener.onClick(this);
            }
        }
        //================================================================================
        // Public methods
        //================================================================================

        public void setCheckedState() {
            setBackgroundResource(R.drawable.rounded_corner_active);
            mTextView.setTextColor(mPressedTextColor);
//            mUnitTextView.setTextColor(mPressedTextColor);
        }

        public void setNormalState() {
            setBackground(mInitialBackgroundDrawable);
            mTextView.setTextColor(mTextColor);
//            mUnitTextView.setTextColor(mUnitTextColor);
        }

        public String getText() {
            return mText;
        }

        public void setText(String value) {
            mText = value;
        }

//        public String getUnit() {
//            return mUnit;
//        }

//        public void setUnit(String unit) {
//            mUnit = unit;
//        }

        //================================================================================
        // Checkable implementation
        //================================================================================

        @Override
        public void setChecked(boolean checked) {
            if (mChecked != checked) {
                mChecked = checked;
                if (!mOnCheckedChangeListeners.isEmpty()) {
                    for (int i = 0; i < mOnCheckedChangeListeners.size(); i++) {
                        mOnCheckedChangeListeners.get(i).onCheckedChanged(this, mChecked);
                    }
                }
                if (mChecked) {
                    setCheckedState();
                } else {
                    setNormalState();
                }
            }
        }

        @Override
        public boolean isChecked() {
            return mChecked;
        }

        @Override
        public void toggle() {
            setChecked(!mChecked);
        }

        @Override
        public void addOnCheckChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
            mOnCheckedChangeListeners.add(onCheckedChangeListener);
        }

        @Override
        public void removeOnCheckChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
            mOnCheckedChangeListeners.remove(onCheckedChangeListener);
        }

        //================================================================================
        // Inner classes
        //================================================================================
        private final class TouchListener implements OnTouchListener {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        onTouchDown(event);
                        break;
                    case MotionEvent.ACTION_UP:
                        onTouchUp(event);
                        break;
                }
                if (mOnTouchListener != null) {
                    mOnTouchListener.onTouch(v, event);
                }
                return true;
            }
        }
    }

