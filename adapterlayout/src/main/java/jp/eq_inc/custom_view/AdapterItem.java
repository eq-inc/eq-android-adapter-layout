package jp.eq_inc.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AdapterItem extends ViewGroup {
    private int mItemLayoutResId = 0;
    private List<AdapterItemElement> mElementList = new ArrayList<>();

    public AdapterItem(Context context) {
        super(context);
        initialize(null, 0);
    }

    public AdapterItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(attrs, 0);
    }

    public AdapterItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs, defStyleAttr);
    }

    public AdapterItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(attrs, defStyleAttr);
    }

    public int getItemLayoutResId(){
        return mItemLayoutResId;
    }

    public List<AdapterItemElement> getElementList(){
        return mElementList;
    }

    private void addItem(AdapterItemElement item){
        mElementList.add(item);
    }

    private void removeItem(AdapterItemElement item){
        mElementList.remove(item);
    }

    @Override
    public void addView(View child) {
        if (child instanceof AdapterItemElement) {
            addItem((AdapterItemElement)child);
        } else {
            super.addView(child);
        }
    }

    @Override
    public void addView(View child, int index) {
        if (child instanceof AdapterItemElement) {
            addItem((AdapterItemElement)child);
        } else {
            super.addView(child, index);
        }
    }

    @Override
    public void addView(View child, int width, int height) {
        if (child instanceof AdapterItemElement) {
            addItem((AdapterItemElement)child);
        } else {
            super.addView(child, width, height);
        }
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (child instanceof AdapterItemElement) {
            addItem((AdapterItemElement)child);
        } else {
            super.addView(child, params);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof AdapterItemElement) {
            addItem((AdapterItemElement)child);
        } else {
            super.addView(child, index, params);
        }
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof AdapterItemElement) {
            addItem((AdapterItemElement)child);
            return true;
        } else {
            return super.addViewInLayout(child, index, params);
        }
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params, boolean preventRequestLayout) {
        if (child instanceof AdapterItemElement) {
            addItem((AdapterItemElement)child);
            return true;
        } else {
            return super.addViewInLayout(child, index, params, preventRequestLayout);
        }
    }

    @Override
    public void removeView(View view) {
        if (view instanceof AdapterItemElement) {
            removeItem((AdapterItemElement)view);
        } else {
            super.removeView(view);
        }
    }

    @Override
    public void removeViewInLayout(View view) {
        if (view instanceof AdapterItemElement) {
            removeItem((AdapterItemElement)view);
        } else {
            super.removeViewInLayout(view);
        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        // 処理なし
    }

    private void initialize(AttributeSet attrs, int defStyle){
        if(attrs != null){
            // Load attributes
            final TypedArray a = getContext().obtainStyledAttributes(
                    attrs, jp.eq_inc.custom_view.R.styleable.AdapterItem, defStyle, 0);

            try {
                mItemLayoutResId = a.getResourceId(jp.eq_inc.custom_view.R.styleable.AdapterItem_layout_res_id, mItemLayoutResId);
                if (mItemLayoutResId == 0) {
                    // 未設定
                    throw new IllegalArgumentException("need to set layout_res_id attribute");
                }
            }finally {
                a.recycle();
            }
        }
    }
}
