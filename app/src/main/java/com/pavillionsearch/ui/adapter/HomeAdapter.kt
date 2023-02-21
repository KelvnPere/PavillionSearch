package com.pavillionsearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pavillionsearch.R
import com.pavillionsearch.model.Item


class HomeAdapter: RecyclerView.Adapter<HomeAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_search,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val search = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(search.avatar_url).into(holder.itemView.findViewById(R.id.avatarUrl_Image))
            val loginText:TextView = holder.itemView.findViewById(R.id.login_textView)
            val typeTextView:TextView = holder.itemView.findViewById(R.id.type_textView)
            loginText.text = search.login
            typeTextView.text = search.type
            setOnClickListener {
                onItemClicklisterner?.let { it(search) }
            }
        }
    }

    private var onItemClicklisterner: ((Item) -> Unit)? = null

    fun setOnItemClickListerner(listener:(Item) -> Unit){
        onItemClicklisterner = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}