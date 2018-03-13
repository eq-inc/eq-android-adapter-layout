package jp.eq_inc.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterLayout extends RecyclerView {
    private HashMap<Integer, Integer> mLayoutResIdToViewTypeMap = new HashMap<>();
    private List<AdapterItem> mItemList = new ArrayList<>();
    private Class mViewHolderClass = SimpleViewHolder.class;

    public AdapterLayout(Context context) {
        super(context);
        init(null, 0);
    }

    public AdapterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public AdapterLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        if (attrs != null) {
            // Load attributes
            final TypedArray a = getContext().obtainStyledAttributes(
                    attrs, jp.eq_inc.custom_view.R.styleable.AdapterLayout, defStyle, 0);

            try {
                if (a.hasValue(jp.eq_inc.custom_view.R.styleable.AdapterLayout_view_holder)) {
                    try {
                        mViewHolderClass = Class.forName(a.getString(jp.eq_inc.custom_view.R.styleable.AdapterLayout_view_holder));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                a.recycle();
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if ((adapter == null) || adapter instanceof SimpleAdapter) {
            super.setAdapter(adapter);
        } else {
            throw new UnsupportedOperationException("this method is not supported, instead setDataArray method");
        }
    }

    public void setDataArray(AdapterItemData[] dataArray) {
        if (dataArray == null) {
            setAdapter(null);
        } else {
            setAdapter(new SimpleAdapter(dataArray));
        }
    }

    public void notifyDataSetChanged() {
        RecyclerView.Adapter adapter = getAdapter();

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void notifyItemChanged(int position) {
        RecyclerView.Adapter adapter = getAdapter();

        if (adapter != null) {
            adapter.notifyItemChanged(position);
        }
    }

    public void notifyItemChanged(int position, Object payload) {
        RecyclerView.Adapter adapter = getAdapter();

        if (adapter != null) {
            adapter.notifyItemChanged(position, payload);
        }
    }

    public void notifyItemInserted(int position) {
        RecyclerView.Adapter adapter = getAdapter();

        if (adapter != null) {
            adapter.notifyItemInserted(position);
        }
    }

    public void notifyItemMoved(int fromPosition, int toPosition) {
        RecyclerView.Adapter adapter = getAdapter();

        if (adapter != null) {
            adapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        RecyclerView.Adapter adapter = getAdapter();

        if (adapter != null) {
            adapter.notifyItemRangeChanged(positionStart, itemCount);
        }
    }

    public void notifyItemRangeChanged(int positionStart, int itemCount, Object payload) {
        RecyclerView.Adapter adapter = getAdapter();

        if (adapter != null) {
            adapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }
    }

    public void notifyItemRangeInserted(int positionStart, int itemCount) {
        RecyclerView.Adapter adapter = getAdapter();

        if (adapter != null) {
            adapter.notifyItemRangeInserted(positionStart, itemCount);
        }
    }

    public void notifyItemRangeRemoved(int positionStart, int itemCount) {
        RecyclerView.Adapter adapter = getAdapter();

        if (adapter != null) {
            adapter.notifyItemRangeRemoved(positionStart, itemCount);
        }
    }

    public void notifyItemRemoved(int position) {
        RecyclerView.Adapter adapter = getAdapter();

        if (adapter != null) {
            adapter.notifyItemRemoved(position);
        }
    }

    private void addItem(AdapterItem item){
        synchronized (mLayoutResIdToViewTypeMap){
            if(!mLayoutResIdToViewTypeMap.containsKey(item.getItemLayoutResId())){
                mLayoutResIdToViewTypeMap.put(item.getItemLayoutResId(), mLayoutResIdToViewTypeMap.size());
            }
        }

        mItemList.add(item);
    }

    private void removeItem(AdapterItem item){
        // mLayoutResIdToViewTypeMapにはそのまま残し、mItemElementListからのみ削除
        mItemList.remove(item);
    }

    @Override
    public void addView(View child) {
        if (child instanceof AdapterItem) {
            addItem((AdapterItem)child);
        } else {
            super.addView(child);
        }
    }

    @Override
    public void addView(View child, int index) {
        if (child instanceof AdapterItem) {
            addItem((AdapterItem)child);
        } else {
            super.addView(child, index);
        }
    }

    @Override
    public void addView(View child, int width, int height) {
        if (child instanceof AdapterItem) {
            addItem((AdapterItem)child);
        } else {
            super.addView(child, width, height);
        }
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (child instanceof AdapterItem) {
            addItem((AdapterItem)child);
        } else {
            super.addView(child, params);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof AdapterItem) {
            addItem((AdapterItem)child);
        } else {
            super.addView(child, index, params);
        }
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof AdapterItem) {
            addItem((AdapterItem)child);
            return true;
        } else {
            return super.addViewInLayout(child, index, params);
        }
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params, boolean preventRequestLayout) {
        if (child instanceof AdapterItem) {
            addItem((AdapterItem)child);
            return true;
        } else {
            return super.addViewInLayout(child, index, params, preventRequestLayout);
        }
    }

    @Override
    public void removeView(View view) {
        if (view instanceof AdapterItem) {
            removeItem((AdapterItem)view);
        } else {
            super.removeView(view);
        }
    }

    @Override
    public void removeViewInLayout(View view) {
        if (view instanceof AdapterItem) {
            removeItem((AdapterItem)view);
        } else {
            super.removeViewInLayout(view);
        }
    }

    private class SimpleAdapter extends RecyclerView.Adapter {
        private AdapterItemData[] mDataArray;

        public SimpleAdapter(AdapterItemData[] dataArray) {
            if (dataArray == null) {
                throw new IllegalArgumentException("dataArray == null");
            }
            mDataArray = dataArray;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            int resId = 0;
            for(Map.Entry<Integer, Integer> entry : mLayoutResIdToViewTypeMap.entrySet()){
                if(entry.getValue() == viewType){
                    resId = entry.getKey();
                    break;
                }
            }

            View itemView = inflater.inflate(resId, parent, false);
            ViewHolder viewHolder = null;

            try {
                Constructor<? extends ViewHolder> constructor = mViewHolderClass.getDeclaredConstructor(View.class);
                viewHolder = constructor.newInstance(itemView);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            AdapterItemData targetData = mDataArray[position];

            View view = holder.itemView;
            AdapterItem adapterItem = null;
            for(AdapterItem tempAdapterItem : mItemList){
                if(tempAdapterItem.getItemLayoutResId() == targetData.getLayoutResId()){
                    adapterItem = tempAdapterItem;
                }
            }

            for (AdapterItemElement itemElement : adapterItem.getElementList()) {
                View targetView = view.findViewById(itemElement.getTargetViewResId());
                try {
                    Object getObject = null;
                    AccessibleObject setter;

                    switch (itemElement.getGetterType()) {
                        case Method: {
                            Method method = getDeclaredMethodFromAll(targetData.getClass(), itemElement.getGetter());
                            method.setAccessible(true);
                            getObject = method.invoke(targetData);
                        }
                        break;
                        case Field: {
                            Field field = getDeclaredFieldFromAll(targetData.getClass(), itemElement.getGetter());
                            field.setAccessible(true);
                            getObject = field.get(targetData);
                        }
                        break;
                    }

                    switch (itemElement.getSetterType()) {
                        case Method:
                            setter = getDeclaredMethodFromAll(targetView.getClass(), itemElement.getSetter(), itemElement.getSetterValueType());
                            setter.setAccessible(true);
                            ((Method) setter).invoke(targetView, getObject);
                            break;
                        case Field:
                            setter = getDeclaredFieldFromAll(targetView.getClass(), itemElement.getSetter());
                            setter.setAccessible(true);
                            ((Field)setter).set(targetView, getObject);
                            break;
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public int getItemCount() {
            return mDataArray.length;
        }

        @Override
        public int getItemViewType(int position) {
            return mLayoutResIdToViewTypeMap.get(mDataArray[position].getLayoutResId());
        }
    }

    private static Method getDeclaredMethodFromAll(Class targetClass, String methodName, Class... params) throws NoSuchMethodException {
        Method ret = null;

        try {
            ret = targetClass.getDeclaredMethod(methodName, params);
        } catch (NoSuchMethodException e) {
            Class superClass = targetClass.getSuperclass();
            if(superClass != null){
                ret = getDeclaredMethodFromAll(superClass, methodName, params);
            }

            if(ret == null){
                throw e;
            }
        }

        return ret;
    }

    private static Field getDeclaredFieldFromAll(Class targetClass, String fieldName) throws NoSuchFieldException {
        Field ret = null;

        try {
            ret = targetClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClass = targetClass.getSuperclass();
            if(superClass != null) {
                ret = getDeclaredFieldFromAll(superClass, fieldName);
            }

            if(ret == null){
                throw e;
            }
        }

        return ret;
    }

    private static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface AdapterItemData {
        int getLayoutResId();
    }
}
