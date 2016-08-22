package com.nejc.mamiapp.fragments_and_adapters;/**
 * @author Nejc
 * <p/>
 * Description:
 */


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/***********
 * REVISION HISTORY *****************
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * /
 ***********************************************/
public class CustomImageView extends ImageView {
    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

}
