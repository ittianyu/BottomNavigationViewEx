package com.ittianyu.bottomnavigationviewex;

import android.content.Context;
import android.graphics.Paint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by yu on 2016/11/10.
 */
public class BottomNavigationViewEx extends BottomNavigationView {
    private int mShiftAmount;
    private float mScaleUpFactor;
    private float mScaleDownFactor;
    private boolean animationRecord;
    private float mLargeLabelSize;
    private float mSmallLabelSize;
    private boolean visibilityTextSizeRecord;
    private boolean visibilityHeightRecord;
    private int mItemHeight;

    public BottomNavigationViewEx(Context context) {
        super(context);
    }

    public BottomNavigationViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomNavigationViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * change the visibility of icon
     *
     * @param visibility
     */
    public void setIconVisibility(boolean visibility) {
        /*
        1. get field in this class
        private final BottomNavigationMenuView mMenuView;

        2. get field in mButtons
        private BottomNavigationItemView[] mButtons;

        3. get mIcon in mButtons
        private ImageView mIcon

        4. set mIcon visibility gone

        5. change mItemHeight to only text size in mMenuView
         */
        // 1. get mMenuView
        final BottomNavigationMenuView mMenuView = getField(getClass().getSuperclass(), this, "mMenuView", BottomNavigationMenuView.class);
        // 2. get mButtons
        BottomNavigationItemView[] mButtons = getField(mMenuView.getClass(), mMenuView, "mButtons", BottomNavigationItemView[].class);
        // 3. get mIcon in mButtons
        for (BottomNavigationItemView button : mButtons) {
            ImageView mIcon = getField(button.getClass(), button, "mIcon", ImageView.class);
            // 4. set mIcon visibility gone
            mIcon.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
        }

        // 5. change mItemHeight to only text size in mMenuView
        if (!visibility) {
            // if not record mItemHeight
            if (!visibilityHeightRecord) {
                visibilityHeightRecord = true;
                mItemHeight = getField(mMenuView.getClass(), mMenuView, "mItemHeight", int.class);
            }

            // change mItemHeight
            BottomNavigationItemView button = mButtons[0];
            if (null != button) {
                final ImageView mIcon = getField(button.getClass(), button, "mIcon", ImageView.class);
//                System.out.println("mIcon.getMeasuredHeight():" + mIcon.getMeasuredHeight());
                if (null != mIcon) {
                    mIcon.post(new Runnable() {
                        @Override
                        public void run() {
//                            System.out.println("mIcon.getMeasuredHeight():" + mIcon.getMeasuredHeight());
                            setField(mMenuView.getClass(), mMenuView, "mItemHeight", mItemHeight - mIcon.getMeasuredHeight());
                        }
                    });
                }
            }
        } else {
            // if not record the mItemHeight, we need do nothing.
            if (!visibilityHeightRecord)
                return;

            // restore it
            setField(mMenuView.getClass(), mMenuView, "mItemHeight", mItemHeight);
        }

        mMenuView.updateMenuView();
    }


    /**
     * change the visibility of text
     *
     * @param visibility
     */
    public void setTextVisibility(boolean visibility) {
        /*
        1. get field in this class
        private final BottomNavigationMenuView mMenuView;

        2. get field in mButtons
        private BottomNavigationItemView[] mButtons;

        3. set text size in mButtons
        private final TextView mLargeLabel
        private final TextView mSmallLabel

        4. change mItemHeight to only icon size in mMenuView
         */
        // 1. get mMenuView
        final BottomNavigationMenuView mMenuView = getField(getClass().getSuperclass(), this, "mMenuView", BottomNavigationMenuView.class);
        // 2. get mButtons
        BottomNavigationItemView[] mButtons = getField(mMenuView.getClass(), mMenuView, "mButtons", BottomNavigationItemView[].class);
        // 3. change field mShiftingMode value in mButtons
        for (BottomNavigationItemView button : mButtons) {
            TextView mLargeLabel = getField(button.getClass(), button, "mLargeLabel", TextView.class);
            TextView mSmallLabel = getField(button.getClass(), button, "mSmallLabel", TextView.class);

            if (!visibility) {
                // if not record the font size, record it
                if (!visibilityTextSizeRecord && !animationRecord) {
                    visibilityTextSizeRecord = true;
                    mLargeLabelSize = mLargeLabel.getTextSize();
                    mSmallLabelSize = mSmallLabel.getTextSize();
                }

                // if not visitable, set font size to 0
                mLargeLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, 0);
                mSmallLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, 0);

            } else {
                // if not record the font size, we need do nothing.
                if (!visibilityTextSizeRecord)
                    break;

                // restore it
                mLargeLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLargeLabelSize);
                mSmallLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mSmallLabelSize);
            }
        }

        // 4 change mItemHeight to only icon size in mMenuView
        if (!visibility) {
            // if not record mItemHeight
            if (!visibilityHeightRecord) {
                visibilityHeightRecord = true;
                mItemHeight = getField(mMenuView.getClass(), mMenuView, "mItemHeight", int.class);
            }

            // change mItemHeight to only icon size in mMenuView
            // private final int mItemHeight;

            // change mItemHeight
//            System.out.println("mLargeLabel.getMeasuredHeight():" + getFontHeight(mSmallLabelSize));
            setField(mMenuView.getClass(), mMenuView, "mItemHeight", mItemHeight - getFontHeight(mSmallLabelSize));

        } else {
            // if not record the mItemHeight, we need do nothing.
            if (!visibilityHeightRecord)
                return;
            // restore mItemHeight
            setField(mMenuView.getClass(), mMenuView, "mItemHeight", mItemHeight);
        }

        mMenuView.updateMenuView();
    }

    private static int getFontHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) + 2;
    }

    /**
     * enable or disable click item animation(text scale and icon move animation in no item shifting mode)
     * @param enable It means the text won't scale and icon won't move when active it in no item shifting mode if false.
     */
    public void enableAnimation(boolean enable) {
        /*
        1. get field in this class
        private final BottomNavigationMenuView mMenuView;

        2. get field in mButtons
        private BottomNavigationItemView[] mButtons;

        3. chang mShiftAmount to 0 in mButtons
        private final int mShiftAmount

        change mScaleUpFactor and mScaleDownFactor to 1f in mButtons
        private final float mScaleUpFactor
        private final float mScaleDownFactor

        4. change label font size in mButtons
        private final TextView mLargeLabel
        private final TextView mSmallLabel
         */

        // 1. get mMenuView
        BottomNavigationMenuView mMenuView = getField(getClass().getSuperclass(), this, "mMenuView", BottomNavigationMenuView.class);
        // 2. get mButtons
        BottomNavigationItemView[] mButtons = getField(mMenuView.getClass(), mMenuView, "mButtons", BottomNavigationItemView[].class);
        // 3. change field mShiftingMode value in mButtons
        for (BottomNavigationItemView button : mButtons) {
            TextView mLargeLabel = getField(button.getClass(), button, "mLargeLabel", TextView.class);
            TextView mSmallLabel = getField(button.getClass(), button, "mSmallLabel", TextView.class);

            // if disable animation, need animationRecord the source value
            if (!enable) {
                if (!animationRecord) {
                    animationRecord = true;
                    mShiftAmount = getField(button.getClass(), button, "mShiftAmount", int.class);
                    mScaleUpFactor = getField(button.getClass(), button, "mScaleUpFactor", float.class);
                    mScaleDownFactor = getField(button.getClass(), button, "mScaleDownFactor", float.class);

                    mLargeLabelSize = mLargeLabel.getTextSize();
                    mSmallLabelSize = mSmallLabel.getTextSize();

//                    System.out.println("mShiftAmount:" + mShiftAmount + " mScaleUpFactor:"
//                            + mScaleUpFactor + " mScaleDownFactor:" + mScaleDownFactor
//                            + " mLargeLabel:" + mLargeLabelSize + " mSmallLabel:" + mSmallLabelSize);
                }
                // disable
                setField(button.getClass(), button, "mShiftAmount", 0);
                setField(button.getClass(), button, "mScaleUpFactor", 1);
                setField(button.getClass(), button, "mScaleDownFactor", 1);

                // let the mLargeLabel font size equal to mSmallLabel
                mLargeLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mSmallLabelSize);

                // debug start
//                mLargeLabelSize = mLargeLabel.getTextSize();
//                System.out.println("mLargeLabel:" + mLargeLabelSize);
                // debug end

            } else {
                // haven't change the value. It means it was the first call this method. So nothing need to do.
                if (!animationRecord)
                    return;
                // enable animation
                setField(button.getClass(), button, "mShiftAmount", mShiftAmount);
                setField(button.getClass(), button, "mScaleUpFactor", mScaleUpFactor);
                setField(button.getClass(), button, "mScaleDownFactor", mScaleDownFactor);
                // restore
                mLargeLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLargeLabelSize);
            }
        }
        mMenuView.updateMenuView();
    }

    /**
     * enable the shifting mode for navigation
     * @param enable It will has a shift animation if true. Otherwise all items are the same width.
     */
    public void enableShiftingMode(boolean enable) {
        /*
        1. get field in this class
        private final BottomNavigationMenuView mMenuView;

        2. change field mShiftingMode value in mMenuView
        private boolean mShiftingMode = true;
         */
        // 1. get mMenuView
        BottomNavigationMenuView mMenuView = getField(getClass().getSuperclass(), this, "mMenuView", BottomNavigationMenuView.class);
        // 2. change field mShiftingMode value in mMenuView
        setField(mMenuView.getClass(), mMenuView, "mShiftingMode", enable);

        mMenuView.updateMenuView();
    }

    /**
     * enable the shifting mode for each item
     * @param enable It will has a shift animation for item if true. Otherwise the item text always be shown.
     */
    public void enableItemShiftingMode(boolean enable) {
        /*
        1. get field in this class
        private final BottomNavigationMenuView mMenuView;

        2. get field in this mMenuView
        private BottomNavigationItemView[] mButtons;

        3. change field mShiftingMode value in mButtons
        private boolean mShiftingMode = true;
         */
        // 1. get mMenuView
        BottomNavigationMenuView mMenuView = getField(getClass().getSuperclass(), this, "mMenuView", BottomNavigationMenuView.class);
        // 2. get mButtons
        BottomNavigationItemView[] mButtons = getField(mMenuView.getClass(), mMenuView, "mButtons", BottomNavigationItemView[].class);
        // 3. change field mShiftingMode value in mButtons
        for (BottomNavigationItemView button : mButtons) {
            setField(button.getClass(), button, "mShiftingMode", enable);
        }
        mMenuView.updateMenuView();
    }

    /**
     * get the current checked item position
     * @return index of item, start from 0.
     */
    public int getCurrentItem() {
        /*
        1. get field in this class
        private final BottomNavigationMenuView mMenuView;

        2. get field in mMenuView
        private BottomNavigationItemView[] mButtons;

        3. get menu and traverse it to get the checked one
         */

        // 1. get mMenuView
        BottomNavigationMenuView mMenuView = getField(getClass().getSuperclass(), this, "mMenuView", BottomNavigationMenuView.class);
        // 2. get mButtons
        BottomNavigationItemView[] mButtons = getField(mMenuView.getClass(), mMenuView, "mButtons", BottomNavigationItemView[].class);
        // 3. get menu and traverse it to get the checked one
        Menu menu = getMenu();
        for (int i = 0; i < mButtons.length; i++) {
            if (menu.getItem(i).isChecked()) {
                return i;
            }
        }
        return 0;
    }

    /**
     * set the current checked item
     *
     * @param item start from 0.
     */
    public void setCurrentItem(int item) {
        // check bounds
        if (item < 0 || item >= getMaxItemCount()) {
            throw new ArrayIndexOutOfBoundsException("item is out of bounds, we expected 0 - "
                    + (getMaxItemCount() - 1) + ". Actually " + item);
        }

        /*
        1. get field in this class
        private final BottomNavigationMenuView mMenuView;

        2. get field in mMenuView
        private BottomNavigationItemView[] mButtons;
        private final OnClickListener mOnClickListener;

        3. call mOnClickListener.onClick();
         */
        // 1. get mMenuView
        BottomNavigationMenuView mMenuView = getField(getClass().getSuperclass(), this, "mMenuView", BottomNavigationMenuView.class);
        // 2. get mButtons
        BottomNavigationItemView[] mButtons = getField(mMenuView.getClass(), mMenuView, "mButtons", BottomNavigationItemView[].class);
        // get mOnClickListener
        View.OnClickListener mOnClickListener = getField(mMenuView.getClass(), mMenuView, "mOnClickListener", View.OnClickListener.class);

//        System.out.println("mMenuView:" + mMenuView + " mButtons:" + mButtons + " mOnClickListener" + mOnClickListener);
        // 3. call mOnClickListener.onClick();
        mOnClickListener.onClick(mButtons[item]);

    }

    /**
     * get private filed in this specific object
     *
     * @param targetClass
     * @param instance    the filed owner
     * @param fieldName
     * @param fieldType   the field type
     * @param <T>
     * @return field if success, null otherwise.
     */
    private <T> T getField(Class targetClass, Object instance, String fieldName, Class<T> fieldType) {
        try {
            Field field = field = targetClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(instance);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * change the field value
     *
     * @param targetClass
     * @param instance      the filed owner
     * @param fieldName
     * @param value
     */
    private void setField(Class targetClass, Object instance, String fieldName, Object value) {
        try {
            Field field = field = targetClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
