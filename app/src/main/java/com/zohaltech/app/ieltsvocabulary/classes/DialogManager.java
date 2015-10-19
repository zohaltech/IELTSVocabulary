package com.zohaltech.app.ieltsvocabulary.classes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.rey.material.app.TimePickerDialog;
import com.zohaltech.app.ieltsvocabulary.R;

import java.text.SimpleDateFormat;

public final class DialogManager {

    public static String timeResult;

    public static Dialog getPopupDialog(
            Context context
            , final String caption
            , final String message
            , String positiveButtonText
            , String negativeButtonText
            , final Runnable onDialogShown
            , final Runnable onPositiveButtonClick
            , final Runnable onNegativeButtonClick) {
        if (onDialogShown != null) {
            onDialogShown.run();
        }
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_popup);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        TextView txtCaption = (TextView) dialog.findViewById(R.id.txtCaption);
        TextView txtMessage = (TextView) dialog.findViewById(R.id.txtMessage);
        Button positiveButton = (Button) dialog.findViewById(R.id.positiveButton);
        Button negativeButton = (Button) dialog.findViewById(R.id.negativeButton);
        txtCaption.setText(caption);
        txtMessage.setText(message);
        positiveButton.setText(positiveButtonText);
        negativeButton.setText(negativeButtonText);

        positiveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onPositiveButtonClick.run();
                dialog.dismiss();
            }
        });

        negativeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onNegativeButtonClick.run();
                dialog.dismiss();
            }
        });
        return dialog;
    }

    public static void showTimePickerDialog(Activity activity
            , String caption, int hour, int minute, final Runnable onPositiveActionClick) {

        final TimePickerDialog timePickerDialog = new TimePickerDialog(activity);
        timePickerDialog.title(caption);
        timePickerDialog.cancelable(true);
        timePickerDialog.cornerRadius(5);
        timePickerDialog.hour(hour);
        timePickerDialog.minute(minute);
        timePickerDialog.negativeAction("CANCEL");
        timePickerDialog.positiveAction("OK");
        timePickerDialog.positiveActionClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                timeResult = timePickerDialog.getFormattedTime(new SimpleDateFormat("HH:mm"));
                onPositiveActionClick.run();
                timeResult = "";
                timePickerDialog.dismiss();
            }
        });
        timePickerDialog.negativeActionClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.dismiss();
            }
        });
        timePickerDialog.show();
    }
}
