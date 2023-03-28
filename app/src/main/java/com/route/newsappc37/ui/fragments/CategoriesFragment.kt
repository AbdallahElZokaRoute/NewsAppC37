package com.route.newsappc37.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.route.newsappc37.R
import com.route.newsappc37.model.Category
import com.route.newsappc37.ui.adapter.CategoriesAdapter
import com.route.newsappc37.ui.adapter.OnCategorySelectedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    val categories = listOf(
        Category(
            "sports", R.drawable.ball,
            R.string.sports, R.color.red
        ),
        Category(
            "technology", R.drawable.politics,
            R.string.technology, R.color.blue
        ),
        Category(
            "health", R.drawable.health,
            R.string.health, R.color.pink
        ),
        Category(
            "business", R.drawable.bussines,
            R.string.business, R.color.brown_orange
        ),
        Category(
            "general", R.drawable.environment,
            R.string.general, R.color.baby_blue
        ),
        Category(
            "science", R.drawable.science,
            R.string.science, R.color.yellow
        ),
    )
    lateinit var recyclerView: RecyclerView
    lateinit var categoriesAdapter: CategoriesAdapter
    var onCategorySelectedListener: OnCategorySelectedListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = requireView().findViewById(R.id.categories_recycler_view)
        categoriesAdapter = CategoriesAdapter(categories)
        recyclerView.adapter = categoriesAdapter
        categoriesAdapter.onCategorySelected = object : OnCategorySelectedListener {

            override fun onCategorySelected(category: Category) {
                onCategorySelectedListener?.onCategorySelected(category)
            }
        }
    }
}