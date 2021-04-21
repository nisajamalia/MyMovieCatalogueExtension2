package com.nisa.mymoviecatalogueextension.data.model.response

import com.nisa.mymoviecatalogueextension.data.model.Cast
import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("cast") val cast: List<Cast>?,
    @SerializedName("id") val id: Int?
)