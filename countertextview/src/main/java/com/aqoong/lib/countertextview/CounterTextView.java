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

    private String curText;
    private String barText;
    private String maxText;

    private TextView tvCur;
    private TextView tvBar;
    private TextView tvMax;

    public CounterTextView(Context context) {
        super(context);
        try{
            initLayout();
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
            textSize    = typedArray.getDimensionPixelSize(R.styleable.CounterView_textSize, 15);

            curText     = typedArray.getString(R.styleable.CounterView_curText);
            barText     = typedArray.getString(R.styleable.CounterView_barText);
            maxText     = typedArray.getString(R.styleable.CounterView_maxText);
        }finally {
            if(barText == null){
                barText = "/";
            }

            typedArray.recycle();
        }

        try{
            initLayout();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initLayout() throws Exception{
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_counter, this);

        tvCur = findViewById(R.id.cur);
        tvBar = findViewById(R.id.bar);
        tvMax = findViewById(R.id.max);

        setupLayout();
    }

    private void setupLayout(){
        setTextSize(textSize);
        setTextColor(textColor);
        setText(curText, barText, maxText);
        setBarEnable(barEnable);

        setBgShape(bgShape);
        setBgColor(bgColor);
        setBgColorAlpha(bgColorAlpha);
    }

    public CounterTextView setText(String current, String bar, String max){
        tvCur.setText(current);
        tvBar.setText(bar);
        tvMax.setText(max);
        this.curText    = current;
        this.barText    = bar;
        this.maxText    = max;
        return this;
    }

    public CounterTextView setTextSize(float size){
        tvCur.setTextSize(size);
        tvBar.setTextSize(size);
        tvMax.setTextSize(size);
        this.textSize = size;
        return this;
    }

    public CounterTextView setTextColor(int color){
        tvCur.setTextColor(color);
        tvBar.setTextColor(color);
        tvMax.setTextColor(color);
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
        tvBar.setVisibility(barEnable ? VISIBLE : INVISIBLE);
        this.barEnable = barEnable;
        return this;
    }

    public CounterTextView setCurText(String curText) {
        tvCur.setText(curText);
        this.curText = curText;
        return this;
    }

    public CounterTextView setBarText(String barText) {
        tvBar.setText(barText);
        this.barText = barText;
        return this;
    }

    public CounterTextView setMaxText(String maxText) {
        tvMax.setText(maxText);
        this.maxText = maxText;
        return this;
    }
}
