package com.aqoong.lib.countertextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by HanWonWoo (AQoong) on 23/04/2019.
 * <p>
 * mail : cooldnjsdn@gmail.com
 * blog : https://devowly.blogspot.com
 **/
public class CounterTextView extends LinearLayout {
    private int bgShape;
    private int bgColor;
    private float bgColorAlpha;
    private boolean barEnable;
    private int textColor;
    private float textSize;

    private String frontText;
    private String midText;
    private String backText;

    private TextView vFront;
    private TextView vMid;
    private TextView vBack;

    public CounterTextView(Context context) {
        super(context);
        try{
            initData();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public CounterTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CounterView);
        try{
            bgShape     = typedArray.getResourceId(R.styleable.CounterView_background_shape, R.drawable.border_transfer);
            bgColor     = typedArray.getColor(R.styleable.CounterView_background_color, ContextCompat.getColor(getContext(), R.color.light_gray));
            bgColorAlpha= typedArray.getFloat(R.styleable.CounterView_background_alpha,  1f);
            barEnable   = typedArray.getBoolean(R.styleable.CounterView_barEnable, true);

            textColor   = typedArray.getColor(R.styleable.CounterView_textColor, ContextCompat.getColor(getContext(), R.color.black));
            textSize    = typedArray.getDimension(R.styleable.CounterView_textSize, 25);

            frontText   = typedArray.getString(R.styleable.CounterView_frontText);
            midText     = typedArray.getString(R.styleable.CounterView_midText);
            backText    = typedArray.getString(R.styleable.CounterView_backText);
        }finally {
            typedArray.recycle();
        }

        try{
            initData();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void initData(){
        if(midText == null){
            midText = "/";
        }

        initLayout();
    }

    private void initLayout(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_counter, this);
        setBgShape(bgShape);

        vFront = findViewById(R.id.cur);
        vMid = findViewById(R.id.bar);
        vBack = findViewById(R.id.max);

        setTextSize(textSize);
        setTextColor(textColor);
        setText(frontText, midText, backText);
        setBarEnable(barEnable);

        setBgColorAlpha(bgColorAlpha);
    }

    public CounterTextView setText(String front, String mid, String back){
        setFrontText(front);
        setMidText(mid);
        setBackText(back);
        return this;
    }

    public CounterTextView setTextSize(float size){
        this.textSize = size / getResources().getDisplayMetrics().scaledDensity;

        vFront.setTextSize(textSize);
        vMid.setTextSize(textSize);
        vBack.setTextSize(textSize);
        return this;
    }

    public CounterTextView setTextColor(int color){
        vFront.setTextColor(color);
        vMid.setTextColor(color);
        vBack.setTextColor(color);
        this.textColor = color;
        return this;
    }

    public CounterTextView setBgShape(int bgShape) {
        this.setBackgroundResource(bgShape);
        this.bgShape = bgShape;
        return this;
    }

    public CounterTextView setBgColor(int bgColor) {
        this.bgColor = bgColor;
        Drawable background = this.getBackground();

        if (background instanceof ShapeDrawable) {
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(bgColor);
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            gradientDrawable.setColor(bgColor);
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(bgColor);
        }

        return this;
    }

    public CounterTextView setBgColorAlpha(float bgColorAlpha) {
        this.bgColorAlpha = bgColorAlpha;

        int alpha = Math.round(Color.alpha(bgColor) * bgColorAlpha);
        return setBgColor(Color.argb(alpha, Color.red(bgColor), Color.green(bgColor), Color.blue(bgColor)));
    }

    public CounterTextView setBarEnable(boolean barEnable) {
        vMid.setVisibility(barEnable ? VISIBLE : INVISIBLE);
        this.barEnable = barEnable;
        return this;
    }

    public CounterTextView setFrontText(String frontText) {
        vFront.setText(frontText);
        this.frontText = frontText;
        return this;
    }

    public CounterTextView setMidText(String midText) {
        vMid.setText(midText);
        this.midText = midText;
        return this;
    }

    public CounterTextView setBackText(String backText) {
        vBack.setText(backText);
        this.backText = backText;
        return this;
    }
}
