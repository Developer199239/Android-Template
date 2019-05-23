package com.jalilurrahman.expandable;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout expandable, headerLayout, activeLayout, dntLayout, invisibleLayout, awayLayout;
    int height;
    ImageView statusIcon, activeDropdown, dntDropdown, invisibleDropdown, awayDropdown;
    TextView statusTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        headerLayout = findViewById(R.id.headerLayout);
        activeLayout = findViewById(R.id.activeLayout);
        dntLayout = findViewById(R.id.dntLayout);
        invisibleLayout = findViewById(R.id.invisibleLayout);
        awayLayout = findViewById(R.id.awayLayout);

        statusIcon = findViewById(R.id.statusIcon);
        statusTitle = findViewById(R.id.statusTitle);

        activeDropdown = findViewById(R.id.activeDropdown);
        dntDropdown = findViewById(R.id.dntDropdown);
        invisibleDropdown = findViewById(R.id.invisibleDropdown);
        awayDropdown = findViewById(R.id.awayDropdown);

        headerLayout.setOnClickListener(this);
        activeLayout.setOnClickListener(this);
        dntLayout.setOnClickListener(this);
        invisibleLayout.setOnClickListener(this);
        awayLayout.setOnClickListener(this);

        updateStatusHeader(UserStatusType.ACTIVE);


        expandable = findViewById(R.id.expandable);

        expandable.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        expandable.getViewTreeObserver().removeOnPreDrawListener(this);
                        expandable.setVisibility(View.GONE);

                        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        expandable.measure(widthSpec, heightSpec);
                        height = expandable.getMeasuredHeight();
                        return true;
                    }
                });
    }


    private void expand(RelativeLayout layout, int layoutHeight) {
        layout.setVisibility(View.VISIBLE);
        ValueAnimator animator = slideAnimator(layout, 0, layoutHeight);
        animator.start();
    }

    private void collapse(final RelativeLayout layout) {
        int finalHeight = layout.getHeight();
        ValueAnimator mAnimator = slideAnimator(layout, finalHeight, 0);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animator) {
                layout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        mAnimator.start();
    }


    private ValueAnimator slideAnimator(final RelativeLayout layout, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();
                layoutParams.height = value;
                layout.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.headerLayout:
                break;
            case R.id.activeLayout:
                updateStatusHeader(UserStatusType.ACTIVE);
                break;
            case R.id.dntLayout:
                updateStatusHeader(UserStatusType.DO_NOT_DISTURB);
                break;
            case R.id.invisibleLayout:
                updateStatusHeader(UserStatusType.INVISIBLE);
                break;
            case R.id.awayLayout:
                updateStatusHeader(UserStatusType.AWAY);
                break;
        }
        if (expandable.getVisibility() == View.GONE) {
            expand(expandable, height);
        } else {
            collapse(expandable);
        }
    }

    void updateStatusHeader(int type) {
        activeDropdown.setVisibility(View.INVISIBLE);
        dntDropdown.setVisibility(View.INVISIBLE);
        invisibleDropdown.setVisibility(View.INVISIBLE);
        awayDropdown.setVisibility(View.INVISIBLE);

        if (type == UserStatusType.ACTIVE) {
            statusIcon.setImageResource(android.R.drawable.presence_online);
            statusTitle.setText("Active");
            activeDropdown.setVisibility(View.VISIBLE);
        } else if (type == UserStatusType.DO_NOT_DISTURB) {
            statusIcon.setImageResource(R.drawable.ic_status_do_not_disturb);
            statusTitle.setText("Do not disturb");
            dntDropdown.setVisibility(View.VISIBLE);
        } else if (type == UserStatusType.INVISIBLE) {
            statusIcon.setImageResource(R.drawable.ic_status_invisible);
            statusTitle.setText("Invisible");
            invisibleDropdown.setVisibility(View.VISIBLE);
        } else if (type == UserStatusType.AWAY) {
            statusIcon.setImageResource(R.drawable.ic_status_away);
            statusTitle.setText("Away");
            awayDropdown.setVisibility(View.VISIBLE);
        }
    }
}
