# L06.01_Fragments
Lecture 06.01 - Fragments, DISCA - UPV, Development of apps for mobile devices.

 Displays different custom Fragments (LogInFragment, SignInFragment, ListStringFragment, GridImageFragment) within the same screen.
 
 There exist two FrameLayout, that could be empty, or display one of the two Fragments: LogInFragment and SignInFragment in the first one, and ListStringFragment and GridImageFragment in the other.
 Performed transactions are added to a BackStack, so they can be undone by pressing the Back button.
 Each Fragment has its own OptionsMenu, which is added to the available options on the ActionBar.
 
 In addition, the activity presents options to display Settings through a PreferenceFragment, and a DialogFragment to confirm that the user wants to exit the application (otherwise the user must navigate back through the whole Fragmen Back Stack).
 The DialogFragment communicates to this activity through the PositiveButtonClickedListener interface.
 
 Up navigation from other activities (like the SettingsActivity) requires this activity to define android:launchMode="singleTop" in the Manifest.
