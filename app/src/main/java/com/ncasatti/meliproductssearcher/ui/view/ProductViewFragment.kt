package com.ncasatti.meliproductssearcher.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ncasatti.meliproductssearcher.data.model.ProductModel
import com.ncasatti.meliproductssearcher.databinding.FragmentProductViewBinding
import com.ncasatti.meliproductssearcher.ui.viewmodel.SearchViewModel
import com.squareup.picasso.Picasso


class ProductViewFragment : Fragment() {

    private lateinit var binding: FragmentProductViewBinding
    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentProductViewBinding.inflate(inflater, container, false)
        searchViewModel.actionBarTittle.postValue("")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val productSelected = searchViewModel.position.value

        val product = productSelected?.let {
            searchViewModel.searchMutableData.value?.results?.get(it)
        }

        if (product != null) loadData(product)


    }

    private fun loadData(product: ProductModel) {
        binding.tvProductName.text = product.title
        val urlThumbnail = product.thumbnail.replace("http:", "https:")
        Picasso.get().load(urlThumbnail).into(binding.imageViewProduct)

        val price = "$ ${product.price}"
        binding.tvPrice.text = price
        binding.tvSeller.text = product.seller.eshop?.nick_name ?: ""

        val rating = (product.seller.seller_reputation.transactions.ratings.positive * 5)
        binding.ratingBar.rating = rating
        val availableQuantity = "${product.available_quantity} disponibles"
        binding.tvAvailableQuantity.text = availableQuantity
        val soldQuantity = "${product.sold_quantity} vendidos"
        binding.tvSoldQuantity.text = soldQuantity
    }
}