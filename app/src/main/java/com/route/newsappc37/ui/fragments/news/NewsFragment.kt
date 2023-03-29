package com.route.newsappc37.ui.fragments.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.route.domain.entity.NetworkResponse
import com.route.domain.entity.NewsItemDTO
import com.route.domain.entity.SourcesItemDTO
import com.route.newsappc37.R
import com.route.newsappc37.api.coroutines.MyThreadWorker
import com.route.newsappc37.databinding.FragmentNewsBinding
import com.route.newsappc37.model.Category
import com.route.newsappc37.model.NewsIntents
import com.route.newsappc37.model.NewsViewStates
import com.route.newsappc37.ui.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment private constructor() : Fragment() {
    companion object {
        lateinit var selectedCategory: Category
        fun newInstance(category: Category): NewsFragment {
            selectedCategory = category
            return NewsFragment()

        }
    }


    val viewModel: NewsViewModel by viewModels()

    //Eager Initialization
    var newsAdapter: NewsAdapter = NewsAdapter(null)

    // Lazy Initialization
    val newsAdapter2: NewsAdapter by lazy {
        NewsAdapter(null)
    }
    // Lazy vs Eager Initialization
    //


    lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
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

        // Execute  -> Runs on Main Thread
        // Enqueue  -> Enqueues Calls to background Thread
        Log.e("TAG", "onViewCreated:${Thread.currentThread().name} ")
        val thread = MyThreadWorker()
//        viewModel.triggerStateFlow()
        lifecycleScope.launchWhenStarted {

            // ViewModel -> Fragment or Activity
            viewModel.stateFlow.collect {
                Log.e("TagFragment", "Received $it")
            }
        }
        Log.e("Tag", "${thread.name}")
        subscribeToLiveData()
        viewModel.reduceNewsViewStates()
        lifecycleScope.launchWhenStarted {
            viewModel.newsViewStates.collect {
                when (it) {
                    is NewsViewStates.LoadingState -> {
                        //Show Loading
                        viewModel.loadingLiveData.value = true
                    }
                    is NewsViewStates.EmptyState -> {
                        //Dismiss Loading
                        // Show Toast that List is Empty
                        viewModel.loadingLiveData.value = false
                        Toast.makeText(requireContext(), "Empty List", Toast.LENGTH_SHORT).show()
                    }
                    is NewsViewStates.Error -> {
                        ////Dismiss Loading
                        // Show Toast that there is an error
                        viewModel.loadingLiveData.value = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                    }
                    is NewsViewStates.LoadedState -> {
                        //dismiss Loading
                        // Notify adapter
                        viewModel.loadingLiveData.value = false
                        newsAdapter.updateData(it.newsList)
                    }
                }
            }

        }
        thread.start()
    }

    /**
     * 1- Create Model for MVI (Intents , States)
     * 2- Process Intent
     * 3- Reduce States
     * 4- Render UI
     *
     *
     */

    fun subscribeToLiveData() {
        viewModel.sourcesLiveData.observe(viewLifecycleOwner) { sources ->
            addTabs(sources)
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoadingVisible ->
            binding.loadingProgressBar.isVisible = isLoadingVisible
        }
        /*lifecycleScope.launchWhenStarted {
            viewModel.newsFlow.collect {
                when (it) {
                    is NetworkResponse.Loading -> {
                        viewModel.loadingLiveData.value = true
                        Log.e("TAG", "subscribeToLiveData: ")

                    }
                    is NetworkResponse.Success<List<NewsItemDTO>> -> {
                     /*   viewModel.loadingLiveData.value = false
                        newsAdapter.updateData(it.data)
//                        newsAdapter2.updateData(it.data)
                        Log.e("TAG1", "subscribeToLiveData: ")

                      */
                    }
                    is NetworkResponse.Error -> {
                        viewModel.loadingLiveData.value = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                        Log.e("TA2", "subscribeToLiveData: ")
                    }

                }


            }
        }*/
        viewModel.messageLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

    }


    private fun addTabs(sources: List<SourcesItemDTO?>?) {
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
                val source = tab?.tag as SourcesItemDTO
//                viewModel.sourcesItemIdStateFlow.value = source.id
                lifecycleScope.launchWhenStarted {
                    viewModel.newsIntents.send(NewsIntents.SelectedTab(source.id))
                }
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
