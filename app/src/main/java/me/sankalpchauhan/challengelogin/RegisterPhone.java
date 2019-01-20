package me.sankalpchauhan.challengelogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;

import me.sankalpchauhan.challengelogin.helpers.helper;
import me.sankalpchauhan.challengelogin.helpers.transitionHelper;
import me.sankalpchauhan.challengelogin.listners.OnRevealAnimationListener;

public class RegisterPhone extends AppCompatActivity {
    //Views
    EditText firstnameregphone;
    EditText lastnameregphone;
    EditText phoneNumberphone;
    EditText otpphone;
    Button otpBTNphone;
    Button registerphone;
    RelativeLayout mRlContainer;
    LinearLayout mLLContainer;
    ImageView logoem;

    //Contains commonly used methods
    helper help;

    //Custom Transitions
    transitionHelper transHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);
        firstnameregphone = findViewById(R.id.firstnamephone);
        lastnameregphone = findViewById(R.id.lastnamephone);
        phoneNumberphone = findViewById(R.id.phoneNumber);
        otpphone = findViewById(R.id.otp);
        otpBTNphone = findViewById(R.id.otpBTN);
        registerphone = findViewById(R.id.regBTNphonefinal);
        mRlContainer = findViewById(R.id.containerPhone);
        mLLContainer = findViewById(R.id.LinearPhoneContainer);

        logoem = findViewById(R.id.logophone);

        transHelper = new transitionHelper();
        help = new helper();

        //Attached Slidr for slide activity end
        Slidr.attach(this);

        transHelper.wave(700, 0, logoem);

        //Taking data of first name and last name from previous activity so that user don't have to enter again
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            firstnameregphone.setText(null);
            lastnameregphone.setText(null);
        } else {
            firstnameregphone.setText(extras.getString("firstname"));
            lastnameregphone.setText(extras.getString("lastname"));
        }

        //Transitions
        transHelper.standUp(1500, 0, otpBTNphone);
        transHelper.standUp(1500, 0, registerphone);


        /**
         * Checking for a valid phone number string and making +91 non-erasablee
         */
        phoneNumberphone.setText("+91");
        Selection.setSelection(phoneNumberphone.getText(), phoneNumberphone.getText().length());


        phoneNumberphone.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("+91")) {
                    phoneNumberphone.setText("+91");
                    Selection.setSelection(phoneNumberphone.getText(), phoneNumberphone.getText().length());

                }

            }
        });

//BUTTON CLICKS:
        otpBTNphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNumberphone.getText().length() != 13) {
                    help.DisplayDialog(RegisterPhone.this, "Oops!", "Please check the mobile number that you have entered", "OK");
                } else {
                    //Implement Here
                    help.StandardToast(RegisterPhone.this, "Success");
                    help.addNotification(RegisterPhone.this, "OTP 6432", "6432 is your OTP for registration", R.drawable.logo, 2);
                }
            }
        });


        registerphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DATA INTEGRITY CHECKS
                if (firstnameregphone.getText().toString().equals("") || lastnameregphone.getText().toString().equals("") || phoneNumberphone.toString().equals("")) {
                    help.ImageInToast(RegisterPhone.this, "Please fill in all details", R.drawable.oopsimage);
                    WrongInfoShake();
                } else if (otpphone.getText().toString().equals("")) {
                    help.DisplayDialog(RegisterPhone.this, "Just one more step...", "Please fill in OTP ", "OK");
                } else {
                    if (otpphone.getText().toString().equals("6432")) {
                        help.ImageInToast(RegisterPhone.this, "Registered", R.drawable.thumbsup);
                    } else {
                        help.ImageInToast(RegisterPhone.this, "Invalid OTP", R.drawable.oopsimage);
                    }
                }

            }
        });

        logoem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transHelper.tadaHi(700, 0, logoem);
            }
        });


    }

    //-----------ONCREATE END------------


    //Transitions on back pressed
    @Override
    public void onBackPressed() {
        transHelper.zoomOut(700, 0, logoem);
        findViewById(R.id.childContainerphone).setVisibility(View.INVISIBLE);
        transHelper.animateRevealHide(this, findViewById(R.id.secboxphone), R.color.colorAccent, logoem.getWidth() / 2,
                new OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {
                        RegisterPhone.super.onBackPressed();
                    }

                    @Override
                    public void onRevealShow() {

                    }
                });
    }

    //SHAKE ANIMATION IN CASE OF DATA INTEGRITY FAILIURE
    public void WrongInfoShake() {
        transHelper.shakeAnimation(400, 1, firstnameregphone);
        transHelper.shakeAnimation(400, 1, lastnameregphone);
        transHelper.shakeAnimation(400, 1, otpphone);
        transHelper.shakeAnimation(400, 1, phoneNumberphone);
    }


}
