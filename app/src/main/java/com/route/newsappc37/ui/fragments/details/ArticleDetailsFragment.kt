package com.route.newsappc37.ui.fragments.details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.domain.entities.NewsItemDTO
import com.route.newsappc37.R
import com.route.newsappc37.databinding.FragmentArticleDetailsBinding



class ArticleDetailsFragment private constructor(): Fragment() {

    companion object {
        lateinit var articleItemSelected: NewsItemDTO



        fun newInstance(articlesItem: NewsItemDTO): ArticleDetailsFragment {
            articleItemSelected = articlesItem
            return ArticleDetailsFragment()
        }
    }
private lateinit var binding: FragmentArticleDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
         binding = DataBindingUtil.inflate(inflater,R.layout.fragment_article_details, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.articleDetails = articleItemSelected


        // fullArticle.text = articleItemSelected.url
        // Linkify.addLinks(fullArticle, Linkify.WEB_URLS)
        binding.fullArticle.setOnClickListener {
            val url : String = articleItemSelected.url!!
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(requireContext(), "Download a browser to view", Toast.LENGTH_SHORT).show()
            }
        }

    }

}