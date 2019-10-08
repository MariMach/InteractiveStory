package com.example.interactivestory;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LocaleHelper {

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    public static Context onAttach(Context context) {
        String lang = getPersistedData(context, Locale.getDefault().getLanguage());
        return selLocale(context, lang);
    }

    public static Context onAttach(Context context, String defaultLanguage) {
        String lang = getPersistedData(context, defaultLanguage);
        return selLocale(context, lang);
    }

    private static String getPersistedData(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE, language);
    }

    public static Context selLocale(Context context, String lang) {
       persist(context, lang);
       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                return updateResources(context, lang);
       }
       return updateResourcesLegacy(context, lang);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Context updateResources(Context context, String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        config.setLayoutDirection(locale);

        return context.createConfigurationContext(config);
    }

    @SuppressWarnings("deprecation")
    public static Context updateResourcesLegacy(Context context, String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources res = context.getResources();

        Configuration config = res.getConfiguration();
        config.locale = locale;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLayoutDirection(locale);
        }
        res.updateConfiguration(config, res.getDisplayMetrics());
        return context;
    }


    public static void persist(Context context, String lang){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(SELECTED_LANGUAGE, lang);
        editor.apply();
    }
}
