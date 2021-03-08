package com.nguyen.yelp4

data class Data(val total: Int, val businesses: List<Business>)

data class Business(
    val name: String,
    val rating: Double,
    val price: String,
    val review_count: Int,
    val distance: Double,
    val image_url: String,
    val categories: List<Category>,
    val location: Location
) {
    fun displayDistance(): String {
        val perMeter = 0.000621371
        val miles = "%.2f".format(distance * perMeter)
        return "$miles mi"
    }
}

data class Category(val title: String)

data class Location(val address1: String)