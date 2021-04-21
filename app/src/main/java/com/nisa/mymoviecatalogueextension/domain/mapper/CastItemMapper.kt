package com.nisa.mymoviecatalogueextension.domain.mapper

import com.nisa.mymoviecatalogueextension.data.model.response.CastResponse
import com.nisa.mymoviecatalogueextension.data.model.uimodel.CastViewItem
import com.nisa.mymoviecatalogueextension.domain.decider.CastItemDecider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CastItemMapper @Inject constructor(private val itemDecider: CastItemDecider) :
    Mapper<CastResponse, List<CastViewItem>?> {
    override fun mapFrom(item: CastResponse): List<CastViewItem>? {
        return item.cast?.map { cast ->
            CastViewItem(
                name = cast.name.orEmpty(),
                character = cast.character.orEmpty(),
                profilePath = itemDecider.provideImagePath(cast.profilePath).orEmpty()
            )
        }
    }
}