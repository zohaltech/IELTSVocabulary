package widgets;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zohaltech.app.ieltsvocabulary.R;
import com.zohaltech.app.ieltsvocabulary.classes.App;

public class MyToast extends Toast {

    //    public static enum MessageType{
    //        INFORMATION,
    //        WARNING,
    //        ERROR
    //    }
    ImageView image;
    TextView  text;

    private MyToast(Context context) {
        super(context);
        View layout = LayoutInflater.from(App.context).inflate(R.layout.my_toast, null);
        image = (ImageView) layout.findViewById(R.id.image);
        text = (TextView) layout.findViewById(R.id.text);
        setView(layout);
        //setGravity(Gravity.BOTTOM, 0, 0);
    }

    public static void show(String message, int duration) {
        MyToast myToast = new MyToast(App.context);
        myToast.image.setVisibility(View.GONE);
        myToast.text.setText(message);
        myToast.setDuration(duration);
        myToast.show();
    }

    public static void show(Activity activity, String message, int duration) {
        MyToast myToast = new MyToast(activity);
        myToast.image.setVisibility(View.GONE);
        myToast.text.setText(message);
        myToast.setDuration(duration);
        myToast.show();
    }

    public static void show(String message, int duration, int imageResId) {
        MyToast myToast = new MyToast(App.context);
        myToast.image.setVisibility(View.VISIBLE);
        myToast.image.setImageResource(imageResId);
        myToast.text.setText(message);
        myToast.setDuration(duration);
        myToast.show();
    }

    //    public static void show(String message, int duration, MessageType messageType) {
    //        MyToast myToast = new MyToast(G.context);
    //        myToast.image.setVisibility(View.VISIBLE);
    //        switch (messageType){
    //            case INFORMATION:
    //                myToast.image.setImageResource(R.drawable.information);
    //                break;
    //            case WARNING:
    //                myToast.image.setImageResource(R.drawable.warning);
    //                break;
    //            case ERROR:
    //                myToast.image.setImageResource(R.drawable.error);
    //                break;
    //        }
    //        myToast.text.setContent(message);
    //        myToast.setDuration(duration);
    //        myToast.show();
    //    }
}
