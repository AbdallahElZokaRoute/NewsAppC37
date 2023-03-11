package com.route.newsappc37

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.newsappc37.model.Category
import com.route.newsappc37.ui.adapter.OnCategorySelectedListener
import com.route.newsappc37.ui.fragments.CategoriesFragment
import com.route.newsappc37.ui.fragments.news.NewsFragment
import com.route.newsappc37.ui.fragments.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var drawerButton: ImageView
    lateinit var categoriesTextView: TextView
    lateinit var settingsTextView: TextView
    lateinit var categoriesFragment: CategoriesFragment
    lateinit var settingsFragment: SettingsFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        drawerButton = findViewById(R.id.drawer_button)
        drawerLayout = findViewById(R.id.drawer_layout)
        categoriesTextView = findViewById(R.id.categories_text_view)
        settingsTextView = findViewById(R.id.settings_text_view)
        categoriesFragment = CategoriesFragment()
        categoriesFragment.onCategorySelectedListener = object : OnCategorySelectedListener {
            override fun onCategorySelected(category: Category) {
                pushFragment(NewsFragment.newInstance(category), true)
            }

        }
        pushFragment(categoriesFragment)
        categoriesTextView.setOnClickListener {
            pushFragment(categoriesFragment)
        }
        settingsTextView.setOnClickListener {
            pushFragment(settingsFragment)

        }

        drawerButton.setOnClickListener {
            drawerLayout.open()
        }

    }

    fun pushFragment(fragment: Fragment, addToBackstack: Boolean = false) {
        val fragmentTransaction =
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
        if (addToBackstack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
        drawerLayout.close()

    }
}