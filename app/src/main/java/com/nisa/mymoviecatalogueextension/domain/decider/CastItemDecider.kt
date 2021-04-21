package com.nisa.mymoviecatalogueextension.domain.decider

import com.nisa.mymoviecatalogueextension.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CastItemDecider @Inject constructor() {
    fun provideImagePath(path: String?): String? = "${Constants.PROFILE_PATH}$path"
}