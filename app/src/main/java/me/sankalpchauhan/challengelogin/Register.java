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

        transhelp.wobble(1500,0,logoem);
        transhelp.fadeIn(1000,0,findViewById(R.id.secbox));
        transhelp.standUp(1500,0,regMobileBTN);
        transhelp.standUp(1500,0,regEmailBTN);
        transhelp.slideInRight(1000,0,firstnamereg);
        transhelp.slideInLeft(1000,0,lastnamereg);
        transhelp.slideInRight(1000,0,passwordreg);
        transhelp.slideInLeft(1000,0,confirmpasswordreg);

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
                                transhelp.flash(700,0,logoem);
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
                    help.ImageInToast(Register.this,"Please fill in all details", R.drawable.oopsimage);
                    WrongInfoShake();
                } else if (!passwordreg.getText().toString().equals(confirmpasswordreg.getText().toString())) {
                    help.ImageInToast(Register.this,"Passwords do not match", R.drawable.oopsimage);
                    transhelp.shakeAnimation(400,1,passwordreg);
                    transhelp.shakeAnimation(400, 1, confirmpasswordreg);
                } else {
                    // METHODS GOES HERE
                    help.ImageInToast(Register.this,"Registered", R.drawable.thumbsup);
                }
            }
        });


        logoem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transhelp.tadaHi(700,0,logoem);
            }
        });
    }

//Circular Animation On Back Pressed
    @Override
    public void onBackPressed() {
        transhelp.zoomOut(700,0,logoem);
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

    public void WrongInfoShake(){
        transhelp.shakeAnimation(400, 1, firstnamereg);
        transhelp.shakeAnimation(400, 1, lastnamereg);
        transhelp.shakeAnimation(400,1,passwordreg);
        transhelp.shakeAnimation(400, 1, confirmpasswordreg);
        transhelp.shakeAnimation(400, 1, emailreg);
    }


}
