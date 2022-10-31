package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.myapplication.databinding.ItemRowBinding

class StockAdapter (val items: MutableList<Stock>)
    : RecyclerView.Adapter<StockAdapter.ViewHolder>(){

    private lateinit var binding: ItemRowBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):StockAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding=ItemRowBinding.inflate(inflater, parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount()= items.size

    inner class ViewHolder(itemView: ItemRowBinding):RecyclerView.ViewHolder(itemView.root){
        fun bind(item: Stock){
        binding.apply {
            tvId.text=item.name.toString()
            tvName.text=item.value.toString()
        }
        }
    }




}