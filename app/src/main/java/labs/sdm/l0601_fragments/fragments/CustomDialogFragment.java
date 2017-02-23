/*
 * Copyright (c) 2016. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.sdm.l0601_fragments.fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import labs.sdm.l0601_fragments.R;

/**
 * Creates a DialogFragment to ask confirmation from the user.
 */
public class CustomDialogFragment extends DialogFragment {

    // Hold a reference to the activity's callback to notify the user has confirmed the action
    PositiveButtonClickedListener listener;

    /**
     * Required empty public constructor.
     */
    public CustomDialogFragment() {
    }

    /**
     * Defines an interface that the parent activity should implement to
     * handle confirmation from the user.
     */
    public interface PositiveButtonClickedListener {
        void onPositiveButtonClicked();
    }

    /**
     * This method is called whenever the Fragment is attached to the activity,
     * so it is safe now access to the activity.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // The activity should implement the previously defined interface
        try {
            // Keep a reference to the activity's callback
            listener = (PositiveButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds a custom AlertDialog to ask the user for confirmation.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build an AlertDialog to ask for confirmation before finishing the application
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // Set the massage to display in the Dialog
        builder.setMessage(R.string.dialog_message);
        // Include a Button for handling positive confirmation: exit application
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Notify the activity that the user wants to finish the application
                if (listener != null) {
                    listener.onPositiveButtonClicked();
                }
            }
        });
        // Include a Button for handling negative confirmation: do not exit the application
        // No need for an onClickListener() here, as no action will take place
        builder.setNegativeButton(android.R.string.cancel, null);
        // Create and return the Dialog
        return builder.create();
    }
}