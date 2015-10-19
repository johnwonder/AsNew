package com.ufida.john.mypullscrollview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by jn on 2015/10/19.
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        float headerHeight= typedArray.getDimension(R.styleable.MyTextView_headerHeight, -1);
        int age = typedArray.getInt(R.styleable.MyTextView_age, -1);
        typedArray.recycle();

        this.setText("headerHeight:"+headerHeight +" age:"+age);

    }
}
