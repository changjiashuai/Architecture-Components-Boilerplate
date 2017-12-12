package com.changjiashuai.architecture_components_boilerplate.browse

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.changjiashuai.architecture_components_boilerplate.R
import com.changjiashuai.architecture_components_boilerplate.mapper.BufferooMapper
import com.changjiashuai.presentation.browse.BrowseBufferoosViewModel
import com.changjiashuai.presentation.browse.BrowseBufferoosViewModelFactory
import com.changjiashuai.presentation.data.Resource
import com.changjiashuai.presentation.data.ResourceState
import com.changjiashuai.presentation.model.BufferooView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var browseAdapter: BrowseAdapter
    @Inject lateinit var mapper: BufferooMapper
    @Inject lateinit var viewModelFactory: BrowseBufferoosViewModelFactory

    private lateinit var browseBufferoosViewModel: BrowseBufferoosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        browseBufferoosViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(BrowseBufferoosViewModel::class.java)

        setupBrowseRecycler()
        setupViewListeners()
    }

    private fun setupBrowseRecycler() {
        recycler_browse.layoutManager = LinearLayoutManager(this)
        recycler_browse.adapter = browseAdapter
    }

    private fun setupViewListeners() {
        view_empty.emptyListener = {
            browseBufferoosViewModel.fetchBufferoos()
        }
        view_error.errorListener = {
            browseBufferoosViewModel.fetchBufferoos()
        }
    }

    override fun onStart() {
        super.onStart()
        browseBufferoosViewModel.getBufferoos().observe(this, Observer<Resource<List<BufferooView>>> {
            it?.let { handleDataState(it.status, it.data, it.message) }
        })
    }

    private fun handleDataState(resourceState: ResourceState, data: List<BufferooView>?,
                                message: String?) {
        when (resourceState) {
            ResourceState.LOADING -> setupScreenForLoadingState()
            ResourceState.SUCCESS -> setupScreenForSuccessState(data)
            ResourceState.ERROR -> setupScreenForErrorState(message)
        }
    }

    private fun setupScreenForLoadingState() {
        progress.visibility = View.VISIBLE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.GONE
    }

    private fun setupScreenForSuccessState(data: List<BufferooView>?) {
        view_error.visibility = View.GONE
        progress.visibility = View.GONE
        if (data != null && data.isNotEmpty()) {
            updateListView(data)
            recycler_browse.visibility = View.VISIBLE
        } else {
            view_empty.visibility = View.VISIBLE
        }
    }

    private fun updateListView(bufferoos: List<BufferooView>) {
        browseAdapter.bufferoos = bufferoos.map { mapper.mapToViewModel(it) }
        browseAdapter.notifyDataSetChanged()
    }

    private fun setupScreenForErrorState(message: String?) {
        progress.visibility = View.GONE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.VISIBLE
    }
}
