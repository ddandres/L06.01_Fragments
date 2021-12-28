/*
 * Copyright (c) 2016. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0601_fragments.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import labs.dadm.l0601_fragments.R;
import labs.dadm.l0601_fragments.fragments.CustomDialogFragment;
import labs.dadm.l0601_fragments.fragments.GridImageFragment;
import labs.dadm.l0601_fragments.fragments.ListStringFragment;
import labs.dadm.l0601_fragments.fragments.LogInFragment;
import labs.dadm.l0601_fragments.fragments.SignInFragment;

// Displays different custom Fragments (LogInFragment, SignInFragment, ListStringFragment,
// GridImageFragment) within the same screen.
// There exist two FrameLayout, that could be empty, or display one of the two Fragments:
// LogInFragment and SignInFragment in the first one, and ListStringFragment and
// GridImageFragment in the other.
// Performed transactions are added to a BackStack, so they can be undone by pressing the Back button.
// Each Fragment has its own OptionsMenu, which is added to the available options on the ActionBar.
// In addition, the activity presents options to display Settings through a PreferenceFragment, and
// a DialogFragment to confirm that the user wants to exit the application.
// The DialogFragment communicates to this activity through the OnPositiveButtonClickedListener interface.
// Up navigation from other activities (like the SettingsActivity) requires this activity to define
// android:launchMode="singleTop" in the Manifest.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener listener = v -> updateFragments(v.getId());
        findViewById(R.id.rbLogin).setOnClickListener(listener);
        findViewById(R.id.rbSignIn).setOnClickListener(listener);
        findViewById(R.id.rbClear1).setOnClickListener(listener);
        findViewById(R.id.rbListStrings).setOnClickListener(listener);
        findViewById(R.id.rbGridImages).setOnClickListener(listener);
        findViewById(R.id.rbClear2).setOnClickListener(listener);

        getSupportFragmentManager().setFragmentResultListener(
                "finish_app",
                this,
                (requestKey, result) -> {
                    // Finishes the application upon user's request.
                    MainActivity.this.finish();
                });
    }

    // This method is executed when the activity is created to populate the ActionBar with actions.
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // This method is executed when any action from the ActionBar is selected.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Determine the action to take place according to the Id of the action selected
        final int itemSelected = item.getItemId();
        if (itemSelected == R.id.mExit) {
            // Display a dialog to ask the user whether to exit the application
            (new CustomDialogFragment()).show(getSupportFragmentManager(), null);
        } else if (itemSelected == R.id.mSettings) {
            // Display the application Settings
            // Start the SettingsActivity
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    // Replaces or removes Fragments from the UI according to the user's actions.
    private void updateFragments(int clickedRadioButton) {

        // Hold references to the Fragment to be added/removed into/from the UI
        // and the required Bundle (if any)
        Class<? extends Fragment> fragmentToAdd = null;
        Fragment fragmentToRemove = null;

        Bundle bundle = null;
        // Identifier of the Layout that will hold the new Fragment
        int layout = 0;

        // Determine the action to take according to the clicked RadioButton
        if (clickedRadioButton == R.id.rbLogin) {
            // Replace any Fragment in the first FrameLayout by the LogInFragment

            // Keep a reference to the FragmentContainerView to be used to add the Fragment
            layout = R.id.fcvFragment1;
            // Get a reference to the Fragment class
            fragmentToAdd = LogInFragment.class;
            // Set the information to pass an initial username to the Fragment
            bundle = new Bundle();
            bundle.putString("username", "David");
        } else if (clickedRadioButton == R.id.rbSignIn) {
            // Replace any Fragment in the first FrameLayout by the SignInFragment

            // Keep a reference to the FragmentContainerView to be used to add the Fragment
            layout = R.id.fcvFragment1;
            // Get a reference to the Fragment class
            fragmentToAdd = SignInFragment.class;
        } else if (clickedRadioButton == R.id.rbClear1) {
            // Remove all Fragments from the first FrameLayout

            // Get a reference to the Fragment identified by the Id of its container (LinearLayout)
            fragmentToRemove = getSupportFragmentManager().findFragmentById(R.id.fcvFragment1);
        } else if (clickedRadioButton == R.id.rbListStrings) {
            // Replace any Fragment in the second FrameLayout by the ListStringFragment

            // Keep a reference to the FragmentContainerView to be used to add the Fragment
            layout = R.id.fcvFragment2;
            // Get a reference to the Fragment class
            fragmentToAdd = ListStringFragment.class;
        } else if (clickedRadioButton == R.id.rbGridImages) {
            // Replace any Fragment in the second FrameLayout by the GridImageFragment

            // Keep a reference to the FragmentContainerView to be used to add the Fragment
            layout = R.id.fcvFragment2;
            // Get a reference to the Fragment class
            fragmentToAdd = GridImageFragment.class;
        } else if (clickedRadioButton == R.id.rbClear2) {
            // Remove all Fragments from the second FrameLayout

            // Get a reference to the Fragment identified by the Id of its container (LinearLayout)
            fragmentToRemove = getSupportFragmentManager().findFragmentById(R.id.fcvFragment2);
        }

        // Get a FragmentTransaction to begin some operations with the current FragmentManager
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setReorderingAllowed(true);
        // Remove the required Fragment
        if (fragmentToRemove != null) {
            transaction.remove(fragmentToRemove);
        }
        // Replace the Fragments in the required Layout by the selected one
        if (fragmentToAdd != null) {
            transaction.replace(layout, fragmentToAdd, bundle);
        }
        // Add the transaction to the BackStack, so it can be reversed by pressing the Back button
        transaction.addToBackStack(null);
        // Make changes effective
        transaction.commit();
    }
}