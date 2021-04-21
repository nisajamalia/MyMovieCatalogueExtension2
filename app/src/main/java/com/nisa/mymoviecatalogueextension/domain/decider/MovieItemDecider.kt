package com.nisa.mymoviecatalogueextension.domain.decider

import com.nisa.mymoviecatalogueextension.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieItemDecider @Inject constructor() {
    fun provideImagePath(path: String?): String? = "${Constants.POSTER_PATH}$path"
}