package com.example.farsicards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnimationTestActivity extends AppCompatActivity {
    ValueAnimator animation = ValueAnimator.ofFloat(.5f,0f);
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);
        animation.setDuration(500);
        button = findViewById(R.id.animated_button);

        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                // You can use the animated value in a property that uses the
                // same type as the animation. In this case, you can use the
                // float value in the translationX property.
                float animatedValue = (float)updatedAnimation.getAnimatedValue();
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) button.getLayoutParams();
                params.verticalBias = animatedValue;
                params.horizontalBias = animatedValue;
                button.setLayoutParams(params);
            }
        });


    }




    public void AnimateClick(View view){
        animation.start();
    }
}