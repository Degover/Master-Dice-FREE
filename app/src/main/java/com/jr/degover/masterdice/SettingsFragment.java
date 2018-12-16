package com.jr.degover.masterdice;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends PreferenceFragment {
    //Declara Variaveis
    //Declara o SharedPreference
    SharedPreferences prefs;
    SharedPreferences.Editor prefEditor;
    //Declara os CheckBoxes
    CheckBoxPreference box1;
    CheckBoxPreference box2;
    CheckBoxPreference box3;
    //Declara os switches
    SwitchPreference colorModeSwitch;
    //Outros
    boolean colorMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_settings);

        //Inicia Variaveis
        //Inicia as preferencias
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefEditor = prefs.edit();
        //Inicia os CheckBoxes
        box1 = (CheckBoxPreference)findPreference("Box1");
        box2 = (CheckBoxPreference)findPreference("Box2");
        box3 = (CheckBoxPreference)findPreference("Box3");
        //Inicia os Switches
        colorModeSwitch = (SwitchPreference)findPreference("colorMode");

        //Coloca os estados iniciais
        colorMode = prefs.getBoolean("colorMode", false);
        colorModeSwitch.setChecked(colorMode);

        //Muda as checkboxes
        switch (prefs.getInt("d2Mode", 1)){
            case 1:
                box1.setWidgetLayoutResource(R.layout.checkbox_true);
                box1.setChecked(true);
                box2.setWidgetLayoutResource(R.layout.checkbox_false);
                box2.setChecked(false);
                box3.setWidgetLayoutResource(R.layout.checkbox_false);
                box3.setChecked(false);
                break;
            case 2:
                box1.setWidgetLayoutResource(R.layout.checkbox_false);
                box1.setChecked(false);
                box2.setWidgetLayoutResource(R.layout.checkbox_true);
                box2.setChecked(true);
                box3.setWidgetLayoutResource(R.layout.checkbox_false);
                box3.setChecked(false);
                break;
            case 3:
                box1.setWidgetLayoutResource(R.layout.checkbox_false);
                box1.setChecked(false);
                box2.setWidgetLayoutResource(R.layout.checkbox_false);
                box2.setChecked(false);
                box3.setWidgetLayoutResource(R.layout.checkbox_true);
                box3.setChecked(true);
                break;
        } //end switch

        //Coloca os clickListeners
        box1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if(box1.isChecked()){
                    //Coloca a preferencia no SharedPreferences
                    prefEditor.putInt("d2Mode", 1);
                    prefEditor.apply();

                    //Muda o estado dos outros CheckBoxes
                    box1.setWidgetLayoutResource(R.layout.checkbox_true);
                    box1.setChecked(true);
                    box2.setWidgetLayoutResource(R.layout.checkbox_false);
                    box2.setChecked(false);
                    box3.setWidgetLayoutResource(R.layout.checkbox_false);
                    box3.setChecked(false);
                } //end if
                return false;
            } //end onPreferenceClick
        });

        box2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if(box2.isChecked()){
                    //Coloca a preferencia no SharedPreferences
                    prefEditor.putInt("d2Mode", 2);
                    prefEditor.apply();

                    //Muda o estado dos outros CheckBoxes
                    box1.setWidgetLayoutResource(R.layout.checkbox_false);
                    box1.setChecked(false);
                    box2.setWidgetLayoutResource(R.layout.checkbox_true);
                    box2.setChecked(true);
                    box3.setWidgetLayoutResource(R.layout.checkbox_false);
                    box3.setChecked(false);
                } //end if
                return false;
            } //end onPreferenceClick
        });

        box3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if(box3.isChecked()){
                    //Coloca a preferencia no SharedPreferences
                    prefEditor.putInt("d2Mode", 3);
                    prefEditor.apply();

                    //Muda o estado dos outros CheckBoxes
                    box1.setWidgetLayoutResource(R.layout.checkbox_false);
                    box1.setChecked(false);
                    box2.setWidgetLayoutResource(R.layout.checkbox_false);
                    box2.setChecked(false);
                    box3.setWidgetLayoutResource(R.layout.checkbox_true);
                    box3.setChecked(true);
                } //end if
                return false;
            } //end onPreferenceClick
        });

        colorModeSwitch.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //Muda a preferencia no SharedPreferences
                colorMode = !colorMode;
                prefEditor.putBoolean("colorMode", colorMode);
                return false;
            }
        });

    } //end onCreate




}
