package com.nisa.mymoviecatalogueextension.presentation.search

import android.graphics.Color
import android.transition.TransitionInflater
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.nisa.mymoviecatalogueextension.R
import com.nisa.mymoviecatalogueextension.base.extension.clickSubmitButton
import com.nisa.mymoviecatalogueextension.base.extension.hideKeyboard
import com.nisa.mymoviecatalogueextension.base.extension.showKeyboard
import com.nisa.mymoviecatalogueextension.base.view.BaseFragment
import com.nisa.mymoviecatalogueextension.data.enum.MovieListPageType
import com.nisa.mymoviecatalogueextension.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutResourceId: Int = R.layout.fragment_search
    override val classTypeOfViewModel: Class<SearchViewModel> = SearchViewModel::class.java

    override fun init() {
        context?.let { sharedElementEnterTransition = TransitionInflater.from(it).inflateTransition(android.R.transition.move) }

        setupSearchView()
    }


    override fun setupClickListeners() {
        binding.imageButtonBack.setOnClickListener { findNavController().popBackStack() }

    }

    private fun setupSearchView() {
        (binding.searchView.findViewById(androidx.appcompat.R.id.search_src_text) as TextView).setTextColor(Color.WHITE)
        binding.searchView.clickSubmitButton { query ->
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToMovieListFragment(pageType = MovieListPageType.SEARCH, searchQuery = query))
        }
    }

    override fun onStart() {
        super.onStart()
        binding.searchView.showKeyboard(requireActivity())
        binding.searchView.onActionViewExpanded()
    }

    override fun onStop() {
        super.onStop()
        binding.searchView.hideKeyboard(requireActivity())
    }

}
