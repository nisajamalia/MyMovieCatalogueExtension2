package com.nisa.mymoviecatalogueextension.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.nisa.mymoviecatalogueextension.R
import com.nisa.mymoviecatalogueextension.base.adapter.BaseViewHolder
import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieViewItem
import com.nisa.mymoviecatalogueextension.databinding.ItemLoadMoreBinding
import com.nisa.mymoviecatalogueextension.databinding.ItemMovieListBinding
import com.nisa.mymoviecatalogueextension.presentation.common.MovieItemViewHolder
import com.nisa.mymoviecatalogueextension.presentation.list.adapter.holder.LoadMoreViewHolder
import com.nisa.mymoviecatalogueextension.util.MovieItemDiffCallback

class MovieListAdapter : PagedListAdapter<MovieViewItem, BaseViewHolder<*>>(MovieItemDiffCallback.diffCallback) {
    /** Item Click Functions **/
    var onMovieItemClick: ((MovieViewItem) -> Unit)? = null


    enum class ItemType {
        TYPE_MOVIE {
            override fun onCreateViewHolder(parent: ViewGroup, layoutInflater: LayoutInflater, onMovieItemClick: ((MovieViewItem) -> Unit)?): BaseViewHolder<*> {
                val binding = DataBindingUtil.inflate<ItemMovieListBinding>(layoutInflater, R.layout.item_movie_list, parent, false)
                return MovieItemViewHolder(binding, onMovieItemClick)
            }
        },
        TYPE_LOAD_MORE {
            override fun onCreateViewHolder(parent: ViewGroup, layoutInflater: LayoutInflater, onMovieItemClick: ((MovieViewItem) -> Unit)?): BaseViewHolder<*> {
                val binding = DataBindingUtil.inflate<ItemLoadMoreBinding>(layoutInflater, R.layout.item_load_more, parent, false)
                return LoadMoreViewHolder(binding)
            }
        };

        abstract fun onCreateViewHolder(parent: ViewGroup, layoutInflater: LayoutInflater, onMovieItemClick: ((MovieViewItem) -> Unit)? = null): BaseViewHolder<*>
        fun viewType(): Int = ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemType.values()[viewType].onCreateViewHolder(parent, layoutInflater, onMovieItemClick)
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemCount > 1 && itemCount == position + 1) {
            ItemType.TYPE_LOAD_MORE.viewType()
        } else return ItemType.TYPE_MOVIE.viewType()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return if (getItemViewType(position) == ItemType.TYPE_LOAD_MORE.viewType()) {
                4
            } else {
                1
            }
        }
    }


    override fun getItemCount(): Int {
        return if (super.getItemCount() != 0) super.getItemCount() + 1 else 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MovieItemViewHolder -> holder.bind(getItem(position))
        }
    }


}