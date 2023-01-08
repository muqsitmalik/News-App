package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val listner:NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {

    private val item:ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)

        view.setOnClickListener(){
            listner.newsItemClicked(item[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = item[position]
        holder.title.setText(currentItem.title)
        holder.author.setText(currentItem.author)
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun updateNews(updatedNews:ArrayList<News>){
        item.clear()
        item.addAll(updatedNews)
        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title = itemView.findViewById<TextView>(R.id.newstitle)
    val image = itemView.findViewById<ImageView>(R.id.newsimageview)
    val author = itemView.findViewById<TextView>(R.id.newsauthor)
}

interface NewsItemClicked{
    fun newsItemClicked(item:News)
}