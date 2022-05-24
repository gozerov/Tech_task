package ru.gozerov.tech_task.screens.news_details

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collect
import ru.gozerov.core.screens.BaseFragment
import ru.gozerov.core.utils.appComponent
import ru.gozerov.tech_task.R
import ru.gozerov.tech_task.databinding.NewsDetailsFragmentBinding
import ru.gozerov.tech_task.screens.news_details.NewsDetailsState.*
import ru.gozerov.tech_task.screens.news_web.NewsWebFragment.Companion.ARG_URL
import javax.inject.Inject

class NewsDetailsFragment : BaseFragment<NewsDetailsState>() {

    private lateinit var binding: NewsDetailsFragmentBinding

    @Inject
    lateinit var factory: NewsDetailsViewModel.Factory

    private val viewModel: NewsDetailsViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(ARG_ID)
        viewModel.getNewsById(id)
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect { state ->
                renderState(state)
            }
        }
        binding.goToWebButton.setOnClickListener {
            viewModel.newsUrl?.let { url ->
                findNavController().navigate(R.id.action_newsDetailsFragment_to_newsWebFragment, bundleOf(ARG_URL to url))
            }

        }
    }

    override fun renderState(state: NewsDetailsState) {
        when(state) {
            is LoadingState -> {
                binding.detailsContainer.forEach { v ->
                    if (v.id == R.id.newsDetailsProgress)
                        v.visibility = View.VISIBLE
                    else
                        v.visibility = View.GONE
                }
            }
            is SuccessState -> {
                binding.detailsContainer.forEach { v ->
                    if (v.id == R.id.errorState || v.id == R.id.newsDetailsProgress)
                        v.visibility = View.GONE
                    else
                        v.visibility = View.VISIBLE
                }
                with(binding) {
                    txtSource.text = state.news.source
                    txtTitle.text = state.news.title
                    txtPublishedAt.text = state.news.publishedAt
                    txtDescription.text = state.news.description
                    txtContent.text = state.news.content
                    state.news.imageUrl?.let { url ->
                        Glide.with(this@NewsDetailsFragment)
                            .load(url)
                            .into(imgNews)
                    }

                }
            }
            is ErrorState -> {
                Log.e("ERROR_STATE", state.errorMessage)
                binding.detailsContainer.forEach { v ->
                    if (v.id == R.id.errorState)
                        v.visibility = View.VISIBLE
                    else
                        v.visibility = View.GONE
                }
                binding.errorState.retryButton.setOnClickListener {
                    val id = requireArguments().getInt(ARG_ID)
                    viewModel.tryAgain(id)
                }
            }
        }
    }

    companion object {
        const val ARG_ID = "ARG_ID"
    }

}