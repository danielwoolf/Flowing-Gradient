package com.dynamitechetan.flowinggradient;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by DynamiteChetan on 16-07-2016.
 */
public class FlowingGradient extends View {

	int duration = 4000;
    int draw;
	RelativeLayout rl;
	LinearLayout ll;
	ImageView im;
	int alphaint;
	AnimationDrawable frameAnimation;

    public FlowingGradient(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.gradient, 0, 0);

		draw = a.getResourceId(R.styleable.gradient_transition_drawable, R.drawable.translate);
		duration = a.getInt(R.styleable.gradient_transition_duration, 75);
        init();
    }

    public FlowingGradient(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.gradient,0, 0);

        draw = a.getResourceId(R.styleable.gradient_transition_drawable, R.drawable.translate);
        duration = a.getInt(R.styleable.gradient_transition_duration,75);
        init();
    }

    public FlowingGradient(Context context) {
        super(context);
		TypedArray a = getContext().obtainStyledAttributes(R.styleable.gradient);
		draw = a.getResourceId(R.styleable.gradient_transition_drawable, R.drawable.translate);
		duration = a.getInt(R.styleable.gradient_transition_duration, 75);
        init();
    }

    private void init() {
        setBackgroundResource(draw);
        final AnimationDrawable frameAnimation = (AnimationDrawable) getBackground();
        frameAnimation.setEnterFadeDuration(duration);
        frameAnimation.setExitFadeDuration(duration);
        post(new Runnable(){
            public void run(){
                frameAnimation.start();
            }
        });
    }




    public FlowingGradient setTransitionDuration(int time) {
		this.duration = time;

        return this;
    }

	public void setLinearLayout(LinearLayout ll) {
        this.ll = ll;
    }

	public void setImageView(ImageView im) {
        this.im = im;
    }

	public void setRelativeLayout(RelativeLayout rl) {
        this.rl = rl;
    }

    public void start() {

        if (ll != null) {
			ll.setBackgroundResource(draw);
        } else if (rl != null) {
			rl.setBackgroundResource(draw);
        } else if (im != null) {
			im.setBackgroundResource(draw);
        }
        if (ll != null) {
            frameAnimation = (AnimationDrawable) ll.getBackground();
        } else if (rl != null) {
            frameAnimation = (AnimationDrawable) rl.getBackground();
        } else if (im != null) {
            frameAnimation = (AnimationDrawable) im.getBackground();
        }
		frameAnimation.setEnterFadeDuration(duration);
		frameAnimation.setExitFadeDuration(duration);
        frameAnimation.start();

    }

	public void setBackgroundResource(int draw) {
		this.draw = draw;

    }

    public void setAlpha(int alphaint) {
        this.alphaint = alphaint;
        frameAnimation.setAlpha(alphaint);
    }

    public void stop() {
        frameAnimation.stop();
    }
}