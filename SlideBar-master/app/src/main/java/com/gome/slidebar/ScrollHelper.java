package com.gome.slidebar;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

/**
 * Created by weijiaqi on 2018/3/3.
 */

public class ScrollHelper implements ComputeScrollRecycleView.OnDirectionScrollListener{

    private ComputeScrollRecycleView mComputeScrollRecycleView;
    private LinearLayoutManager mLinearLayoutManager;
    private LetterFlagView mLetterFlagView;
    private int mLastFirstVisibleItemPosition = -1;
    private View mLastFirstVisibleView;

    public ScrollHelper(ComputeScrollRecycleView computeScrollRecycleView,
                        LinearLayoutManager linearLayoutManager, LetterFlagView letterFlagView) {
        computeScrollRecycleView.addOnDirectionScrollListener(this);
        mComputeScrollRecycleView = computeScrollRecycleView;
        mLinearLayoutManager = linearLayoutManager;
        mLetterFlagView = letterFlagView;
    }

    @Override
    public void onScrollDown() {
//        Log.e("lion","onScrollDown");
        if (null == mLinearLayoutManager) {
            return;
        }

        int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int nextVisibleItemPosition = firstVisibleItemPosition + 1;
        int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
        View firstVisibleView = mLinearLayoutManager.findViewByPosition(firstVisibleItemPosition);
        if (firstVisibleView == null) {
            return;
        }

        if (mLastFirstVisibleItemPosition != firstVisibleItemPosition) {
            mLastFirstVisibleView = mLinearLayoutManager.findViewByPosition(mLastFirstVisibleItemPosition);
            mLastFirstVisibleItemPosition = firstVisibleItemPosition;
            if (mLetterFlagView.findLetterItem(LetterFlagView.VIEW_TYPE.cartoon) == null) {
                mLetterFlagView.addLetterFlagItem(LetterFlagView.VIEW_TYPE.cartoon);
                mLetterFlagView.upwardView();
            }
        }

        if (mLastFirstVisibleView == null) {
            return;
        }

        float firstVisibleItemScrollY = firstVisibleView.getHeight() + firstVisibleView.getTop();
//        Log.e("lion", firstVisibleView.getTag() + " | " + (firstVisibleView.getTop() + firstVisibleView.getHeight()));
        if (firstVisibleItemScrollY  <= mLetterFlagView.getLetterItemHeight()) {

            mLetterFlagView.animateCartoonItem(mLetterFlagView.getLetterItemHeight()-firstVisibleItemScrollY,
                    (String) mLastFirstVisibleView.getTag(),true);
        }else {
            mLetterFlagView.endCartoonItemAnimate();
        }


    }

    @Override
    public void onScrollUp() {
//        Log.e("lion","onScrollUp");

        if (null == mLinearLayoutManager) {
            return;
        }

        int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int nextVisibleItemPosition = firstVisibleItemPosition + 1;
        int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
        View firstVisibleView = mLinearLayoutManager.findViewByPosition(firstVisibleItemPosition);
        View nextVisibleItemView = mLinearLayoutManager.findViewByPosition(nextVisibleItemPosition);
        if (firstVisibleView == null || nextVisibleItemView == null
                || nextVisibleItemPosition > lastVisibleItemPosition)  {
            return;
        }



        if (mLastFirstVisibleItemPosition != firstVisibleItemPosition) {
            mLastFirstVisibleItemPosition = firstVisibleItemPosition;
            mLetterFlagView.endCartoonItemAnimate();
        }

        float firstVisibleItemScrollY = firstVisibleView.getHeight() + firstVisibleView.getTop();
        if (firstVisibleItemScrollY  <= mLetterFlagView.getLetterItemHeight()) {
            if (mLetterFlagView.findLetterItem(LetterFlagView.VIEW_TYPE.cartoon) == null) {
                mLetterFlagView.addLetterFlagItem(LetterFlagView.VIEW_TYPE.cartoon);
            }
//            Log.e("lion","firstVisibleItemScrollY = " + firstVisibleItemScrollY +
//                    " mLetterFlagView.getLetterItemHeight() = " + mLetterFlagView.getLetterItemHeight());
            mLetterFlagView.animateCartoonItem(mLetterFlagView.getLetterItemHeight()-firstVisibleItemScrollY
                    ,(String)nextVisibleItemView.getTag(), false);
        }




//        Log.e("lion", "tag = "+ firstVisibleView.getTag() + " | " + firstVisibleView.getTop() + " | " +firstVisibleView.getHeight());

    }
}
