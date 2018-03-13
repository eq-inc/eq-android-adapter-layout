package jp.eq_inc.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AdapterItemElement extends View {
    public enum GetterType{
        Method(0),
        Field(1);

        int mValue = 0;

        GetterType(int value){
            mValue = value;
        }

        static GetterType getGetterType(int value){
            GetterType ret = null;

            for(GetterType tempGetterType : GetterType.values()){
                if(tempGetterType.mValue == value){
                    ret = tempGetterType;
                    break;
                }
            }

            return ret;
        }
    }

    public enum SetterType{
        Method(0),
        Field(1);

        int mValue = 0;

        SetterType(int value){
            mValue = value;
        }

        static SetterType getSetterType(int value){
            SetterType ret = null;

            for(SetterType tempGetterType : SetterType.values()){
                if(tempGetterType.mValue == value){
                    ret = tempGetterType;
                    break;
                }
            }

            return ret;
        }
    }

    private int mViewResId = 0;
    private String mGetter = null;
    private GetterType mGetterType = GetterType.Field;
    private String mSetter = "setText";
    private SetterType mSetterType = SetterType.Method;
    private Class mSetterValueType = String.class;

    public AdapterItemElement(Context context) {
        super(context);
        initialize(null, 0);
    }

    public AdapterItemElement(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(attrs, 0);
    }

    public AdapterItemElement(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs, defStyleAttr);
    }

    public AdapterItemElement(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(attrs, defStyleAttr);
    }

    public int getTargetViewResId(){
        return mViewResId;
    }

    public String getGetter(){
        return mGetter;
    }

    public GetterType getGetterType(){
        return mGetterType;
    }

    public String getSetter(){
        return mSetter;
    }

    public SetterType getSetterType(){
        return mSetterType;
    }

    public Class getSetterValueType(){
        return mSetterValueType;
    }

    private void initialize(AttributeSet attrs, int defStyle){
        if(attrs != null){
            // Load attributes
            final TypedArray a = getContext().obtainStyledAttributes(
                    attrs, R.styleable.AdapterItemElement, defStyle, 0);

            try {
                mViewResId = a.getResourceId(R.styleable.AdapterItemElement_view_res_id, mViewResId);
                if (mViewResId == 0) {
                    // 未設定
                    throw new IllegalArgumentException("need to set view_res_id attribute");
                }

                mGetter = a.getString(R.styleable.AdapterItemElement_getter);
                if (mGetter == null) {
                    // 未設定
                    throw new IllegalArgumentException("need to set getter attribute");
                }

                if(a.hasValue(R.styleable.AdapterItemElement_getter_type)){
                    mGetterType = GetterType.getGetterType(a.getInt(R.styleable.AdapterItemElement_getter_type, GetterType.Field.mValue));
                }

                mSetter = a.getString(R.styleable.AdapterItemElement_setter);
                if (mSetter == null) {
                    // 未設定
                    throw new IllegalArgumentException("need to set setter attribute");
                }

                if(a.hasValue(R.styleable.AdapterItemElement_setter_type)){
                    mSetterType = SetterType.getSetterType(a.getInt(R.styleable.AdapterItemElement_setter_type, SetterType.Field.mValue));
                }

                mSetterValueType = getTargetClass(a.getString(R.styleable.AdapterItemElement_setter_value_type));
            }finally {
                a.recycle();
            }
        }
    }

    private static Class getTargetClass(String getterType){
        Class ret = String.class;

        if(getterType == null || getterType.equals("")){
            // 未設定なので、stringとして扱う
        }else{
            Class[] targetClassArray = new Class[]{
                    byte.class,
                    char.class,
                    short.class,
                    int.class,
                    long.class,
                    float.class,
                    double.class,
                    String.class,
                    CharSequence.class,
            };

            for(Class tempRet : targetClassArray){
                if(getterType.equals(tempRet.getName())){
                    ret = tempRet;
                    break;
                }
            }
        }

        return ret;
    }
}
