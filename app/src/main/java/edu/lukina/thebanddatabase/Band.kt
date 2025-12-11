package edu.lukina.thebanddatabase

/**
 * Created by Yelyzaveta Lukina on 07/22/25.
 */

// Declares a data class named 'Band'
// Data classes automatically generate standard methods like equals(), hashCode(), toString(),
// and componentN() functions.
data class Band(
    // Declares a mutable property 'id' of type Int, with a default value of 0.
    // This typically represents a unique identifier for the band.
    var id: Int = 0,

    // Declares a mutable property 'name' of type String, with a default empty string value.
    // This will store the name of the band.
    var name: String = "",

    // Declares a mutable property 'description' of type String, with a default empty string value.
    // This will store a description of the band.
    var description: String = ""
) {
    // The primary constructor for the Band data class is defined in the class header.
    // This empty block indicates that there is no additional logic needed
    // in the primary constructor's body.
}
