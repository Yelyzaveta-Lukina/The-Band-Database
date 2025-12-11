package edu.lukina.thebanddatabase

/**
 * Created by Yelyzaveta Lukina on 07/22/25.
 */

// Imports the Context class from the Android framework,
// used for accessing application-specific resources and classes.
import android.content.Context

// Declares the BandRepository class with a private constructor,
// enforcing the Singleton pattern.
// The constructor takes a Context object as a parameter.
class BandRepository private constructor(context: Context) {

    // Declares a public mutable property 'bandList' of type MutableList<Band>.
    // It is initialized as an empty mutable list that will hold Band objects.
    var bandList: MutableList<Band> = mutableListOf()

    // Defines a companion object, which allows for static-like members in Kotlin.
    companion object {
        // Declares a private nullable property 'instance' of type BandRepository.
        // This will hold the single instance of the BandRepository. It's initialized to null.
        private var instance: BandRepository? = null

        // Defines a public function 'getInstance'
        // that returns the single instance of BandRepository.
        // It takes a Context object as a parameter.
        fun getInstance(context: Context): BandRepository {
            // Checks if the 'instance' is null (i.e., if the repository hasn't been created yet).
            if (instance == null) {
                // If 'instance' is null, creates a new BandRepository instance
                // using the private constructor.
                // The provided 'context' is passed to the constructor.
                instance = BandRepository(context)
            }
            // Returns the non-null instance of BandRepository.
            // The '!!' operator asserts that 'instance' is not null at this point.
            return instance!!
        }
    }

    // Defines an initializer block, which is executed
    // when an instance of BandRepository is created.
    init {
        // Retrieves an array of band names from the string resources using the provided context.
        val bands = context.resources.getStringArray(R.array.bands)
        // Retrieves an array of band descriptions from the string resources
        // using the provided context.
        val descriptions = context.resources.getStringArray(R.array.descriptions)
        // Iterates through the indices of the 'bands' array.
        for (i in bands.indices) {
            // Creates a new Band object with an ID (i + 1), the current band name,
            // and the current band description.
            // Adds the newly created Band object to the 'bandList'.
            bandList.add(Band(i + 1, bands[i], descriptions[i]))
        }
    }

    // Defines a public function 'getBand' that takes a bandId (Int) as a parameter.
    // It returns a nullable Band object (Band?).
    fun getBand(bandId: Int): Band? {
        // Uses the 'find' extension function on 'bandList' to search for a band.
        // The lambda expression '{ it.id == bandId }' specifies the condition:
        // it returns the first band whose 'id' property matches the provided 'bandId'.
        // If no such band is found, 'find' returns null.
        return bandList.find { it.id == bandId }
    }
}
