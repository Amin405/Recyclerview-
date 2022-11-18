package com.example.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Button


import com.example.myapplication.databinding.ItemRowBinding

class StockAdapter (val user: MutableList<Stock>)
    : RecyclerView.Adapter<StockAdapter.ViewHolder>(){


    private lateinit var binding: ItemRowBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):StockAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding=ItemRowBinding.inflate(inflater, parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(user[position])
    }

    override fun getItemCount()= user.size






    inner class ViewHolder(itemView: ItemRowBinding):RecyclerView.ViewHolder(itemView.root) {
        fun bind(item: Stock) {
            binding.apply {
                tvId.text = item.name
                tvName.text = item.symbol
                tvNameStock.text = item.current_price.toString()
                updateButton.setOnClickListener {
                    tvNameStock.text = (item.current_price+600).toString()
                }
            }


        }


    }
    }