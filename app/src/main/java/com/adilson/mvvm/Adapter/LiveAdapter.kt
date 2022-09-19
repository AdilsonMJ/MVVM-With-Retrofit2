package com.adilson.mvvm.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adilson.mvvm.R
import com.adilson.mvvm.databinding.ResItemLiveBinding
import com.adilson.mvvm.models.Live
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class LiveAdapter(
    private val onClick: (Live) -> Unit
) : RecyclerView.Adapter<LiveAdapter.LiveViewHolder>(){

    private var lives = mutableListOf<Live>()

    inner class LiveViewHolder(val binding : ResItemLiveBinding) : RecyclerView.ViewHolder(binding.root){

        private val title : TextView = binding.title
        private val author : TextView = binding.author

        fun bind(live: Live, onClick: (Live) -> Unit ){

            title.text = live.title
            author.text = live.author

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(live.thumbnailUrl)
                .into(binding.thumbnail)


            itemView.rootView.setOnClickListener{
                onClick(live)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveViewHolder {

        val resItemLiveBinding = ResItemLiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LiveViewHolder(resItemLiveBinding)

    }

    override fun onBindViewHolder(holder: LiveViewHolder, position: Int) {

        holder.bind(live = lives[position], onClick)

    }

    override fun getItemCount(): Int = lives.size


    fun setLivesList(lives: List<Live>){
        this.lives = lives.toMutableList()
        notifyDataSetChanged()
    }

}