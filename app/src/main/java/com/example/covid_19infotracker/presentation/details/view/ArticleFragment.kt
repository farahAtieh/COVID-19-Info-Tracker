package com.example.covid_19infotracker.presentation.details.view

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.covid_19infotracker.R
import com.example.covid_19infotracker.data.model.News
import com.example.covid_19infotracker.databinding.FragmentArticleDetailsBinding

class ArticleFragment: Fragment(R.layout.fragment_article_details) {

    private val args by navArgs<ArticleFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArticleDetailsBinding.bind(view)

        val articleData = args.articleData

        binding.apply {

            currentArticle = articleData

            tvAuthor.isVisible = articleData.author != null
            tvAuthorLabel.isVisible = articleData.author != null

            Glide.with(this@ArticleFragment)
                .load(articleData.urlToImage)
                .centerCrop()
                .thumbnail(0.1f)
                .error(R.drawable.ic_placeholder_article)
                /*.listener(object: RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = true
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        clArticleDetails.isVisible = true
                        tvAuthor.isVisible = articleData.author != null
                        tvAuthorLabel.isVisible = articleData.author != null

                        return false
                    }
                })*/
                .into(ivArticalLogo)


            val uri = Uri.parse(articleData.url)
            val intent = Intent(Intent.ACTION_VIEW, uri)

            tvReadArticle.apply {
                setOnClickListener{
                    context.startActivity(intent)
                }
                paint.isUnderlineText = true
            }



        }
    }
}