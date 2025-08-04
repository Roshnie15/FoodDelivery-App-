package com.example.fooddeliveryapp
object CartManager {
    val cartItems = mutableListOf<FoodItem>()

    fun addToCart(food: FoodItem) {
        cartItems.add(food)
    }

    fun clearCart() {
        cartItems.clear()
    }

    fun getTotalPrice(): Double {
        return cartItems.sumOf { it.price }
    }
}
