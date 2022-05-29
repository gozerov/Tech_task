package ru.gozerov.presentation.screens.news_web

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import ru.gozerov.presentation.databinding.FragmentNewsWebBinding

class NewsWebFragment: Fragment() {

    private lateinit var binding: FragmentNewsWebBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.webView) {
            settings.javaScriptEnabled = true
            webViewClient = object: WebViewClient() {}
            Log.e("TAG", requireArguments().getString(ARG_URL) ?: "null")
            arguments?.getString(ARG_URL)?.let { url ->
                loadUrl(url)
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    } else {
                        isEnabled = false
                        activity?.onBackPressed()
                    }
                }
            })
    }


    companion object {
        const val ARG_URL = "ARG_URL"
    }

}