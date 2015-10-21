package com.zohaltech.app.ieltsvocabulary.activities;

import android.app.SearchManager;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zohaltech.app.ieltsvocabulary.R;
import com.zohaltech.app.ieltsvocabulary.classes.WebApiClient;
import com.zohaltech.app.ieltsvocabulary.fragments.DrawerFragment;
import com.zohaltech.app.ieltsvocabulary.fragments.SearchFragment;
import com.zohaltech.app.ieltsvocabulary.fragments.ThemesFragment;

import widgets.MySnackbar;


public class MainActivity extends PaymentActivity {

    long startTime;
    private DrawerLayout   drawerLayout;
    private DrawerFragment drawerFragment;
    private Fragment       fragment;

    @Override
    protected void onCreated() {
        super.onCreated();
        setContentView(R.layout.activity_main);
        startTime = System.currentTimeMillis() - 5000;
        //        SystemSetting setting=SystemSettings.getCurrentSettings();
        //        SystemSettings.register(setting);
        //
        //        if(setting.getPremium().equals(Helper.hashString(Helper.getDeviceId()))){
        //            String s;
        //        }

        //if (App.preferences.getBoolean("Encoded", false))
        // EncryptVocabs();
        WebApiClient.sendUserData(WebApiClient.PostAction.INSTALL, null);
    }

    @Override
    protected void onToolbarCreated() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(drawerLayout, toolbar);
        drawerFragment.setMenuVisibility(true);
        displayView(0);
    }

    @Override
    protected void updateUiToPremiumVersion() {
        if (drawerFragment != null) {
            drawerFragment.updateUi();
        }
        destroyPaymentDialog();
        displayView(0);
    }

    @Override
    protected void updateUiToTrialVersion() {
        //nothing
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        MenuItemCompat.setOnActionExpandListener(searchMenuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                displayView(0);
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                return true;  // Return true to expand action view
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (fragment != null && fragment instanceof SearchFragment) {
                    ((SearchFragment) fragment).search(newText);
                }
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(1);
            }
        });

        return true;
    }

    private void displayView(int position) {
        fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new ThemesFragment();
                title = getString(R.string.title_themes);
                break;
            case 1:
                fragment = new SearchFragment();
                title = getString(R.string.title_search);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment, title);
            //fragmentTransaction.addToBackStack(title);
            fragmentTransaction.commit();

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(title);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if ((System.currentTimeMillis() - startTime) > 2000) {
            startTime = System.currentTimeMillis();
            MySnackbar.show(drawerLayout, getString(R.string.press_back_again_to_exit), Snackbar.LENGTH_SHORT);
        } else {
            super.onBackPressed();
        }
    }

    //    private void EncryptVocabs() {
    //        ArrayList<Vocabulary> vocabularies = Vocabularies.select();
    //        ArrayList<Example> sentences = Examples.select();
    //
    //        for (Vocabulary vocabulary : vocabularies) {
    //            try {
    //                vocabulary.setEncVocab(CoreSec.encrypt(vocabulary.getVocabulary()));
    //                vocabulary.setEncPersianDef(CoreSec.encrypt(vocabulary.getVocabPersianDef()));
    //                vocabulary.setEncEngDef(CoreSec.encrypt(vocabulary.getVocabEnglishDef()));
    //
    //                Vocabularies.update(vocabulary);
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        //        for (Vocabulary vocabulary : vocabularies) {
    //        //            try {
    //        //
    //        //                String voc = decrypt(vocabulary.getEncVocab1());
    //        //                String p = decrypt(vocabulary.getEncPersianDef1());
    //        //
    //        //                //                        String voc = java.net.URLDecoder.decode(new String(xsamCrypt.decrypt(vocabulary.getEncVocab1())),"UTF-8");
    //        //                //                        String p =  java.net.URLDecoder.decode(new String(xsamCrypt.decrypt(vocabulary.getEncPersianDef1())),"UTF-8");
    //        //                //                vocabulary.setEncVocab(XsamCrypt.bytesToHex(xsamCrypt.encrypt(vocabulary.getVocabulary())));
    //        //                //                vocabulary.setEncPersianDef(XsamCrypt.bytesToHex(xsamCrypt.encrypt(vocabulary.getVocabPersianDef())));
    //        //                //                vocabulary.setEncEngDef(XsamCrypt.bytesToHex(xsamCrypt.encrypt(vocabulary.getVocabEnglishDef())));
    //        //
    //        //                Vocabularies.update(vocabulary);
    //        //
    //        //            } catch (Exception e) {
    //        //                e.printStackTrace();
    //        //            }
    //        //        }
    //        for (Example example : sentences) {
    //            try {
    //                example.setEncEnglish(CoreSec.encrypt(example.getEnglish()));
    //                example.setEncPersian(CoreSec.encrypt(example.getPersian()));
    //
    //                Examples.update(example);
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        App.preferences.edit().putBoolean("Encoded", true).apply();
    //    }
}