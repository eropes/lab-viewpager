package edu.uw.viewpager

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import kotlinx.android.synthetic.main.notification_template_lines_media.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SearchFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SearchFragment : Fragment() {
    private var listener: OnSearchListener? = null

    companion object {
        @JvmStatic
        fun newInstance(): SearchFragment {
            val arguments = Bundle()
            val fragment = SearchFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    interface OnSearchListener {
        fun onSearchSubmitted (searchTerm: String)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSearchListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnSearchListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_search, container, false)

        val searchButton = view.findViewById<Button>(R.id.btn_search)
        searchButton.setOnClickListener {
            val searchText = view.findViewById<View>(R.id.txt_search)
            val searchTerm = searchText.text.toString()
            listener!!.onSearchSubmitted(searchTerm)
        }
        return view
    }



}
