/*
 * Copyright (c) 2016. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.sdm.l0601_fragments.fragments;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import labs.sdm.l0601_fragments.R;

/**
 * Displays the application Settings to the user through a PreferenceFragment.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    /**
     * Required empty public constructor.
     */
    public SettingsFragment() {
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Creates the View to be shown from a Preference resource
        addPreferencesFromResource(R.xml.preference_settings);
    }
}
