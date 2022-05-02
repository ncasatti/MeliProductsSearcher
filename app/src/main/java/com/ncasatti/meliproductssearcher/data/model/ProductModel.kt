package com.ncasatti.meliproductssearcher.data.model

data class ProductModel(
    val id: String,
    val site_id: String,
    val title: String,
    val price: Float,
    val currency_id: String,
    val available_quantity: Int,
    val sold_quantity: Int,
    val thumbnail: String,
    val seller: Seller
)

data class Seller(
    val id: Int,
    val eshop: Eshop?,
    val seller_reputation: SellerReputation
)

data class SellerReputation(
    val level_id: String,
    val transactions: Transactions
)

data class Transactions(
    val ratings: Ratings
)

data class Ratings(
    val negative: Float,
    val neutral: Float,
    val positive: Float
)

data class Eshop(
    val nick_name: String
)