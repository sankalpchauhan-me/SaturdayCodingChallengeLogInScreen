package me.sankalpchauhan.challengelogin;

import android.content.DialogInterface;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    EditText emailTB;
    EditText passTB;
    Button loginBTN;
    Button registerBTN;
    TextView passForget;
    ImageView logoem;

    //Commonly used methods
    helper help;

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

        help = new helper();

        if (!help.isConnected(MainActivity.this))
            help.buildNetworkDialog(MainActivity.this).show();


        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //BASIC INFORMATION INTEGRITY CHECKS
                if (emailTB.getText().toString().equals("") || passTB.getText().toString().equals("")) {
                    help.DisplayDialog(MainActivity.this, "Oops!", "Please fill in all the details", "OK");
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



    public void SignUpSuccess(){
        if (emailTB.getText().toString().equals("sankalpchauhan.me@gmail.com") && passTB.getText().toString().equals("12345")){
            Intent i = new Intent(MainActivity.this, MainApp.class);
            startActivity(i);
        }

        else{
            help.DisplayDialog(this, "Oops!", "Invalid Credentials !", "OK");
        }
    }


}
