package com.ailsa.example.rain;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ailsa
 * <p>
 * 2019/3/7 0007
 * <p>
 * RainView，下雨效果的具体实现 [ 垂直 ]
 */
public class FallsDownRainView extends BaseRainView {
    /**
     * 雨点集合
     */
    private List<RainDrop> rainDrops;
    /**
     * 雨点数量、颜色、是否随机生成颜色
     */
    private int rainDropCount;
    private int rainDropColor;
    private boolean isRandColor;
    /**
     * 雨点在X、Y方向的偏移量
     */
    private int offsetY;

    public FallsDownRainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FallsDownRainView);
        rainDropCount = typedArray.getInteger(R.styleable.FallsDownRainView_count, 30);
        rainDropColor = typedArray.getInteger(R.styleable.FallsDownRainView_color, context.getColor(R.color.colorRainDrop));
        isRandColor = typedArray.getBoolean(R.styleable.FallsDownRainView_randColor, false);
        offsetY = typedArray.getInteger(R.styleable.FallsDownRainView_offsetY, 20);
        typedArray.recycle();
        rainDrops = new ArrayList<>();
    }

    @Override
    protected void initRainDrops() {
        for (int i = 0; i < rainDropCount; i++) {
            rainDrops.add(new RainDrop(getHeight(), getWidth(), offsetY,
                    rainDropColor, isRandColor));
        }
    }

    @Override
    protected void drawRainDrops(Canvas canvas) {
        for (RainDrop rainDrop : rainDrops) {
            rainDrop.drawSingleRainDrop(canvas);
        }
    }

    @Override
    protected void moveRainDrops() {
        for (RainDrop rainDrop : rainDrops) {
            rainDrop.moveSingleRainDrop();
        }
    }

}
