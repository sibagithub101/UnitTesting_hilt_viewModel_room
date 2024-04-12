package com.example.unittesting_course_1.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.unittesting_course_1.R
import com.example.unittesting_course_1.roomdb.ArtEntity
import javax.inject.Inject

class ArtRecyclerAdapter @Inject constructor(val glide: RequestManager) :
    RecyclerView.Adapter<ArtRecyclerAdapter.ArtViewHolder>() {
    class ArtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
        private val diffUtil = object : DiffUtil.ItemCallback<ArtEntity>() {
            override fun areItemsTheSame(oldItem: ArtEntity, newItem: ArtEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ArtEntity, newItem: ArtEntity): Boolean {
                return oldItem == newItem
            }
        }

    private val recylerListDiffer = AsyncListDiffer(this,diffUtil)
    var artList: List<ArtEntity>
        get() =  recylerListDiffer.currentList
        set(value) = recylerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
   val view = LayoutInflater.from(parent.context).inflate(R.layout.art_row,parent,false)
        return ArtViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
   val imageView = holder.itemView.findViewById<ImageView>(R.id.artImage)
   val nameText = holder.itemView.findViewById<TextView>(R.id.nameTv)
   val artistName = holder.itemView.findViewById<TextView>(R.id.artitsName)
   val yearText = holder.itemView.findViewById<TextView>(R.id.artYearTv)

        val art = artList[position]
        holder.itemView.apply {
            nameText.text = "Name: ${art.name}"
            artistName.text = "Artist Name: ${art.artistName}"
            yearText.text = "Year: ${art.year}"
            glide.load(art.imageUrl).into(imageView)
        }
    }

    override fun getItemCount(): Int {
       return artList.size
    }


}