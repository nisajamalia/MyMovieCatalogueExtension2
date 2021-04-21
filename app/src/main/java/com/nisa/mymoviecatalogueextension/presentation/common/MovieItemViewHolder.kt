package com.nisa.mymoviecatalogueextension.presentation.common

import com.nisa.mymoviecatalogueextension.base.adapter.BaseViewHolder
import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieViewItem
import com.nisa.mymoviecatalogueextension.databinding.ItemMovieListBinding


class MovieItemViewHolder(val binding: ItemMovieListBinding, val itemClick: ((MovieViewItem) -> Unit)?) : BaseViewHolder<MovieViewItem?>(binding.root) {

    override fun bind(data: MovieViewItem?) {
        if (data == null) return

        binding.movie = data
        binding.root.setOnClickListener {
            itemClick?.invoke(data)
        }
        binding.executePendingBindings()
    }


}