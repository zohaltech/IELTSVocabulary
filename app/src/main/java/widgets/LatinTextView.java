package widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


public class LatinTextView extends AppCompatTextView {

    public LatinTextView(Context context) {
        super(context);
        initialize();
    }

    public LatinTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public LatinTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    private void initialize() {
        setLineSpacing(1f, 1.2f);
    }

    //@Override
    //public void setTypeface(Typeface tf) {
    //    super.setTypeface(App.englishFont);
    //}
    //
    //@Override
    //public void setTypeface(Typeface tf, int style) {
    //    if (style == Typeface.BOLD) {
    //        super.setTypeface(App.englishFontBold);
    //    } else {
    //        super.setTypeface(App.englishFont);
    //    }
    //}
}
