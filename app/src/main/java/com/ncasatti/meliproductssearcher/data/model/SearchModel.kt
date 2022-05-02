package com.ncasatti.meliproductssearcher.data.model

data class SearchModel(
    val site_id: String,
    val results: List<ProductModel>,
    val paging: Paging
)

data class Paging(
    val total: Int
)
