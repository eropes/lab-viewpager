package edu.uw.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText

class MainActivity : AppCompatActivity(), MovieListFragment.OnMovieSelectedListener {
    private val searchFragment: SearchFragment? = null
    private val movieListFragment: MovieListFragment? = null
    private val detailsFragment: DetailFragment? = null
    private var pagerAdapter: PagerAdapter? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.pager)
        viewPager!!.adapter = pagerAdapter

    }

    //respond to search button clicking
    fun handleSearchClick(v: View) {
        val text = findViewById<View>(R.id.txt_search) as EditText
        val searchTerm = text.text.toString()

        val fragment = MovieListFragment.newInstance(searchTerm)

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment, MOVIE_LIST_FRAGMENT_TAG)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun onMovieSelected(movie: Movie) {
        val fragment = DetailFragment.newInstance(movie)

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment, MOVIE_DETAIL_FRAGMENT_TAG)
        ft.addToBackStack(null) //remember for the back button
        ft.commit()
    }

    companion object {

        private val TAG = "MainActivity"
        val MOVIE_LIST_FRAGMENT_TAG = "MoviesListFragment"
        val MOVIE_DETAIL_FRAGMENT_TAG = "DetailFragment"
    }


    private inner class MoviePagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> return searchFragment
                1 -> return movieListFragment
                2 -> return detailsFragment
                else -> return null
            }
        }

        override fun getCount(): Int {
            if(movieListFragment == null) {
                return 1
            } else if (detailsFragment == null) {
                return 2
            } else {
                return 3
            }
        }

        override fun getItemPosition(`object`: Any?): Int {
            return PagerAdapter.POSITION_NONE
        }



    }
}
