package widgets;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.zohaltech.app.ieltsvocabulary.R;

public class MySnackbar {
    public static void show(View view, String message, int duration) {
        Snackbar snackbar = Snackbar.make(view, message, duration);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundResource(R.color.primary_dark);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(view.getContext().getResources().getColor(R.color.white));
        snackbar.show();
    }
}
