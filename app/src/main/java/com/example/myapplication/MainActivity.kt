package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var stockAdapter : StockAdapter
    private var stockList : MutableList<Stock> = mutableListOf()
    private var url = "https://api.coingecko.com/"


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //loadData()

        val retrofit = initialRetrofit()
        fetchStocks(retrofit)

        // get reference to button
        val addButton = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.AddStockText)


        // set on-click listener
        addButton.setOnClickListener {
            val stockName = editText.text.toString().trim().lowercase()

            //check if the EditText have values or not
            if(stockName.isNotEmpty())
            {
                createMessage("Added $stockName")
                fetchSpecificStock(retrofit,stockName)
                
            } else {
                createMessage("Please enter some stock like bitcoin or solana! ")
            }
            editText.text.clear()
        }
    }

    private fun createMessage(message :String) {
        Toast.makeText(
            this@MainActivity,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun initialRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // TODO:refactoring duplicated code
    private fun fetchSpecificStock(retrofit: Retrofit, name: String){
        val api = retrofit.create(ApiService:: class.java)
        api.getSpecificStock(name).enqueue(object : Callback<MutableList<Stock>> {
            override fun onResponse(call: Call<MutableList<Stock>>, response: Response<MutableList<Stock>>) {
                stockList.add(response.body()!![0])
                showData(stockList)
            }
            override fun onFailure(call: Call<MutableList<Stock>>, t: Throwable) {
                d("fail",t.message.toString())
            }
        })
    }

    private fun fetchStocks(retrofit: Retrofit) {
        val api = retrofit.create(ApiService:: class.java)
        api.fetchAllStocks().enqueue(object : Callback<MutableList<Stock>> {
            override fun onResponse(call: Call<MutableList<Stock>>, response: Response<MutableList<Stock>>) {
                stockList = response.body()!!
                showData(stockList)
            }
            override fun onFailure(call: Call<MutableList<Stock>>, t: Throwable) {
                d("fail",t.message.toString())
            }
        })
    }

    private fun showData(stock: MutableList<Stock>) {
        stockAdapter = StockAdapter(stock)
        binding.apply {
            rvMain.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = stockAdapter
            }
    }

    fun loadData(){
        stockList.add(Stock("", "AAPL", 115.69))
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
}