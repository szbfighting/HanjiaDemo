package com.example.slidedeletemenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.example.slidedeletemenu.R;

public class SlidingButtonView extends HorizontalScrollView {

    private TextView delete;
    private int lScrollWidth;
    private boolean first = false;
    public SlidingButtonView(Context context) {
        super(context,null);
    }

    public SlidingButtonView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public SlidingButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //实现第一次进入时获取删除按钮控件
        if (!first) {
            delete = findViewById(R.id.tv_delete);
            first = true;
        }


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //实现默认隐藏删除按钮
        if (changed){
            this.scrollTo(0,0);
            //获取水平滚动条可以滚动的范围，也就是右侧删除按钮的宽度
            lScrollWidth = delete.getWidth();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                changeScrollX();

        }
        return super.onTouchEvent(ev);
    }

    //根据滑动距离是否显示删除按钮
    private void changeScrollX(){
        if (getScrollX()>=lScrollWidth/2){
            this.smoothScrollTo(lScrollWidth,0);
        }else{
            scrollTo(0,0);
        }
    }
}
