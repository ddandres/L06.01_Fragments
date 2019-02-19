/*
 * Copyright (c) 2016. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0601_fragments.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import labs.dadm.l0601_fragments.R;
import labs.dadm.l0601_fragments.fragments.CustomDialogFragment;
import labs.dadm.l0601_fragments.fragments.GridImageFragment;
import labs.dadm.l0601_fragments.fragments.ListStringFragment;
import labs.dadm.l0601_fragments.fragments.LogInFragment;
import labs.dadm.l0601_fragments.fragments.SignInFragment;

/**
 * Displays different custom Fragments (LogInFragment, SignInFragment, ListStringFragment,
 * GridImageFragment) within the same screen.
 * There exist two FrameLayout, that could be empty, or display one of the two Fragments:
 * LogInFragment and SignInFragment in the first one, and ListStringFragment and
 * GridImageFragment in the other.
 * Performed transactions are added to a BackStack, so they can be undone by pressing the Back button.
 * Each Fragment has its own OptionsMenu, which is added to the available options on the ActionBar.
 * In addition, the activity presents options to display Settings through a PreferenceFragment, and
 * a DialogFragment to confirm that the user wants to exit the application.
 * The DialogFragment communicates to this activity through the PositiveButtonClickedListener interface.
 * Up navigation from other activities (like the SettingsActivity) requires this activity to define
 * android:launchMode="singleTop" in the Manifest.
 */
public class MainActivity extends AppCompatActivity implements CustomDialogFragment.PositiveButtonClickedListener {

    // Tags identifying the different fragments
    private static final String LOGIN = "login";
    private static final String SIGNIN = "signin";
    private static final String LIST = "list";
    private static final String GRID = "grid";
    private static final String DIALOG = "dialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is executed when the activity is created to populate the ActionBar with actions.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * This method is executed when any action from the ActionBar is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Determine the action to take place according to the Id of the action selected
        switch (item.getItemId()) {
            // Display a dialog to ask the user whether to exit the application
            case R.id.mExit:
                // Create and show the CustomDialogFragment
                (new CustomDialogFragment()).show(getSupportFragmentManager(), DIALOG);
                return true;

            // Display the application Settings
            case R.id.mSettings:
                // Start the SettingsActivity
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Implementation of the PositiveButtonClickedListener interface.
     * Finishes the application upon user's request.
     */
    @Override
    public void onPositiveButtonClicked() {
        finish();
    }

    /**
     * Replaces or removes Fragments from the UI according to the user's actions.
     */
    public void updateFragments(View view) {

        // Hold references to the Fragment to be added/removed into/from the UI
        Fragment fragmentToAdd = null;
        Fragment fragmentToRemove = null;
        // Tag to identify the Fragment to be added to the UI
        String tag = null;
        // Identifier of the Layout that will hold the new Fragment
        int layout = 0;

        // Determine the action to take according to the clicked RadioButton
        switch (view.getId()) {

            // Replace any Fragment in the first FrameLayout by the LogInFragment
            case R.id.rbLogin:
                // Keep a reference to the Tag and FrameLayout to be used to add the Fragment
                tag = LOGIN;
                layout = R.id.flFragment1;
                // Get a reference to the Fragment identified by the required Tag
                fragmentToAdd = getSupportFragmentManager().findFragmentByTag(tag);
                // If there is no such reference, then create a new LogInFragment
                if (fragmentToAdd == null) {
                    fragmentToAdd = LogInFragment.newInstance("David");
                }
                break;

            // Replace any Fragment in the first FrameLayout by the SignInFragment
            case R.id.rbSignIn:
                // Keep a reference to the Tag and FrameLayout to be used to add the Fragment
                tag = SIGNIN;
                layout = R.id.flFragment1;
                // Get a reference to the Fragment identified by the required Tag
                fragmentToAdd = getSupportFragmentManager().findFragmentByTag(tag);
                // If there is no such reference, then create a new LogInFragment
                if (fragmentToAdd == null) {
                    fragmentToAdd = new SignInFragment();
                }
                break;

            // Remove all Fragments from the first FrameLayout
            case R.id.rbClear1:
                // Get a reference to the Fragment identified by the Id of its container (LinearLayout)
                fragmentToRemove = getSupportFragmentManager().findFragmentById(R.id.flFragment1);
                break;

            // Replace any Fragment in the second FrameLayout by the ListStringFragment
            case R.id.rbListStrings:
                // Keep a reference to the Tag and FrameLayout to be used to add the Fragment
                tag = LIST;
                layout = R.id.flFragment2;
                // Get a reference to the Fragment identified by the required Tag
                fragmentToAdd = getSupportFragmentManager().findFragmentByTag(tag);
                // If there is no such reference, then create a new LogInFragment
                if (fragmentToAdd == null) {
                    fragmentToAdd = new ListStringFragment();
                }
                break;

            // Replace any Fragment in the second FrameLayout by the GridImageFragment
            case R.id.rbGridImages:
                // Keep a reference to the Tag and FrameLayout to be used to add the Fragment
                tag = GRID;
                layout = R.id.flFragment2;
                // Get a reference to the Fragment identified by the required Tag
                fragmentToAdd = getSupportFragmentManager().findFragmentByTag(tag);
                // If there is no such reference, then create a new LogInFragment
                if (fragmentToAdd == null) {
                    fragmentToAdd = new GridImageFragment();
                }
                break;

            // Remove all Fragments from the second FrameLayout
            case R.id.rbClear2:
                // Get a reference to the Fragment identified by the Id of its container (LinearLayout)
                fragmentToRemove = getSupportFragmentManager().findFragmentById(R.id.flFragment2);
                break;
        }

        // Get a FragmentTransaction to begin some operations with the current FragmentManager
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Remove the required Fragment
        if (fragmentToRemove != null) {
            transaction.remove(fragmentToRemove);
        }
        // Replace the Fragments in the required Layout by the selected one
        if (fragmentToAdd != null) {
            transaction.replace(layout, fragmentToAdd, tag);
        }
        // Add the transaction to the BackStack, so it can be reversed by pressing the Back button
        transaction.addToBackStack(null);
        // Make changes effective
        transaction.commit();
    }
}