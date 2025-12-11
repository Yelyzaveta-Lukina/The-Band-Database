package edu.lukina.thebanddatabase

/**
 * Created by Yelyzaveta Lukina on 07/22/25.
 */

// Imports the Bundle class from the Android framework, used for passing data between components
import android.os.Bundle
// Imports the LayoutInflater class,
// used to instantiate layout XML files into their corresponding View objects
import android.view.LayoutInflater
// Imports the View class, the basic building block for user interface components
import android.view.View
// Imports the ViewGroup class, a special view that can contain other views (called children)
import android.view.ViewGroup
// Imports the TextView class, a UI component that displays text
import android.widget.TextView
// Imports the Fragment class from the AndroidX Fragment library
import androidx.fragment.app.Fragment

// Declares the DetailFragment class, inheriting from the Fragment class
class DetailFragment : Fragment() {

    // Defines a companion object, which allows for static-like members in Kotlin.
    // In this case, it defines a constant string ARG_BAND_ID.
    companion object {
        // Declares a constant string ARG_BAND_ID,
        // used as a key for passing the band ID in a Bundle
        const val ARG_BAND_ID = "band_id"
    }

    // Overrides the onCreateView method, which is called by the Android system
    // when it's time for the fragment to draw its user interface for the first time
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflates the layout defined in R.layout.fragment_detail into a View object
        val rootView = inflater.inflate(R.layout.fragment_detail, container, false)

        // Retrieves the band ID from the arguments passed to the fragment.
        val bandId = arguments?.getInt(ARG_BAND_ID) ?: 1 // Default to 1 if no argument

        // Gets the singleton instance of BandRepository using the fragment's required context.
        val band = BandRepository.getInstance(requireContext()).getBand(bandId)

        // Finds the TextView with the ID R.id.band_name within the inflated rootView.
        val bandNameTextView = rootView.findViewById<TextView>(R.id.band_name)

        // Finds the TextView with the ID R.id.band_description within the inflated rootView.
        val bandDescriptionTextView = rootView.findViewById<TextView>(R.id.band_description)

        // Checks if the 'band' object is not null (i.e., if a band was found for the given ID).
        if (band != null) {
            // If a band was found, sets the text of the 'bandNameTextView' to the band's name.
            bandNameTextView.text = band.name
            // Sets the text of the 'bandDescriptionTextView' to the band's description.
            bandDescriptionTextView.text = band.description
        }

        // Returns the inflated and configured 'rootView' to be displayed by the fragment.
        return rootView
    }
}
