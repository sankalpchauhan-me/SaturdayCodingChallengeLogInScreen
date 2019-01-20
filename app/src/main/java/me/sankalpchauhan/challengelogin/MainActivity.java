package me.sankalpchauhan.challengelogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.sankalpchauhan.challengelogin.helpers.helper;
import me.sankalpchauhan.challengelogin.helpers.transitionHelper;

public class MainActivity extends AppCompatActivity {

    EditText emailTB;
    EditText passTB;
    Button loginBTN;
    Button registerBTN;
    TextView passForget;
    ImageView logoem;

    //Commonly used methods
    helper help;

    //Transitions
    transitionHelper transhelp;

    //for custom Animation on Logo
    AnimationDrawable logoanimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailTB = findViewById(R.id.email);
        passTB = findViewById(R.id.password);
        loginBTN = findViewById(R.id.logBTN);
        registerBTN = findViewById(R.id.regBTN);
        passForget = findViewById(R.id.forgotpass);
        logoem = findViewById(R.id.logo);

        //Logo Animation
        logoem.setBackgroundResource(R.drawable.logoanimation);
        logoanimation = (AnimationDrawable) logoem.getBackground();

        help = new helper();
        transhelp = new transitionHelper();

        //NETWORK CHECK
        if (!help.isConnected(MainActivity.this))
            help.buildNetworkDialog(MainActivity.this).show();

        //Transitions
        transhelp.fadeIn(600, 0, findViewById(R.id.mainbox));
        transhelp.rollInUpLeft(700, 0, logoem);
        transhelp.standUp(1000, 0, loginBTN);
        transhelp.standUp(1000, 0, registerBTN);
        transhelp.rollInUpLeft(700, 0, passForget);

        //BUTTON CLICKS :

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DATA INTEGRITY CHECKS
                if (emailTB.getText().toString().equals("") || passTB.getText().toString().equals("")) {
                    help.ImageInToast(MainActivity.this, "Please fill in all details", R.drawable.oopsimage);
                    WrongInfoShake();
                } else {
                    Toast.makeText(MainActivity.this, "Authenticating...", Toast.LENGTH_LONG).show();
                    SignUpSuccess();

                }
            }
        });

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To Register Activity with fade_in animation
                transhelp.rubberBand(800, 0, logoem);
                Intent i = new Intent(MainActivity.this, Register.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, logoem, "reveal");
                ActivityCompat.startActivity(MainActivity.this, i, options.toBundle());
            }
        });


        passForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Provide Functionality
                Toast.makeText(MainActivity.this, "Forgot Password Clicked", Toast.LENGTH_LONG).show();
            }
        });

        logoem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transhelp.tadaHi(700, 0, logoem);
            }
        });

    }
    //---- ONCREATE END -----

    @Override
    protected void onStart() {
        super.onStart();
        if (!help.isConnected(MainActivity.this))
            help.buildNetworkDialog(MainActivity.this).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!help.isConnected(MainActivity.this))
            help.buildNetworkDialog(MainActivity.this).show();
    }

    //A simple back dialog as is used in MainActivity
    @Override
    public void onBackPressed() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage("This app requires sign in to work, Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }


    //For starting Logo Animation
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        logoanimation.start();
    }

    //DUMMY SIGN UP
    public void SignUpSuccess() {
        if (emailTB.getText().toString().equals("sankalpchauhan.me@gmail.com") && passTB.getText().toString().equals("12345")) {
            Intent i = new Intent(MainActivity.this, MainApp.class);
            startActivity(i);
        } else {
            help.StandardToast(this, "Invalid Credentials! ");
            WrongInfoShake();
        }
    }

    //SHAKE ANIMATION IN CASE OF DATA INTEGRITY FAILIURE
    public void WrongInfoShake() {
        transhelp.shakeAnimation(400, 1, emailTB);
        transhelp.shakeAnimation(400, 1, passTB);
    }


}
