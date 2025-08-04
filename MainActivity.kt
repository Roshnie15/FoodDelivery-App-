package com.example.fooddeliveryapp

import com.example.fooddeliveryapp.FoodAdapter
import com.example.fooddeliveryapp.FoodRepository
import com.example.fooddeliveryapp.CartManager
import com.example.fooddeliveryapp.CartActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.foodRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        foodAdapter = FoodAdapter(FoodRepository.foods) { food ->
            CartManager.addToCart(food)
            Toast.makeText(this, "${food.name} added to cart", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = foodAdapter

        // Search functionality
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredFoods = FoodRepository.foods.filter {
                    it.name.contains(newText ?: "", ignoreCase = true)
                }
                foodAdapter.filterList(filteredFoods)
                return true
            }
        })

        // Open cart screen
        findViewById<Button>(R.id.cartButton).setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}
