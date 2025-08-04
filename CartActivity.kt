package com.example.fooddeliveryapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val cartRecyclerView = findViewById<RecyclerView>(R.id.cartRecyclerView)
        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        cartAdapter = CartAdapter(CartManager.cartItems)
        cartRecyclerView.adapter = cartAdapter

        val totalPriceText = findViewById<TextView>(R.id.totalPriceText)
        totalPriceText.text = "Total: ₹${CartManager.getTotalPrice()}"

        findViewById<Button>(R.id.buyButton).setOnClickListener {
            Toast.makeText(this, "Purchase Successful!", Toast.LENGTH_SHORT).show()
            CartManager.clearCart()
            finish()
        }
    }
}
