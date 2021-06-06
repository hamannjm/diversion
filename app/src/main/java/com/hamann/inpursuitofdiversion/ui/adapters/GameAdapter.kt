package com.hamann.inpursuitofdiversion.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hamann.inpursuitofdiversion.databinding.GameItemBinding
import com.hamann.inpursuitofdiversion.models.Game

class GameAdapter(
    private val onGameSelected: (gameGuid: String) -> Unit
): RecyclerView.Adapter<GameAdapter.GameAdapterViewHolder>() {

    private var items: List<Game> = ArrayList()

    fun setGames(games: List<Game>) {
        items = games
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = GameItemBinding.inflate(layoutInflater, parent, false)
        return GameAdapterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: GameAdapterViewHolder, position: Int) {
        val item = items[position]
        holder.binding.root.setOnClickListener {
            onGameSelected(item.guid)
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class GameAdapterViewHolder(val binding: GameItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Game) {
            if (item.image.thumbUrl.isNotBlank()) {
                Glide.with(binding.root.context)
                    .load(item.image.thumbUrl)
                    .into(binding.gameThumbImage)
            }
            binding.gameTitle.text = item.name
        }
    }
}