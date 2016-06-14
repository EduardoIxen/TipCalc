package edu.eduardo.android.tipcalc;

import android.app.Application;

/**
 * Created by DELL on 11/06/2016.
 */
public class TipCalcApp extends Application {
    private final static String ABOUT_URL="https://about.me/adriancatalan";

    public String getAboutUrl(){
        return ABOUT_URL;
    }
}
