package com.zohaltech.app.ieltsvocabulary.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zohaltech.app.ieltsvocabulary.BuildConfig;
import com.zohaltech.app.ieltsvocabulary.R;
import com.zohaltech.app.ieltsvocabulary.classes.App;
import com.zohaltech.app.ieltsvocabulary.classes.Helper;

import widgets.MySnackbar;
import widgets.MyToast;


public class AboutFragment extends Fragment {
    TextView     txtVersion;
    Button       btnShare;
    Button       btnProducts;
    Button       btnFeedback;
    Button       btnRate;
    LinearLayout layoutWebsite;

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        txtVersion = (TextView) view.findViewById(R.id.txtVersion);
        btnShare = (Button) view.findViewById(R.id.btnShare);
        btnProducts = (Button) view.findViewById(R.id.btnProducts);
        btnFeedback = (Button) view.findViewById(R.id.btnFeedback);
        btnRate = (Button) view.findViewById(R.id.btnRate);
        layoutWebsite = (LinearLayout) view.findViewById(R.id.layoutWebsite);

        txtVersion.setText("Version " + BuildConfig.VERSION_NAME);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String message = String.format(getResources().getString(R.string.sharing_message),
                                               getResources().getString(R.string.app_name),
                                               App.marketWebsiteUri);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(intent, getResources().getString(R.string.share_title)));
            }
        });

        btnProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(App.marketDeveloperUri));
                if (!myStartActivity(intent)) {
                    MySnackbar.show(layoutWebsite, getString(R.string.could_not_open_market), Snackbar.LENGTH_SHORT);
                }
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "info@zohaltech.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.feedback_subject));
                startActivity(Intent.createChooser(intent, getResources().getString(R.string.feedback_title)));
            }
        });

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.marketPollIntent);
                intent.setData(Uri.parse(App.marketPollUri));
                intent.setPackage(App.marketPackage);
                if (!myStartActivity(intent)) {
                    intent.setData(Uri.parse(App.marketWebsiteUri));
                    if (!myStartActivity(intent)) {
                        MyToast.show(String.format(getResources().getString(R.string.could_not_open_market), App.marketName, App.marketName), Toast.LENGTH_SHORT);
                    }
                }
            }
        });

        layoutWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.goToWebsite("http://zohaltech.com");
            }
        });


        return view;
    }

    private boolean myStartActivity(Intent intent) {
        try {
            startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

