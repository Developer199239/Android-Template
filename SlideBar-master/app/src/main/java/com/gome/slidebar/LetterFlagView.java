package com.gome.slidebar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.gome.slidebar.LetterFlagView.VIEW_TYPE.cartoon;
import static com.gome.slidebar.LetterFlagView.VIEW_TYPE.normal;

/**
 * Created by admin on 2018/3/2.
 */

public class LetterFlagView extends LinearLayout{


    public static enum VIEW_TYPE{cartoon, normal};
    public final static int CARTOON_TEXT_COLOR = Color.parseColor("#000000");
    public final static int CARTOON_ITEM_COLOR = Color.parseColor("#f1f0f5");
    public final static int NORMAL_TEXT_COLOR = Color.parseColor("#51b038");
    public final static int NORMAL_ITEM_COLOR = Color.parseColor("#ffffff");


    private LayoutInflater mLayoutInflater;

    private float mLetterHeight;

    public LetterFlagView(Context context) {
        this(context, null);
    }

    public LetterFlagView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LetterFlagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLetterHeight = context.getResources().getDimension(R.dimen.letter_item_height);
        mLayoutInflater = LayoutInflater.from(context);
        setOrientation(VERTICAL);
        addLetterFlagItem(normal);
    }

    public LinearLayout addLetterFlagItem(VIEW_TYPE viewType) {
        LinearLayout linearLayout = (LinearLayout) mLayoutInflater.inflate(R.layout.letter_flag_item,this,false);
        linearLayout.setBackgroundColor(viewType == cartoon ? CARTOON_ITEM_COLOR : NORMAL_ITEM_COLOR);
        LayoutParams layoutParams = (LayoutParams) linearLayout.getLayoutParams();
        layoutParams.height = (int) mLetterHeight;
        linearLayout.setTag(viewType.ordinal());

        TextView textView = (TextView) linearLayout.findViewById(R.id.letter_flag);
        textView.setTextColor(viewType == cartoon ? CARTOON_TEXT_COLOR : NORMAL_TEXT_COLOR);
        addView(linearLayout,layoutParams);
        return linearLayout;
    }

    public LinearLayout findLetterItem(VIEW_TYPE viewType) {
        LinearLayout linearLayout = (LinearLayout) findViewWithTag(viewType.ordinal());
        return linearLayout;
    }

    public TextView findLetterFlag(VIEW_TYPE viewType) {
        LinearLayout linearLayout = (LinearLayout) findViewWithTag(viewType.ordinal());
        TextView textView = (TextView) linearLayout.findViewById(R.id.letter_flag);
        return textView;
    }

    public float getLetterItemHeight() {
        return mLetterHeight;
    }

    public void animateCartoonItem(float scrollY, String text, boolean isDown) {
        this.setTranslationY(-scrollY);
        LinearLayout linearLayout = findLetterItem(cartoon);
        TextView textView = findLetterFlag(cartoon);
        float per = scrollY / getLetterItemHeight();
        float percent = 1 - per;
        if (isDown) {
            linearLayout.setAlpha(per);
        }
        textView.setTextColor(Utils.calculateColor(NORMAL_TEXT_COLOR, CARTOON_TEXT_COLOR, percent));
        textView.setBackgroundColor(Utils.calculateColor(NORMAL_ITEM_COLOR, CARTOON_ITEM_COLOR, percent));
        textView.setText(text);

    }

    public void upwardView() {
        this.setTranslationY(-mLetterHeight);
    }

    public void endCartoonItemAnimate() {
        LinearLayout linearLayout = findLetterItem(cartoon);
        this.setTranslationY(0);
        if (linearLayout != null) {
            removeView(linearLayout);
        }
    }


}
