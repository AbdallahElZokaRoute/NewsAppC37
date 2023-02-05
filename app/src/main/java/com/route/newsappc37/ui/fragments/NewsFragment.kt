package com.route.newsappc37.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.route.newsappc37.Constants
import com.route.newsappc37.R
import com.route.newsappc37.api.APIManager
import com.route.newsappc37.model.SourcesItem
import com.route.newsappc37.model.SourcesResponse
import com.route.newsappc37.ui.adapter.NewsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {
    lateinit var newsRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    lateinit var tabLayout: TabLayout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsRecyclerView = requireView().findViewById(R.id.news_list_recycler_view)
        newsRecyclerView.adapter = NewsAdapter(null)
        tabLayout = requireView().findViewById(R.id.news_sources_tab_layout)
        APIManager
            .getWebServices()
            .getSources(Constants.API_KEY)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    Log.e("Tag", "Message2")
                }

                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    Log.e("Tag", "Message1")
                    val sources = response.body()?.sources
                    addTabs(sources)
                }

            })


        // Execute  -> Runs on Main Thread
        // Enqueue  -> Enqueues Calls to background Thread

    }

    private fun addTabs(sources: List<SourcesItem?>?) {
        sources?.let { list ->
//            for (item in list) {
//                val tab = tabLayout.newTab()
//                tab.text = item?.name
//                tabLayout.addTab(tab)
//            }
            list.forEach { item ->
                val tab = tabLayout.newTab()
                tab.text = item?.name
                tabLayout.addTab(tab)
            }
        }
    }
}
