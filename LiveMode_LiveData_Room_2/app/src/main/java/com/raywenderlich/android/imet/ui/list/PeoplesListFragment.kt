
package com.raywenderlich.android.imet.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import android.view.*
import androidx.lifecycle.Observer
import com.raywenderlich.android.imet.IMetApp
import com.raywenderlich.android.imet.R
import com.raywenderlich.android.imet.data.model.People
import com.raywenderlich.android.imet.ui.add.AddPeopleActivity
import com.raywenderlich.android.imet.ui.details.PeopleDetailsActivity
import kotlinx.android.synthetic.main.fragment_peoples_list.*
/**
 * The Fragment to show people list
 */
class PeoplesListFragment : Fragment(),
        PeoplesListAdapter.OnItemClickListener,
        SearchView.OnQueryTextListener,
        SearchView.OnCloseListener {

    private lateinit var searchView: SearchView

    private lateinit var viewModel: PeoplesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        viewModel =
    }

    override fun onResume() {
        super.onResume()

        val peopleRepository = (activity?.application as IMetApp).getPeopleRepository()

        peopleRepository.getAllPeople().observe(this, Observer {
            peopleList->
            populatePeopleList(peopleList!!)
        })

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_peoples_list, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_peoples_list, menu)

        // Initialize Search View
        searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigate to add people
        addFab.setOnClickListener {
            val addPeopleIntent = Intent(context, AddPeopleActivity::class.java)
            startActivity(addPeopleIntent)
        }
    }

    /**
     * Callback for searchView text change
     */
    override fun onQueryTextChange(newText: String?) = true

    /**
     * Callback for searchView query submit
     */
    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    /**
     * Callback for searchView close
     */
    override fun onClose(): Boolean {
        return true
    }

    /**
     * Populates peopleRecyclerView with all people info
     */
    private fun populatePeopleList(peopleList: List<People>) {
        peopleRecyclerView.adapter = PeoplesListAdapter(peopleList, this)
    }

    /**
     * Navigates to people details on item click
     */
    override fun onItemClick(people: People, itemView: View) {
        val detailsIntent = Intent(context, PeopleDetailsActivity::class.java)
        detailsIntent.putExtra(getString(R.string.people_id), people.id)
        startActivity(detailsIntent)
    }

}
