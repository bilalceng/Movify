package com.raywenderlich.movify.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.raywenderlich.movify.databinding.RecyclerViewItemBinding
import com.raywenderlich.movify.response.MovieListResponse
import com.raywenderlich.movify.util.Constants.Companion.POSTER_BASE_URL


class MovieAdaptor:RecyclerView.Adapter<MovieAdaptor.MovieAdaptorViewHolder>(){

    inner class MovieAdaptorViewHolder():RecyclerView.ViewHolder(binding.root){

        fun bind(item: MovieListResponse.Result){
            binding.apply {
                rating.text = item.voteAverage.toString()
                name.text = item.title

                val photoUrl = POSTER_BASE_URL + item.posterPath

                binding.image.load(photoUrl){
                    crossfade(true)
                    scale( Scale.FILL)
                }
                language.text = item.originalLanguage

            }
        }
    }

    lateinit var binding: RecyclerViewItemBinding
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdaptorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RecyclerViewItemBinding.inflate(inflater, parent, false)
        context = parent.context
        return MovieAdaptorViewHolder()
    }

    override fun onBindViewHolder(holder: MovieAdaptorViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
      return  differ.currentList.size
    }

    private val diffCallback = object: DiffUtil.ItemCallback<MovieListResponse.Result>(){
        override fun areItemsTheSame(oldItem: MovieListResponse.Result, newItem: MovieListResponse.Result): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: MovieListResponse.Result, newItem: MovieListResponse.Result): Boolean {
            return newItem == oldItem
        }

    }

     val differ = AsyncListDiffer(this,diffCallback)
/*
    var movies: List<MovieListResponse.Result>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

 */
}