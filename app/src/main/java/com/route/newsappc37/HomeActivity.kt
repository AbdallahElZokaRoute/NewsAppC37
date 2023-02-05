package com.route.newsappc37

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.newsappc37.ui.fragments.CategoriesFragment
import com.route.newsappc37.ui.fragments.NewsFragment
import com.route.newsappc37.ui.fragments.SettingsFragment

class HomeActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var drawerButton: ImageView
    lateinit var categoriesTextView: TextView
    lateinit var settingsTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        drawerButton = findViewById(R.id.drawer_button)
        drawerLayout = findViewById(R.id.drawer_layout)
        categoriesTextView = findViewById(R.id.categories_text_view)
        settingsTextView = findViewById(R.id.settings_text_view)
        pushFragment(NewsFragment())
        categoriesTextView.setOnClickListener {
            pushFragment(CategoriesFragment())
        }
        settingsTextView.setOnClickListener {
            pushFragment(SettingsFragment())

        }

        drawerButton.setOnClickListener {
            drawerLayout.open()
        }

    }

    fun pushFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        drawerLayout.close()

    }
}