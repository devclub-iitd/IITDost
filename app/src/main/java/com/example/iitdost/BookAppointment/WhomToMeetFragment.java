package com.example.iitdost.BookAppointment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.iitdost.R;

/**
 * Created by Shashwat Shivam on 25-3-18.
 */

public class WhomToMeetFragment extends Fragment {

    String department;
    RadioGroup typeOfStaffRadioGrp;
    LinearLayout staffDisplayList;
    private View prevSelected;

    public WhomToMeetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_whom_to_meet, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initializeView(view);
    }


    void setDepartment(String department) {
        this.department = department;
    }

    private void initializeView(View view) {
        staffDisplayList= view.findViewById(R.id.profList);

populateStaffList();

        typeOfStaffRadioGrp = view.findViewById(R.id.selProfRadioGrp);

        typeOfStaffRadioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    //TODO : optimize this based on state (Not refill if already in correct state (Depending on previous state)
                    case R.id.facultyRadio:
                        staffDisplayList.removeAllViews();
                        populateStaffList();
                        //TODO: addChildViews(array,staffDisplayList);
                        break;
                    case R.id.otherStaffRadio:
                        staffDisplayList.removeAllViews();
                        populateStaffList();
                        //TODO: addChildViews(array,staffDisplayList);
                        break;
                    default:
                        break;
                }

            }
        });

        typeOfStaffRadioGrp.check(R.id.facultyRadio);


//        staffDisplayList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//                RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
//
//                ((BookAppointmentActivity) getActivity()).changeState(BookAppointmentActivity.State.SELECT_DATE, rb.getText().toString());
//            }
//        });



    }


//    public void addFaculty() {
//
//        for (int i = 0; i < 10; i++) {
//            RadioButton radioButton = new RadioButton(getContext());
//            radioButton.setId(i);
//            radioButton.setText("Proffff");
//            staffDisplayList.addView(radioButton);
//        }
//    }

    private void populateStaffList() {
        for(int i=0;i<8;i++){

            View v=View.inflate(getContext(), R.layout.template_prof_select, null);
            ImageView profImage=v.findViewById(R.id.profImage);
            Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),
                    R.drawable.facebookplaceholder1);
            profImage.setImageBitmap(getRoundedCornerBitmap(icon,200));
            profImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(prevSelected==null){
                        prevSelected=view;
                    }else{
                        prevSelected.setSelected(false);
                        ImageView prevImageView = prevSelected.findViewById(R.id.profImage);
                        prevImageView.setColorFilter(Color.argb(0,0,0,0));
                        prevSelected=view;
                    }

                    view.setSelected(true);
                    ImageView profImage= view.findViewById(R.id.profImage);

                    profImage.setColorFilter(Color.argb(60, 0, 255, 0));

                    ((BookAppointmentActivity)getActivity()).changeState(BookAppointmentActivity.State.SELECT_DATE,"PROF NAME PASSES HERE");
                }
            });

            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMarginEnd(10);
            layoutParams.setMarginStart(10);
            v.setLayoutParams(layoutParams);

            staffDisplayList.addView(v);
        }
    }



        public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                    .getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final RectF rectF = new RectF(rect);

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, (float) pixels, (float) pixels, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);

            return output;
        }
    }


