package com.here.timedtaxi.splash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;

/**
 * Created by Mickael on 21/03/2018.
 */

public class SplashAnimationsUtils {

    private static final int SPLASH_ANIMATION_ITEM_DELAY = 300;
    private static final int SPLASH_ANIMATION_ITEM_DURATION = 800;

    public static Completable startSplashAnimation(final View logoFirst, final View logoSecond)
    {
        return Completable.create(emitter -> {
            ObjectAnimator firstTextAnimator = getFadeInAnimator(logoFirst);

            ObjectAnimator secondTextAnimator = getFadeInAnimator(logoSecond);
            secondTextAnimator.setStartDelay(SPLASH_ANIMATION_ITEM_DELAY);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(
                    firstTextAnimator,
                    secondTextAnimator
            );

            animatorSet.setDuration(SPLASH_ANIMATION_ITEM_DURATION);

            animatorSet.setStartDelay(SPLASH_ANIMATION_ITEM_DELAY);

            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    emitter.onComplete();
                }
            });

            animatorSet.start();
        }).delay(SPLASH_ANIMATION_ITEM_DURATION, TimeUnit.MILLISECONDS);
    }

    private static ObjectAnimator getFadeInAnimator(View v){
        return ObjectAnimator.ofFloat(
                v,
                "alpha",
                0f,
                1f
        );
    }

}
