package com.covid.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.covid.Adapters.HomeFragmentAdapter
import com.covid.Model.HomeListModel
import com.covid.R
import java.util.*


class HomeFragment : Fragment() {
    private var rvHomeList: RecyclerView? = null
    private var context1: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        context1 = rootView.context
        init(rootView)
        showUI()
        showClickListener()
        return rootView
    }

    private fun init(rootView: View) {
        rvHomeList = rootView.findViewById<View>(R.id.rv_home_list) as RecyclerView
    }

    private fun showUI() {
        val layoutManager = GridLayoutManager(context, 2)
        rvHomeList!!.layoutManager = layoutManager
        var homeListModelList: List<HomeListModel> = ArrayList()
        homeListModelList = HomeListModel.list
        val homeFragmentAdapter = HomeFragmentAdapter(context!!, homeListModelList)
        rvHomeList!!.adapter = homeFragmentAdapter
    }

    private fun showClickListener() {

    }


}
