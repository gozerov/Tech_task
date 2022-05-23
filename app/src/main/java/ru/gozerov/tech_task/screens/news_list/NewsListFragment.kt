package ru.gozerov.tech_task.screens.news_list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.forEach
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ru.gozerov.core.recycler_view.ActionListener
import ru.gozerov.core.recycler_view.LayoutManagerRV
import ru.gozerov.core.recycler_view.ORIENTATION
import ru.gozerov.core.recycler_view.setupRecyclerView
import ru.gozerov.core.screens.BaseFragment
import ru.gozerov.core.utils.appComponent
import ru.gozerov.tech_task.R
import ru.gozerov.tech_task.databinding.NewsListFragmentBinding
import ru.gozerov.tech_task.screens.news_list.NewsListState.*
import javax.inject.Inject

class NewsListFragment : BaseFragment<NewsListState>(), ActionListener<Int> {

    private lateinit var binding: NewsListFragmentBinding

    @Inject
    lateinit var factory: NewsListViewModel.Factory

    private val viewModel: NewsListViewModel by viewModels { factory }

    private val adapter = NewsListAdapter(this)

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect { state ->
                renderState(state)
            }
        }
    }

    override fun renderState(state: NewsListState) {
        when (state) {
            is LoadingState -> {
                binding.root.forEach { v ->
                    if (v.id == R.id.newsListProgress)
                        v.visibility = View.VISIBLE
                    else
                        v.visibility = View.GONE
                }
            }
            is SuccessState -> {
                binding.root.forEach { v ->
                    if (v.id == R.id.newsListRecyclerView)
                        v.visibility = View.VISIBLE
                    else
                        v.visibility = View.GONE
                }
                lifecycleScope.launch {
                    state.newsList.collect { news ->
                        binding.newsListRecyclerView.setupRecyclerView(
                            adapter = adapter,
                            data = news,
                            layoutManager = LayoutManagerRV.LINEAR(ORIENTATION.VERTICAL)
                        )
                    }
                }

            }
            is ErrorState -> {
                Log.e("ERROR_STATE", state.errorMessage)
                binding.root.forEach { v ->
                    if (v.id == R.id.state_error)
                        v.visibility = View.VISIBLE
                    else
                        v.visibility = View.GONE
                }
                binding.stateError.retryButton.setOnClickListener {
                    viewModel.tryAgain()
                }
            }
        }
    }

    override fun onClick(args: Int) {
        findNavController().navigate(R.id.action_newsListFragment_to_newsDetailsFragment, bundleOf("ARG_ID" to args))
    }


}