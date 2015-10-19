package widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;


public class MyButton extends AppCompatButton {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyle) {
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
