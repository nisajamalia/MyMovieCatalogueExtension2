package com.nisa.mymoviecatalogueextension.presentation.search

import androidx.databinding.ObservableField
import com.nisa.mymoviecatalogueextension.base.viewmodel.BaseViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor() : BaseViewModel() {
    var query = ObservableField("")

}