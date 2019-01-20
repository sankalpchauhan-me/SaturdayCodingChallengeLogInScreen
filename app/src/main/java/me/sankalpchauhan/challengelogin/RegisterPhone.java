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


        //Transition
        transHelper = new transitionHelper();
        mRlContainer = findViewById(R.id.containerPhone);
        mLLContainer = findViewById(R.id.LinearPhoneContainer);

        logoem = findViewById(R.id.logophone);


        help = new helper();

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            firstnameregphone.setText(null);
            lastnameregphone.setText(null);
        } else {
            firstnameregphone.setText(extras.getString("firstname"));
            lastnameregphone.setText(extras.getString("lastname"));
        }


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


        otpBTNphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNumberphone.getText().length() != 13) {
                    help.DisplayDialog(RegisterPhone.this, "Oops!", "Please check the mobile number that you have entered", "OK");
                } else {
                    //Implement Here
                    help.StandardToast(RegisterPhone.this, "Clicked");
                }
            }
        });


        registerphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstnameregphone.getText().toString().equals("") || lastnameregphone.getText().toString().equals("") || phoneNumberphone.toString().equals("")) {
                    help.DisplayDialog(RegisterPhone.this, "Oops!", "Please fill in all details", "OK");
                } else if (otpphone.getText().toString().equals("")) {
                    help.DisplayDialog(RegisterPhone.this, "Just one more step...", "Please fill in OTP ", "OK");
                } else {
                    Toast.makeText(RegisterPhone.this, "Clicked", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    //Transitions on back pressed
    @Override
    public void onBackPressed() {
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

}
