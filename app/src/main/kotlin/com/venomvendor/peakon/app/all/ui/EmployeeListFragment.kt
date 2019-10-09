/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.app.all.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.widget.doOnTextChanged
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.venomvendor.peakon.R
import com.venomvendor.peakon.app.all.adapter.EmployeeListAdapter
import com.venomvendor.peakon.app.common.BaseFragment
import com.venomvendor.peakon.app.common.factory.OnItemClickListener
import com.venomvendor.peakon.app.util.EspressoIdlingResource
import com.venomvendor.peakon.employee.model.Employee
import com.venomvendor.peakon.employee.viewmodel.EmployeeViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.employee_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Main View for listing Teams.
 */
class EmployeeListFragment : BaseFragment(), OnItemClickListener<Employee> {

    private val mEmployeeViewModel: EmployeeViewModel by viewModel()

    private val disposable = CompositeDisposable()

    /* Adapter holding data. */
    private lateinit var mEmployeeAdapter: EmployeeListAdapter

    @get:LayoutRes
    override val layout = R.layout.employee_list_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Initialize Listeners
        initListener()
    }

    override fun initViews(view: View) {
        // For performance
        team_list.setHasFixedSize(true)
        // Set layout type

        updateLayoutManager()

        // Create Adapter
        mEmployeeAdapter = EmployeeListAdapter(context!!)
        // Set adapter
        team_list.adapter = mEmployeeAdapter
    }

    private fun updateLayoutManager() {
        team_list.layoutManager = LinearLayoutManager(context)
    }

    /**
     * Initialize event listeners
     */
    private fun initListener() {
        search.doOnTextChanged { text, _, _, _ ->
            text?.let {
                getAllEmployees(it.toString())
            }
        }

        mEmployeeAdapter.setOnItemClickLister(this)
    }

    private fun getAllEmployees(search: String) {
        if (disposable.size() > 0) {
            disposable.clear()
            if (progress_bar.visibility == View.VISIBLE) {
                EspressoIdlingResource.decrement()
            }
        }

        if (search.isBlank()) {
            mEmployeeAdapter.submitList(null)
            return
        }

        EspressoIdlingResource.increment()
        progress_bar.visibility = View.VISIBLE

        disposable.add(
            mEmployeeViewModel.getTeams(search)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent { _, _ ->
                    resetProgress()
                }
                .subscribe({ teams ->
                    handleData(search, teams)
                }, { ex ->
                    handleError(ex)
                })
        )
    }

    /**
     * Handler for data received
     *
     * @param response data
     */
    private fun handleData(search: String, response: List<Employee>) {
        if (response.isEmpty()) {
            showToast("No results for $search")
        }
        // Update data.
        mEmployeeAdapter.submitList(response)
    }

    /**
     * TODO: Handle errors
     */
    private fun handleError(error: Throwable) {
        // Show error toast
        showToast(error.message!!)
    }

    private fun resetProgress() {
        // Update indicators
        progress_bar.visibility = View.GONE
        refresh.isRefreshing = false

        EspressoIdlingResource.decrement()
    }

    override fun onClick(item: Employee, view: View, position: Int) {
        val action =
            EmployeeListFragmentDirections.actionEmployeeListFragmentToEmployeeDetailFragment()
        // Get Navigation controller
        Navigation.findNavController(view)
            // Navigate to next view with predefined action & data.
            .navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
