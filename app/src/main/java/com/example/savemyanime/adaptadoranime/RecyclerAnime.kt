package com.example.savemyanime.adaptadoranime

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.savemyanime.R

class RecyclerAnime(var contexto: Context, var animes: MutableList<Anime>):RecyclerView.Adapter<RecyclerAnime.HolderAnime>() {


    inner class HolderAnime(var itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderAnime {
        var itemView = LayoutInflater.from(contexto).inflate(R.layout.item_anime, parent , false)
        return HolderAnime(itemView)
    }

    override fun onBindViewHolder(holder: HolderAnime, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}