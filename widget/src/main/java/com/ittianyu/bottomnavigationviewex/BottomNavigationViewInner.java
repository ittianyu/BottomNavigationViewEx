package com.ittianyu.bottomnavigationviewex;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Typeface;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * Created by yu on 2016/11/10.
 */
@SuppressLint("RestrictedApi")
public class BottomNavigationViewInner extends BottomNavigationView {
    // used for animation
    private float mShiftAmount;
    private float mScaleUpFactor;
    private float mScaleDownFactor;
    private boolean animationRecord;
    private float mLargeLabelSize;
    private float mSmallLabelSize;
    private boolean visibilityTextSizeRecord;
    private boolean visibilityHeightRecord;
    private int mItemHeight;
    private boolean textVisibility = true;
    // used for animation end

    // used for setupWithViewPager
    private ViewPager mViewPager;
    private MyOnNavigationItemSelectedListener mMyOnNavigationItemSelectedListener;
    private BottomNavigationViewExOnPageChangeListener mPageChangeListener;
    private BottomNavigationMenuView mMenuView;
    private BottomNavigationItemView[] mButtons;
    // used for setupWithViewPager end

    // detect navigation tab changes when the user clicking on navigation item
    private static boolean isNavigationItemClicking = false;

    public BottomNavigationViewInner(Context context) {
        this(context, null);
    }

    public BottomNavigationViewInner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationViewInner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TintTypedArray a = ThemeEnforcement.obtainTintedStyledAttributes(context, attrs,
                com.google.android.material.R.styleable.BottomNavigationView,
                defStyleAttr, com.google.android.material.R.style.Widget_Design_BottomNavigationView,
                new int[]{com.google.android.material.R.styleable.BottomNavigationView_itemTextAppearanceInactive,
                        com.google.android.material.R.styleable.BottomNavigationView_itemTextAppearanceActive});
        // clear if you don't have set item icon tint list
        if (!a.hasValue(com.google.android.material.R.styleable.BottomNavigationView_itemIconTint)) {
            clearIconTintColor();
        }
        a.recycle();
    }

    /**
     * change the visibility of icon
     *
     * @param visibility
     */
    public BottomNavigationViewInner setIconVisibility(boolean visibility) {
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
        final BottomNavigationMenuView mMenuView = getBottomNavigationMenuView();
        // 2. get mButtons
        BottomNavigationItemView[] mButtons = getBottomNavigationItemViews();
        // 3. get mIcon in mButtons
        for (BottomNavigationItemView button : mButtons) {
            ImageView mIcon = getField(button.getClass(), button, "icon");
            // 4. set mIcon visibility gone
            mIcon.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
        }

        // 5. change mItemHeight to only text size in mMenuView
        if (!visibility) {
            // if not record mItemHeight
            if (!visibilityHeightRecord) {
                visibilityHeightRecord = true;
                mItemHeight = getItemHeight();
            }

            // change mItemHeight
            BottomNavigationItemView button = mButtons[0];
            if (null != button) {
                final ImageView mIcon = getField(button.getClass(), button, "icon");
//                System.out.println("mIcon.getMeasuredHeight():" + mIcon.getMeasuredHeight());
                if (null != mIcon) {
                    mIcon.post(new Runnable() {
                        @Override
                        public void run() {
//                            System.out.println("mIcon.getMeasuredHeight():" + mIcon.getMeasuredHeight());
                            setItemHeight(mItemHeight - mIcon.getMeasuredHeight());
                        }
                    });
                }
            }
        } else {
            // if not record the mItemHeight, we need do nothing.
            if (!visibilityHeightRecord)
                return this;

            // restore it
            setItemHeight(mItemHeight);
        }

        mMenuView.updateMenuView();
        return this;
    }

    /**
     * change the visibility of text
     *
     * @param visibility
     */
    public BottomNavigationViewInner setTextVisibility(boolean visibility) {
        this.textVisibility = visibility;
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
        BottomNavigationMenuView mMenuView = getBottomNavigationMenuView();
        // 2. get mButtons
        BottomNavigationItemView[] mButtons = getBottomNavigationItemViews();

        // 3. change field mShiftingMode value in mButtons
        for (BottomNavigationItemView button : mButtons) {
            TextView mLargeLabel = getField(button.getClass(), button, "largeLabel");
            TextView mSmallLabel = getField(button.getClass(), button, "smallLabel");

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
                mItemHeight = getItemHeight();
            }

            // change mItemHeight to only icon size in mMenuView
            // private final int mItemHeight;

            // change mItemHeight
//            System.out.println("mLargeLabel.getMeasuredHeight():" + getFontHeight(mSmallLabelSize));
            setItemHeight(mItemHeight - getFontHeight(mSmallLabelSize));

        } else {
            // if not record the mItemHeight, we need do nothing.
            if (!visibilityHeightRecord)
                return this;
            // restore mItemHeight
            setItemHeight(mItemHeight);
        }

        mMenuView.updateMenuView();
        return this;
    }

    /**
     * get text height by font size
     *
     * @param fontSize
     * @return
     */
    private static int getFontHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) + 2;
    }

    /**
     * enable or disable click item animation(text scale and icon move animation in no item shifting mode)
     *
     * @param enable It means the text won't scale and icon won't move when active it in no item shifting mode if false.
     */
    public BottomNavigationViewInner enableAnimation(boolean enable) {
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
        BottomNavigationMenuView mMenuView = getBottomNavigationMenuView();
        // 2. get mButtons
        BottomNavigationItemView[] mButtons = getBottomNavigationItemViews();
        // 3. change field mShiftingMode value in mButtons
        for (BottomNavigationItemView button : mButtons) {
            TextView mLargeLabel = getField(button.getClass(), button, "largeLabel");
            TextView mSmallLabel = getField(button.getClass(), button, "smallLabel");

            // if disable animation, need animationRecord the source value
            if (!enable) {
                if (!animationRecord) {
                    animationRecord = true;
                    mShiftAmount = getField(button.getClass(), button, "shiftAmount");
                    mScaleUpFactor = getField(button.getClass(), button, "scaleUpFactor");
                    mScaleDownFactor = getField(button.getClass(), button, "scaleDownFactor");

                    mLargeLabelSize = mLargeLabel.getTextSize();
                    mSmallLabelSize = mSmallLabel.getTextSize();

//                    System.out.println("mShiftAmount:" + mShiftAmount + " mScaleUpFactor:"
//                            + mScaleUpFactor + " mScaleDownFactor:" + mScaleDownFactor
//                            + " mLargeLabel:" + mLargeLabelSize + " mSmallLabel:" + mSmallLabelSize);
                }
                // disable
                setField(button.getClass(), button, "shiftAmount", 0);
                setField(button.getClass(), button, "scaleUpFactor", 1);
                setField(button.getClass(), button, "scaleDownFactor", 1);

                // let the mLargeLabel font size equal to mSmallLabel
                mLargeLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mSmallLabelSize);

                // debug start
//                mLargeLabelSize = mLargeLabel.getTextSize();
//                System.out.println("mLargeLabel:" + mLargeLabelSize);
                // debug end

            } else {
                // haven't change the value. It means it was the first call this method. So nothing need to do.
                if (!animationRecord)
                    return this;
                // enable animation
                setField(button.getClass(), button, "shiftAmount", mShiftAmount);
                setField(button.getClass(), button, "scaleUpFactor", mScaleUpFactor);
                setField(button.getClass(), button, "scaleDownFactor", mScaleDownFactor);
                // restore
                mLargeLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLargeLabelSize);
            }
        }
        mMenuView.updateMenuView();
        return this;
    }

    /**
     * @Deprecated use {@link #setLabelVisibilityMode }
     * enable the shifting mode for navigation
     *
     * @param enable It will has a shift animation if true. Otherwise all items are the same width.
     */
    @Deprecated
    public BottomNavigationViewInner enableShiftingMode(boolean enable) {
        /*
        1. get field in this class
        private final BottomNavigationMenuView mMenuView;

        2. change field mShiftingMode value in mMenuView
        private boolean mShiftingMode = true;
         */
        // 1. get mMenuView
//        BottomNavigationMenuView mMenuView = getBottomNavigationMenuView();
        // 2. change field mShiftingMode value in mMenuView
//        setField(mMenuView.getClass(), mMenuView, "isShifting", enable);
//        mMenuView.updateMenuView();
        setLabelVisibilityMode(enable ? 0 : 1);
        return this;
    }

    /**
     * @Deprecated use {@link #setItemHorizontalTranslationEnabled(boolean)}
     * enable the shifting mode for each item
     *
     * @param enable It will has a shift animation for item if true. Otherwise the item text always be shown.
     */
    @Deprecated
    public BottomNavigationViewInner enableItemShiftingMode(boolean enable) {
        /*
        1. get field in this class
        private final BottomNavigationMenuView mMenuView;

        2. get field in this mMenuView
        private BottomNavigationItemView[] mButtons;

        3. change field mShiftingMode value in mButtons
        private boolean mShiftingMode = true;
         */
        // 1. get mMenuView
//        BottomNavigationMenuView mMenuView = getBottomNavigationMenuView();
        // 2. get buttons
//        BottomNavigationItemView[] mButtons = getBottomNavigationItemViews();
        // 3. change field mShiftingMode value in mButtons
//        for (BottomNavigationItemView button : mButtons) {
//            button.setShifting(enable);
//        }
//        mMenuView.updateMenuView();

        setItemHorizontalTranslationEnabled(enable);

        return this;
    }

    /**
     * get the current checked item position
     *
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

        // 2. get mButtons
        BottomNavigationItemView[] mButtons = getBottomNavigationItemViews();
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
     * get menu item position in menu
     *
     * @param item
     * @return position if success, -1 otherwise
     */
    public int getMenuItemPosition(MenuItem item) {
        // get item id
        int itemId = item.getItemId();
        // get meunu
        Menu menu = getMenu();
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            if (menu.getItem(i).getItemId() == itemId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * set the current checked item
     *
     * @param index start from 0.
     */
    public BottomNavigationViewInner setCurrentItem(int index) {
        setSelectedItemId(getMenu().getItem(index).getItemId());
        return this;
    }

    /**
     * get OnNavigationItemSelectedListener
     *
     * @return
     */
    public OnNavigationItemSelectedListener getOnNavigationItemSelectedListener() {
        // private OnNavigationItemSelectedListener mListener;
        OnNavigationItemSelectedListener mListener = getField(BottomNavigationView.class, this, "selectedListener");
        return mListener;
    }

    @Override
    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener listener) {
        // if not set up with view pager, the same with father
        if (null == mMyOnNavigationItemSelectedListener) {
            super.setOnNavigationItemSelectedListener(listener);
            return;
        }

        mMyOnNavigationItemSelectedListener.setOnNavigationItemSelectedListener(listener);
    }

    /**
     * get private mMenuView
     *
     * @return
     */
    public BottomNavigationMenuView getBottomNavigationMenuView() {
        if (null == mMenuView)
            mMenuView = getField(BottomNavigationView.class, this, "menuView");
        return mMenuView;
    }

    /**
     * The lib has a default icon tint color. You can call this method to clear it if no need.
     * It usually used when you set two image for item.
     * @return
     */
    public BottomNavigationViewInner clearIconTintColor() {
        getBottomNavigationMenuView().setIconTintList(null);
        return this;
    }
    
    /**
     * get private mButtons in mMenuView
     *
     * @return
     */
    public BottomNavigationItemView[] getBottomNavigationItemViews() {
        if (null != mButtons)
            return mButtons;
        /*
         * 1 private final BottomNavigationMenuView mMenuView;
         * 2 private BottomNavigationItemView[] mButtons;
         */
        BottomNavigationMenuView mMenuView = getBottomNavigationMenuView();
        mButtons = getField(mMenuView.getClass(), mMenuView, "buttons");
        return mButtons;
    }

    /**
     * get private mButton in mMenuView at position
     *
     * @param position
     * @return
     */
    public BottomNavigationItemView getBottomNavigationItemView(int position) {
        return getBottomNavigationItemViews()[position];
    }

    /**
     * get icon at position
     *
     * @param position
     * @return
     */
    public ImageView getIconAt(int position) {
        /*
         * 1 private final BottomNavigationMenuView mMenuView;
         * 2 private BottomNavigationItemView[] mButtons;
         * 3 private ImageView mIcon;
         */
        BottomNavigationItemView mButtons = getBottomNavigationItemView(position);
        ImageView mIcon = getField(BottomNavigationItemView.class, mButtons, "icon");
        return mIcon;
    }

    /**
     * get small label at position
     * Each item has tow label, one is large, another is small.
     *
     * @param position
     * @return
     */
    public TextView getSmallLabelAt(int position) {
        /*
         * 1 private final BottomNavigationMenuView mMenuView;
         * 2 private BottomNavigationItemView[] mButtons;
         * 3 private final TextView mSmallLabel;
         */
        BottomNavigationItemView mButtons = getBottomNavigationItemView(position);
        TextView mSmallLabel = getField(BottomNavigationItemView.class, mButtons, "smallLabel");
        return mSmallLabel;
    }

    /**
     * get large label at position
     * Each item has tow label, one is large, another is small.
     *
     * @param position
     * @return
     */
    public TextView getLargeLabelAt(int position) {
        /*
         * 1 private final BottomNavigationMenuView mMenuView;
         * 2 private BottomNavigationItemView[] mButtons;
         * 3 private final TextView mLargeLabel;
         */
        BottomNavigationItemView mButtons = getBottomNavigationItemView(position);
        TextView mLargeLabel = getField(BottomNavigationItemView.class, mButtons, "largeLabel");
        return mLargeLabel;
    }

    /**
     * return item count
     *
     * @return
     */
    public int getItemCount() {
        BottomNavigationItemView[] bottomNavigationItemViews = getBottomNavigationItemViews();
        if (null == bottomNavigationItemViews)
            return 0;
        return bottomNavigationItemViews.length;
    }

    /**
     * set all item small TextView size
     * Each item has tow label, one is large, another is small.
     * Small one will be shown when item state is normal
     * Large one will be shown when item checked.
     *
     * @param sp
     */
    public BottomNavigationViewInner setSmallTextSize(float sp) {
        int count = getItemCount();
        for (int i = 0; i < count; i++) {
            getSmallLabelAt(i).setTextSize(sp);
        }
        mMenuView.updateMenuView();
        return this;
    }

    /**
     * set all item large TextView size
     * Each item has tow label, one is large, another is small.
     * Small one will be shown when item state is normal.
     * Large one will be shown when item checked.
     *
     * @param sp
     */
    public BottomNavigationViewInner setLargeTextSize(float sp) {
        int count = getItemCount();
        for (int i = 0; i < count; i++) {
            TextView tvLarge = getLargeLabelAt(i);
            if (null != tvLarge)
                tvLarge.setTextSize(sp);
        }
        mMenuView.updateMenuView();
        return this;
    }

    /**
     * set all item large and small TextView size
     * Each item has tow label, one is large, another is small.
     * Small one will be shown when item state is normal
     * Large one will be shown when item checked.
     *
     * @param sp
     */
    public BottomNavigationViewInner setTextSize(float sp) {
        setLargeTextSize(sp);
        setSmallTextSize(sp);
        return this;
    }

    /**
     * set item ImageView size which at position
     *
     * @param position position start from 0
     * @param width    in dp
     * @param height   in dp
     */
    public BottomNavigationViewInner setIconSizeAt(int position, float width, float height) {
        ImageView icon = getIconAt(position);
        // update size
        ViewGroup.LayoutParams layoutParams = icon.getLayoutParams();
        layoutParams.width = dp2px(getContext(), width);
        layoutParams.height = dp2px(getContext(), height);
        icon.setLayoutParams(layoutParams);

        mMenuView.updateMenuView();
        return this;
    }

    /**
     * set all item ImageView size
     *
     * @param width  in dp
     * @param height in dp
     */
    public BottomNavigationViewInner setIconSize(float width, float height) {
        int count = getItemCount();
        for (int i = 0; i < count; i++) {
            setIconSizeAt(i, width, height);
        }
        return this;
    }

    /**
     * set all item ImageView size
     *
     * @param dpSize  in dp
     */
    public BottomNavigationViewInner setIconSize(float dpSize) {
        setItemIconSize(dp2px(getContext(),dpSize));
        return this;
    }

    /**
     * set menu item height
     *
     * @param height in px
     */
    public BottomNavigationViewInner setItemHeight(int height) {
        // 1. get mMenuView
        final BottomNavigationMenuView mMenuView = getBottomNavigationMenuView();
        // 2. set private final int mItemHeight in mMenuView
        setField(mMenuView.getClass(), mMenuView, "itemHeight", height);

        mMenuView.updateMenuView();
        return this;
    }

    /**
     * get menu item height
     *
     * @return in px
     */
    public int getItemHeight() {
        // 1. get mMenuView
        final BottomNavigationMenuView mMenuView = getBottomNavigationMenuView();
        // 2. get private final int mItemHeight in mMenuView
        return getField(mMenuView.getClass(), mMenuView, "itemHeight");
    }

    /**
     * dp to px
     *
     * @param context
     * @param dpValue dp
     * @return px
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * set Typeface for all item TextView
     *
     * @attr ref android.R.styleable#TextView_typeface
     * @attr ref android.R.styleable#TextView_textStyle
     */
    public BottomNavigationViewInner setTypeface(Typeface typeface, int style) {
        int count = getItemCount();
        for (int i = 0; i < count; i++) {
            getLargeLabelAt(i).setTypeface(typeface, style);
            getSmallLabelAt(i).setTypeface(typeface, style);
        }
        mMenuView.updateMenuView();
        return this;
    }

    /**
     * set Typeface for all item TextView
     *
     * @attr ref android.R.styleable#TextView_typeface
     */
    public BottomNavigationViewInner setTypeface(Typeface typeface) {
        int count = getItemCount();
        for (int i = 0; i < count; i++) {
            getLargeLabelAt(i).setTypeface(typeface);
            getSmallLabelAt(i).setTypeface(typeface);
        }
        mMenuView.updateMenuView();
        return this;
    }

    /**
     * get private filed in this specific object
     *
     * @param targetClass
     * @param instance    the filed owner
     * @param fieldName
     * @param <T>
     * @return field if success, null otherwise.
     */
    private <T> T getField(Class targetClass, Object instance, String fieldName) {
        try {
            Field field = targetClass.getDeclaredField(fieldName);
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
     * @param instance    the filed owner
     * @param fieldName
     * @param value
     */
    private void setField(Class targetClass, Object instance, String fieldName, Object value) {
        try {
            Field field = targetClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will link the given ViewPager and this BottomNavigationViewInner together so that
     * changes in one are automatically reflected in the other. This includes scroll state changes
     * and clicks.
     *
     * @param viewPager
     */
    public BottomNavigationViewInner setupWithViewPager(final ViewPager viewPager) {
        return setupWithViewPager(viewPager, false);
    }

    /**
     * This method will link the given ViewPager and this BottomNavigationViewInner together so that
     * changes in one are automatically reflected in the other. This includes scroll state changes
     * and clicks.
     *
     * @param viewPager
     * @param smoothScroll whether ViewPager changed with smooth scroll animation
     */
    public BottomNavigationViewInner setupWithViewPager(final ViewPager viewPager, boolean smoothScroll) {
        if (mViewPager != null) {
            // If we've already been setup with a ViewPager, remove us from it
            if (mPageChangeListener != null) {
                mViewPager.removeOnPageChangeListener(mPageChangeListener);
            }
        }

        if (null == viewPager) {
            mViewPager = null;
            super.setOnNavigationItemSelectedListener(null);
            return this;
        }

        mViewPager = viewPager;

        // Add our custom OnPageChangeListener to the ViewPager
        if (mPageChangeListener == null) {
            mPageChangeListener = new BottomNavigationViewExOnPageChangeListener(this);
        }
        viewPager.addOnPageChangeListener(mPageChangeListener);

        // Now we'll add a navigation item selected listener to set ViewPager's current item
        OnNavigationItemSelectedListener listener = getOnNavigationItemSelectedListener();
        mMyOnNavigationItemSelectedListener = new MyOnNavigationItemSelectedListener(viewPager, this, smoothScroll, listener);
        super.setOnNavigationItemSelectedListener(mMyOnNavigationItemSelectedListener);
        return this;
    }

    /**
     * A {@link ViewPager.OnPageChangeListener} class which contains the
     * necessary calls back to the provided {@link BottomNavigationViewInner} so that the tab position is
     * kept in sync.
     * <p>
     * <p>This class stores the provided BottomNavigationViewInner weakly, meaning that you can use
     * {@link ViewPager#addOnPageChangeListener(ViewPager.OnPageChangeListener)
     * addOnPageChangeListener(OnPageChangeListener)} without removing the listener and
     * not cause a leak.
     */
    private static class BottomNavigationViewExOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private final WeakReference<BottomNavigationViewInner> mBnveRef;

        public BottomNavigationViewExOnPageChangeListener(BottomNavigationViewInner bnve) {
            mBnveRef = new WeakReference<>(bnve);
        }

        @Override
        public void onPageScrollStateChanged(final int state) {
        }

        @Override
        public void onPageScrolled(final int position, final float positionOffset,
                                   final int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(final int position) {
            final BottomNavigationViewInner bnve = mBnveRef.get();
            if (null != bnve && !isNavigationItemClicking)
                bnve.setCurrentItem(position);
//            Log.d("onPageSelected", "--------- position " + position + " ------------");
        }
    }

    /**
     * Decorate OnNavigationItemSelectedListener for setupWithViewPager
     */
    private static class MyOnNavigationItemSelectedListener implements OnNavigationItemSelectedListener {
        private OnNavigationItemSelectedListener listener;
        private final WeakReference<ViewPager> viewPagerRef;
        private boolean smoothScroll;
        private SparseIntArray items;// used for change ViewPager selected item
        private int previousPosition = -1;


        MyOnNavigationItemSelectedListener(ViewPager viewPager, BottomNavigationViewInner bnve, boolean smoothScroll, OnNavigationItemSelectedListener listener) {
            this.viewPagerRef = new WeakReference<>(viewPager);
            this.listener = listener;
            this.smoothScroll = smoothScroll;

            // create items
            Menu menu = bnve.getMenu();
            int size = menu.size();
            items = new SparseIntArray(size);
            for (int i = 0; i < size; i++) {
                int itemId = menu.getItem(i).getItemId();
                items.put(itemId, i);
            }
        }

        public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener listener) {
            this.listener = listener;
        }

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            int position = items.get(item.getItemId());
            // only set item when item changed
            if (previousPosition == position) {
                return true;
            }
//            Log.d("onNavigationItemSelecte", "position:"  + position);
            // user listener
            if (null != listener) {
                boolean bool = listener.onNavigationItemSelected(item);
                // if the selected is invalid, no need change the view pager
                if (!bool)
                    return false;
            }

            // change view pager
            ViewPager viewPager = viewPagerRef.get();
            if (null == viewPager)
                return false;

            // use isNavigationItemClicking flag to avoid `ViewPager.OnPageChangeListener` trigger
            isNavigationItemClicking = true;
            viewPager.setCurrentItem(items.get(item.getItemId()), smoothScroll);
            isNavigationItemClicking = false;

            // update previous position
            previousPosition = position;

            return true;
        }

    }

    public BottomNavigationViewInner enableShiftingMode(int position, boolean enable) {
        getBottomNavigationItemView(position).setShifting(enable);
        return this;
    }

    public BottomNavigationViewInner setItemBackground(int position, int background) {
        getBottomNavigationItemView(position).setItemBackground(background);
        return this;
    }

    public BottomNavigationViewInner setIconTintList(int position, ColorStateList tint) {
        getBottomNavigationItemView(position).setIconTintList(tint);
        return this;
    }

    public BottomNavigationViewInner setTextTintList(int position, ColorStateList tint) {
        getBottomNavigationItemView(position).setTextColor(tint);
        return this;
    }

    /**
     * set margin top for all icons
     *
     * @param marginTop in px
     */
    public BottomNavigationViewInner setIconsMarginTop(int marginTop) {
        for (int i = 0; i < getItemCount(); i++) {
            setIconMarginTop(i, marginTop);
        }
        return this;
    }

    /**
     * set margin top for icon
     *
     * @param position
     * @param marginTop in px
     */
    public BottomNavigationViewInner setIconMarginTop(int position, int marginTop) {
        /*
        1. BottomNavigationItemView
        2. private final int mDefaultMargin;
         */
        BottomNavigationItemView itemView = getBottomNavigationItemView(position);
        setField(BottomNavigationItemView.class, itemView, "defaultMargin", marginTop);
        mMenuView.updateMenuView();
        return this;
    }

}
