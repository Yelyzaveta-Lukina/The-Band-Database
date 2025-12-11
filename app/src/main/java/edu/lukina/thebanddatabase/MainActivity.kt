package edu.lukina.thebanddatabase

/**
 * Created by Yelyzaveta Lukina on 07/22/25.
 */

// Imports the Bundle class from the Android framework,
// used for passing data between activities or saving instance state.
import android.os.Bundle
// Imports the AppCompatActivity class from AndroidX,
// a base class for activities that use the support library action bar features.
import androidx.appcompat.app.AppCompatActivity
// Imports the Navigation class from the AndroidX Navigation component,
// used for finding NavControllers.
import androidx.navigation.Navigation
// Imports the NavHostFragment class from the AndroidX Navigation component,
// a fragment that hosts a navigation graph.
import androidx.navigation.fragment.NavHostFragment
// Imports the AppBarConfiguration class from the AndroidX Navigation component,
// used to configure the behavior of the action bar with navigation.
import androidx.navigation.ui.AppBarConfiguration
// Imports the NavigationUI class from the AndroidX Navigation component,
// provides static methods to integrate navigation with UI components like the action bar.
import androidx.navigation.ui.NavigationUI

// Declares the MainActivity class, inheriting from AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Overrides the onCreate method, which is called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        // Calls the superclass's (AppCompatActivity) onCreate method to perform default initialization.
        super.onCreate(savedInstanceState)
        // Sets the user interface layout for this activity from the XML file R.layout.activity_main.
        setContentView(R.layout.activity_main)

        // Retrieves the NavHostFragment from the activity's layout using its ID R.id.nav_host_fragment.
        // 'supportFragmentManager' is used to interact with fragments within this activity.
        // The result is cast to NavHostFragment.
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Gets the NavController from the NavHostFragment.
        // The NavController manages app navigation within this NavHost.
        val navController = navHostFragment.navController

        // Creates an AppBarConfiguration.Builder, passing the navigation graph associated with the NavController.
        val appBarConfig = AppBarConfiguration.Builder(navController.graph).build()

        // Sets up the ActionBar to work with the NavController.
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig)
    }

    // Overrides the onSupportNavigateUp method,
    // which is called when the user presses the "Up" button in the action bar.
    override fun onSupportNavigateUp(): Boolean {
        // Finds the NavController associated with the NavHostFragment in this activity.
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        // Attempts to navigate up in the navigation stack using the NavController.
        // If 'navController.navigateUp()' successfully handles the up navigation, it returns true.
        // Otherwise  it calls the superclass's onSupportNavigateUp() method.
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
