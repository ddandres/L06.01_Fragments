/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0601_fragments.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import labs.dadm.l0601_fragments.R;

/**
 * Creates a DialogFragment to ask confirmation from the user.
 */
public class CustomDialogFragment extends DialogFragment {

    /**
     * Required empty public constructor.
     */
    public CustomDialogFragment() {
    }

    /**
     * Builds a custom AlertDialog to ask the user for confirmation.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Build an AlertDialog to ask for confirmation before finishing the application
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        // Set the massage to display in the Dialog
        builder.setMessage(R.string.dialog_message);
        // Include a Button for handling positive confirmation: exit application
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            // Notify the activity that the user wants to finish the application
            getParentFragmentManager().setFragmentResult("finish_app", new Bundle());
        });
        // Include a Button for handling negative confirmation: do not exit the application
        // No need for an onClickListener() here, as no action will take place
        builder.setNegativeButton(android.R.string.cancel, null);
        // Create and return the Dialog
        return builder.create();
    }
}