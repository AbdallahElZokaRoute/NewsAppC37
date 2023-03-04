package com.route.newsappc37.ui.fragments.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.route.newsappc37.Constants
import com.route.newsappc37.R
import com.route.newsappc37.api.APIManager
import com.route.newsappc37.api.coroutines.MyThreadWorker
import com.route.newsappc37.databinding.FragmentNewsBinding
import com.route.newsappc37.databinding.NewsItemBinding
import com.route.newsappc37.model.Category
import com.route.newsappc37.model.NewsResponse
import com.route.newsappc37.model.SourcesItem
import com.route.newsappc37.model.SourcesResponse
import com.route.newsappc37.ui.adapter.NewsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment private constructor() : Fragment() {
    companion object {
        lateinit var selectedCategory: Category
        fun newInstance(category: Category): NewsFragment {
            selectedCategory = category
            return NewsFragment()

        }
    }


    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var binding: FragmentNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel = NewsViewModel()
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return binding.root
    }

    fun initViews() {
        newsAdapter = NewsAdapter(null)
        binding.newsListRecyclerView.adapter = newsAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.getSourcesFromAPI()
        subscribeToLiveData()
        // Execute  -> Runs on Main Thread
        // Enqueue  -> Enqueues Calls to background Thread
        Log.e("TAG", "onViewCreated:${Thread.currentThread().name} ")
        val thread = MyThreadWorker()

        Log.e("Tag", "${thread.name}")

        thread.start()
    }

    fun subscribeToLiveData() {
        viewModel.sourcesLiveData.observe(viewLifecycleOwner) { sources ->
            addTabs(sources)
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoadingVisible ->
            binding.loadingProgressBar.isVisible = isLoadingVisible
        }
        viewModel.articlesLiveData.observe(viewLifecycleOwner) { articles ->
            newsAdapter.updateData(articles)
        }
        viewModel.messageLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()

        }

    }


    private fun addTabs(sources: List<SourcesItem?>?) {
        sources?.let { list ->
//            for (item in list) {
//                val tab = tabLayout.newTab()
//                tab.text = item?.name
//                tabLayout.addTab(tab)
//            }
            list.forEach { item ->
                val tab = binding.newsSourcesTabLayout.newTab()
                tab.text = item?.name
                tab.tag = item
                binding.newsSourcesTabLayout.addTab(tab)
            }
        }

        binding.newsSourcesTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                val source = sources?.get(tab?.position!!)
                val source = tab?.tag as SourcesItem
                viewModel.getNewsBySource(source)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                binding.newsListRecyclerView.smoothScrollToPosition(0)
            }
        })
        binding.newsSourcesTabLayout.getTabAt(1)?.select()
        binding.newsSourcesTabLayout.getTabAt(0)?.select()

    }


}
