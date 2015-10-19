package widgets;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;


public class MySwitch extends SwitchCompat {
    public MySwitch(Context context) {
        super(context);
    }

    public MySwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySwitch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //@Override
    //public void setTypeface(Typeface tf) {
    //    super.setTypeface(App.persianFont);
    //}
    //
    //@Override
    //public void setTypeface(Typeface tf, int style) {
    //    if (style == Typeface.BOLD) {
    //        super.setTypeface(App.persianFontBold);
    //    } else {
    //        super.setTypeface(App.persianFont);
    //    }
    //}
}
