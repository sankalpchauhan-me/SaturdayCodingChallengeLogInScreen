package me.sankalpchauhan.challengelogin.helpers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import me.sankalpchauhan.challengelogin.R;
import me.sankalpchauhan.challengelogin.listners.OnRevealAnimationListener;

public class transitionHelper extends AppCompatActivity {


    public void StartAnimation(View rootview){
        rootview.setVisibility(View.INVISIBLE);
        ViewTreeObserver viewTreeObserver = rootview.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    revealActivity(rootview.getWidth()/2, rootview.getHeight()/2, rootview);
                    rootview.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }



    protected void revealActivity(int x, int y, View rootview) {
        float finalRadius = (float) (Math.max(rootview.getWidth(), rootview.getHeight()) * 1.1);

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootview, x, y, 0, finalRadius);
        circularReveal.setDuration(1000);
        circularReveal.setInterpolator(new AccelerateInterpolator());

        // make the view visible and start the animation
        rootview.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    public static void animateRevealHide(final Context ctx, final View view, @ColorRes final int color,
                                         final int finalRadius, final OnRevealAnimationListener listener) {
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;
        int initialRadius = view.getWidth();

        Animator anim =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, finalRadius);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setBackgroundColor(ctx.getResources().getColor(color));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
                listener.onRevealHide();
            }
        });
        anim.setDuration(ctx.getResources().getInteger(R.integer.animation_duration));
        anim.start();
    }

}
