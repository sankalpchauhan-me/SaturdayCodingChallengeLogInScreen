package me.sankalpchauhan.challengelogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import me.sankalpchauhan.challengelogin.helpers.helper;
import me.sankalpchauhan.challengelogin.helpers.transitionHelper;
import me.sankalpchauhan.challengelogin.listners.OnRevealAnimationListener;

public class Register extends AppCompatActivity {
    //Views
    Button regEmailBTN;
    Button regMobileBTN;
    EditText firstnamereg;
    EditText lastnamereg;
    EditText passwordreg;
    EditText confirmpasswordreg;
    EditText emailreg;

    //Commonly used methods
    helper help;

    //ImageView for transition
    ImageView logoem;

    //Transitions
    transitionHelper transhelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regMobileBTN = findViewById(R.id.regMobileBTN);
        firstnamereg = findViewById(R.id.firstname);
        lastnamereg = findViewById(R.id.lastname);
        passwordreg = findViewById(R.id.passEmail);
        confirmpasswordreg = findViewById(R.id.confirmpassemail);
        emailreg = findViewById(R.id.emailIDemail);
        regEmailBTN = findViewById(R.id.regEmailBTN);
        logoem = findViewById(R.id.logoemail);

        transhelp = new transitionHelper();


        transhelp.StartAnimation(findViewById(R.id.rootContainerregister));

        help = new helper();

        /** Move to Mobile Registration Activity with custom arc animation **/
        regMobileBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, RegisterPhone.class);
                findViewById(R.id.childContaineremail).setVisibility(View.INVISIBLE);
                transhelp.animateRevealHide(Register.this, findViewById(R.id.secbox), R.color.colorAccent, logoem.getWidth() / 2,
                        new OnRevealAnimationListener() {
                            @Override
                            public void onRevealHide() {
                                i.putExtra("firstname", firstnamereg.getText().toString());
                                i.putExtra("lastname", lastnamereg.getText().toString());
                                //startActivity(i);
                                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(Register.this, logoem, ViewCompat.getTransitionName(logoem));
                                ActivityCompat.startActivity(Register.this, i, options.toBundle());
                            }

                            @Override
                            public void onRevealShow() {

                            }
                        });


            }
        });

//REGISTER BUTTON
        regEmailBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firstnamereg.getText().toString().equals("") || lastnamereg.getText().toString().equals("") || passwordreg.getText().toString().equals("") || confirmpasswordreg.getText().toString().equals("") || emailreg.getText().toString().equals("")) {
                    help.DisplayDialog(Register.this, "Oops!", "Please fill in all the details", "OK");
                } else if (!passwordreg.getText().toString().equals(confirmpasswordreg.getText().toString())) {
                    help.DisplayDialog(Register.this, "Oops!", "Passwords do not match", "OK");
                } else {
                    // METHODS GOES HERE
                    Toast.makeText(Register.this, "Clicked", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//Circular Animation On Back Pressed
    @Override
    public void onBackPressed() {
        transhelp.animateRevealHide(this, findViewById(R.id.rootContainerregister), R.color.colorAccent, logoem.getWidth() / 2,
                new OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {
                        Register.super.onBackPressed();
                    }

                    @Override
                    public void onRevealShow() {

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.childContaineremail).setVisibility(View.VISIBLE);
        findViewById(R.id.secbox).setVisibility(View.VISIBLE);
    }


}
