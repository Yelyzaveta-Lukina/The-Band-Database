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
// Imports the Navigation class from the AndroidX Navigation component,
// used for navigating between destinations
import androidx.navigation.Navigation
// Imports the DividerItemDecoration class from AndroidX RecyclerView,
// used to add dividers between items in a RecyclerView
import androidx.recyclerview.widget.DividerItemDecoration
// Imports the RecyclerView class from AndroidX,
// a flexible view for providing a limited window into a large data set
import androidx.recyclerview.widget.RecyclerView
import edu.lukina.thebanddatabase.DetailFragment.Companion.ARG_BAND_ID

// Declares the ListFragment class, inheriting from the Fragment class
class ListFragment : Fragment() {

    // Overrides the onCreateView method, which is called by the Android system
    // when it's time for the fragment to draw its user interface for the first time
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View {
        // Inflates the layout defined in R.layout.fragment_list into a View object
        // 'container' is the parent ViewGroup to attach the inflated view to
        // 'false' means the inflater should not attach the view to the root
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)

        // Defines an OnClickListener for items in the RecyclerView
        val onClickListener = View.OnClickListener { itemView: View ->

            // Retrieves the band ID stored in the tag of the clicked itemView
            val selectedBandId = itemView.tag as Int
            // Creates a new Bundle object to hold arguments to pass to the next fragment
            val args = Bundle()
            // Puts the selected band ID into the Bundle with the key ARG_BAND_ID
            args.putInt(ARG_BAND_ID, selectedBandId)

            // Attempts to find a view with the ID R.id.detail_frag_container within the rootView
            val detailFragContainer = rootView.findViewById<View>(R.id.detail_frag_container)

            // Checks if the detail fragment container view exists
            if (detailFragContainer == null) {
                // If it doesn't exist (likely a single-pane phone layout),
                // navigate to show_item_detail destination
                // navigate() performs the navigation action, passing the arguments (band ID)
                Navigation.findNavController(itemView).navigate(R.id.show_item_detail, args)

            } else {
                // If it does exist (likely a two-pane tablet layout),
                // navigate within the detail_frag_container
                // navigate() loads the fragment_detail destination into this container, passing the arguments
                Navigation.findNavController(detailFragContainer)
                    .navigate(R.id.fragment_detail, args)
            }
        }


        // Finds the RecyclerView widget within the inflated rootView using its ID
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.band_list)
        // Gets the singleton instance of BandRepository using the fragment's required context
        // Then, accesses the bandList property from the repository
        val bands = BandRepository.getInstance(requireContext()).bandList
        // Creates a new BandAdapter instance, passing the list of bands and the onClickListener
        // Sets this adapter to the RecyclerView to populate it with items
        recyclerView.adapter = BandAdapter(bands, onClickListener)

        // Creates a DividerItemDecoration to draw horizontal dividers between items in the RecyclerView
        val divider = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        // Adds the created item decoration to the RecyclerView
        recyclerView.addItemDecoration(divider)

        // Returns the inflated and configured rootView to be displayed by the fragment
        return rootView
    }

    // Defines a private inner class BandAdapter, which inherits from RecyclerView.Adapter
    // It takes a list of Band objects and an OnClickListener as constructor parameters.
    private class BandAdapter( private val bandList: List<Band>,
                               private val onClickListener: View.OnClickListener) :
        RecyclerView.Adapter<ListFragment.BandHolder>() {

        // Overrides the onCreateViewHolder method, which is called
        // when RecycleView needs a new ViewHolder of the given type to represent an item
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandHolder {
            // Gets a LayoutInflater from the context of the parent ViewGroup
            val layoutInflater = LayoutInflater.from(parent.context)
            // Returns a new instance of BandHolder, created by inflating its layout
            return BandHolder(layoutInflater, parent)
        }

        // Overrides the onBindViewHolder method, which is called by RecyclerView
        // to display the data at the specified position
        override fun onBindViewHolder(holder: BandHolder, position: Int) {
            // Gets the Band object at the current position in the bandList
            val band = bandList[position]
            // Calls the bind method of the BandHolder to set the data for this item
            holder.bind(band)
            // Sets the tag of the ViewHolder's itemView to the ID of the current band
            // This tag is used by the onClickListener to identify which band was clicked
            holder.itemView.tag = band.id
            // Sets the onClickListener (defined in onCreateView) to the ViewHolder's itemView
            holder.itemView.setOnClickListener(onClickListener)
        }

        // Overrides the getItemCount method,
        // which returns the total number of items in the data set held by the adapter
        override fun getItemCount(): Int {
            // Returns the size of the bandList
            return bandList.size
        }
    }

    // Defines a private inner class BandHolder, which inherits from RecyclerView.ViewHolder
    // This class describes an item view and metadata about its place within the RecyclerView.
    // It takes a LayoutInflater and an optional parent ViewGroup as constructor parameters.
    private class BandHolder(inflater: LayoutInflater, parent: ViewGroup?) :
    // Calls the superclass constructor, inflating the R.layout.list_item_band layout
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_band, parent, false)) {

        // Declares a private lateinit property 'nameTextView' of type TextView
        private lateinit var nameTextView: TextView

        // Defines an initializer block, which is executed when an instance of BandHolder is created
        init {
            // Finds the TextView with the ID R.id.band_name within the ViewHolder's itemView
            // Assigns this TextView to the nameTextView property
            nameTextView = itemView.findViewById(R.id.band_name)
        }

        // Defines a public function 'bind' that takes a Band object as a parameter
        // This function is responsible for setting the data from the Band object to the views in the ViewHolder
        fun bind(band: Band) {
            // Sets the text of the nameTextView to the name of the provided Band object
            nameTextView.text = band.name
        }
    }
}
