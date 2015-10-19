package widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.zohaltech.app.ieltsvocabulary.classes.App;


public class PersianTextView extends AppCompatTextView {

    public PersianTextView(Context context) {
        super(context);
        initialize();
    }

    public PersianTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public PersianTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    private void initialize() {
        setLineSpacing(1f, 1.5f);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(App.persianFont);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        if (style == Typeface.BOLD) {
            super.setTypeface(App.persianFontBold);
        } else {
            super.setTypeface(App.persianFont);
        }
    }
}
