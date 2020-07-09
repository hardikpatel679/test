package com.hdapp.test2.main.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hdapp.frameworkkotlin.base.fragment.BaseFragment
import com.hdapp.frameworkkotlin.data.api.ResponseResult
import com.hdapp.frameworkkotlin.factory.ViewModelFactory
import com.hdapp.frameworkkotlin.utils.snackbar
import com.hdapp.test.adapter.RowAdapter
import com.hdapp.test2.R

import com.hdapp.test2.events.TitleListener
import com.hdapp.test2.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment() : BaseFragment(), LifecycleOwner  {
    lateinit var mainViewModel: MainViewModel
    lateinit var titleCallback: TitleListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is MainActivity) {
            titleCallback = activity as MainActivity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel = ViewModelProvider(this, ViewModelFactory(api, db))
            .get(MainViewModel::class.java)
        swipeToRefresh.setColorSchemeResources(R.color.colorAccent);
        setListener();
        mainViewModel.getData()

        setObserver()
    }

    private fun setListener() {
        swipeToRefresh.setOnRefreshListener {
            mainViewModel.getData()
            mainViewModel.isswipeToRefresh(true)
        }
    }


    private fun setObserver() {
        activity?.let {
            mainViewModel.issWipetoRefresh.observe(it, Observer {
                swipeToRefresh.isRefreshing = it
            })
            mainViewModel.getRows().observe(it, Observer {
                val adapter = context?.let { it1 -> RowAdapter(it1, it) }
                rvRow.setHasFixedSize(true)
                rvRow.layoutManager = LinearLayoutManager(context)
                rvRow.adapter = adapter
                adapter?.notifyDataSetChanged()
            })
            swipeToRefresh.isRefreshing = false
            mainViewModel.dataResponse.observe(it, Observer {

                when (it) {
                    is ResponseResult.Success -> {
                        it.result?.data?.title?.let {
                            titleCallback.setTitle(it)

                        }
                        //progressBar.hide()
                        //Handel Data
                    }
                    is ResponseResult.Error -> {
                        //progressBar.hide()
                        it.error.errorMsg?.let { it1 -> root_layout.snackbar(it1) }
                    }
                    is ResponseResult.Loading -> {
                        // progressBar.show()
                    }
                }
            })
        }

    }



}