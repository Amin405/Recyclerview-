package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ItemRowBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var stockList : MutableList<Stock> = mutableListOf()
    private  lateinit var  stockAdapter: StockAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        stockAdapter = StockAdapter(stockList)
        binding.apply {
        rvMain.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
                adapter=stockAdapter

        }
        }
    }


    fun loadData(){
        stockList.add(Stock("Apple", "AAPL", 115.69))
        stockList.add(Stock("Microsoft", "MSFT", 214.36))
        stockList.add(Stock("Google", "GOOGL", 1519.45))
        stockList.add(Stock("Salesforce", "CRM", 255.52))
        stockList.add(Stock("Amazon", "AMZN", 3201.86))
        stockList.add(Stock("eBay", "EBAY", 54.05))
        stockList.add(Stock("Twitter", "TWTR", 45.41))
        stockList.add(Stock("Snapchat", "SNAP", 28.11))
        stockList.add(Stock("Facebook", "FB", 260.02))
    }


}