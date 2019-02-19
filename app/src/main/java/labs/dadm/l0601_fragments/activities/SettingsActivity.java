/*
 * Copyright (c) 2016. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0601_fragments.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import labs.dadm.l0601_fragments.fragments.SettingsFragment;

/*
 * Displays the application Settings using a PreferenceFragment.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get a FragmentTransaction to begin some operations with the current FragmentManager
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace all elements in the given Layout by the SettingsFragment
        transaction.replace(android.R.id.content, new SettingsFragment());
        // Make changes effective
        transaction.commit();
    }

}
