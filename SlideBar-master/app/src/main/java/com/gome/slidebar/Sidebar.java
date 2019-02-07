package com.gome.slidebar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/2/27.
 */

public class Sidebar extends FrameLayout {

    private final static int DEFAULT_TEXT_SIZE = 14; // dp
    private final static int DEFAULT_BAR_WIDTH = 32; // dp
    private final static int DEFAULT_BAR_HEIGHT = 450; //dp
    private final static String[] DEFAULT_INDEX_ITEMS = SideBarUtil.SECTION_CH;

    private DisplayMetrics mDisplayMetrics;
    private int mTextColor;
    private float mTextSize;
    private float mBarWidth;
    private float mBarHeight;
    private Bitmap mBarBackground;

    private RectF mStartTouchingArea = new RectF();
    private float mFirstItemBaseLineY;
    private float mIndexItemHeight;
    private String[] mIndexItems;

    private Paint mPaint;

    private boolean mStartTouching = false;

    private int mCurrentIndex = -1;
    private int mLastSelectedIndex = -1;
    private float mCurrentY = -1;

    private TextView mTextView;
    private ViewPropertyAnimator mViewPropertyAnimator;

    private Map<String,List<String>> mMap;

    private OnSelectIndexItemListener onSelectIndexItemListener;

    public Sidebar(@NonNull Context context) {
        this(context, null);
    }

    public Sidebar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public Sidebar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        mDisplayMetrics = context.getResources().getDisplayMetrics();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SideBar);
        mTextColor = typedArray.getColor(R.styleable.SideBar_bar_text_color, Color.GRAY);
        mTextSize = typedArray.getDimension(R.styleable.SideBar_bar_text_size, dp2px(DEFAULT_TEXT_SIZE));
        mBarWidth = typedArray.getDimension(R.styleable.SideBar_bar_width, dp2px(DEFAULT_BAR_WIDTH));
        mBarHeight = typedArray.getDimension(R.styleable.SideBar_bar_height, dp2px(DEFAULT_BAR_HEIGHT));
        int BarBackgroundID = typedArray.getResourceId(R.styleable.SideBar_bar_background, -1);
        mBarBackground = drawableToBitmap(context.getDrawable(BarBackgroundID));
        typedArray.recycle();
        initPaint();
        mIndexItems = DEFAULT_INDEX_ITEMS;
    }

    public void setMap(Map<String, List<String>> map) {
        mMap = map;
    }

    public void updateCurrentIndexByScroll(String letter) {
        if (mMap == null || letter == null) {
            return;
        }
        int index = getPositionInCollection(letter);
        if (index >= 0) {
            mCurrentIndex = index;
            postInvalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildAt(0) instanceof TextView) {
            mTextView = (TextView) getChildAt(0);
            mTextView.setAlpha(0);
            mTextView.setScaleX(0f);
            mTextView.setScaleY(0f);

            mViewPropertyAnimator = mTextView.animate();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawBitmap(mBarBackground,
                mStartTouchingArea.centerX() - mBarBackground.getWidth() / 2,
                mStartTouchingArea.centerY() - mBarBackground.getHeight() / 2, mPaint);

        if (null != mMap && mMap.size() > 0 && mCurrentIndex >= 0) {
            boolean isContainsKey = mMap.containsKey(mIndexItems[mCurrentIndex]);
            if (isContainsKey) {
                mLastSelectedIndex = mCurrentIndex;
            }
        }

        for (int i = 0, mIndexItemsLength = mIndexItems.length; i < mIndexItemsLength; i++) {
            float baseLineY = mFirstItemBaseLineY + mIndexItemHeight * i;
            float baseLineX = mStartTouchingArea.centerX();
            mPaint.setColor((i == mLastSelectedIndex) ? Color.parseColor("#FF2E76FC") : mTextColor);

            canvas.drawText(mIndexItems[i], baseLineX, baseLineY, mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        mIndexItemHeight = (fontMetrics.bottom - fontMetrics.top) * 1.05f;
        mBarHeight = mIndexItems.length * mIndexItemHeight;

        // calculate the width of the longest text as the width of side bar
        for (String indexItem : mIndexItems) {
            mBarWidth = Math.max(mBarWidth, mPaint.measureText(indexItem));
        }

        float areaLeft = width - mBarWidth;
        float areaRight = width;
        float areaTop = height / 2 - mBarHeight / 2;
        float areaBottom = areaTop + mBarHeight;
        mStartTouchingArea.set(areaLeft, areaTop, areaRight, areaBottom);

        // the baseline Y of the first item' text to draw
        mFirstItemBaseLineY = (height / 2 - mBarHeight / 2)
                + (mIndexItemHeight / 2 - (fontMetrics.descent - fontMetrics.ascent) / 2)
                - fontMetrics.ascent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mIndexItems.length == 0) {
            return super.onTouchEvent(event);
        }

        float eventY = event.getY();
        float eventX = event.getX();
        mCurrentIndex = getSelectedIndex(eventY);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mStartTouchingArea.contains(eventX, eventY)) {
                    runTextViewAnimation(1f, 1f, 150, 0, true);
                    mStartTouching = true;
                    onItemSelected();
                    return true;
                } else {
                    mCurrentIndex = -1;
                    return false;
                }

            case MotionEvent.ACTION_MOVE:
                onItemSelected();
                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                runTextViewAnimation(0f, 0f, 300, 300, false);
                mStartTouching = false;
                onItemSelected();
                mCurrentIndex = -1;
                return true;
        }

        return super.onTouchEvent(event);
    }

    private void onItemSelected() {
        if (mStartTouching && null != mTextView)
            mTextView.setText(mIndexItems[mCurrentIndex]);

        if (onSelectIndexItemListener != null) {
            onSelectIndexItemListener.onSelectIndexItem(mIndexItems[mCurrentIndex]);
        }

        invalidate();
    }

    private int getSelectedIndex(float eventY) {
        mCurrentY = eventY - (getHeight() / 2 - mBarHeight / 2);
        if (mCurrentY <= 0) {
            return 0;
        }

        int index = (int) (mCurrentY / this.mIndexItemHeight);
        if (index >= this.mIndexItems.length) {
            index = this.mIndexItems.length - 1;
        }
        return index;
    }

    private float dp2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, this.mDisplayMetrics);
    }

    private float sp2px(int sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, this.mDisplayMetrics);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth() <= 0 ? (int) mBarWidth : drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight() <= 0 ? (int) mBarHeight : drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    private void runTextViewAnimation(float endScaleX, float endScaleY, int duration, int delayTime,
                                      final boolean isStart) {
        if (null == mViewPropertyAnimator || null == mTextView) {
            return;
        }

        mViewPropertyAnimator.setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = isStart ? (Float) animation.getAnimatedValue() : 1f - (Float) animation.getAnimatedValue();
                mTextView.setAlpha(alpha);
            }
        });
        mViewPropertyAnimator.scaleX(endScaleX).scaleY(endScaleY).setDuration(duration).setStartDelay(delayTime);
    }

    private int getPositionInCollection(String letter) {
        if (mIndexItems == null || mIndexItems.length < 0) {
            return -1;
        }
        for (int i = 0; i < mIndexItems.length; i++) {
            if (mIndexItems[i].equals(letter)) {
                return i;
            }
        }
        return -1;
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    public static class SideBarUtil {
        private static final String[] SECTION_CH = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

        private static String[] sCurrentSection = SECTION_CH;

        public static boolean isContains(String key) {
            for (String s : sCurrentSection) {
                if (TextUtils.equals(s, key)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void setOnSelectIndexItemListener(OnSelectIndexItemListener onSelectIndexItemListener) {
        this.onSelectIndexItemListener = onSelectIndexItemListener;
    }

    public interface OnSelectIndexItemListener {
        void onSelectIndexItem(String index);
    }
}
