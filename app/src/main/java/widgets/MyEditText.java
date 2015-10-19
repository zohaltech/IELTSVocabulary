package widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;


public class MyEditText extends AppCompatEditText {
    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
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
