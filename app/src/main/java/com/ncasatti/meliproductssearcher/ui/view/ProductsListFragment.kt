package com.ncasatti.meliproductssearcher.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncasatti.meliproductssearcher.R
import com.ncasatti.meliproductssearcher.databinding.FragmentProductsListBinding
import com.ncasatti.meliproductssearcher.ui.adapter.ProductsReciclerViewAdapter
import com.ncasatti.meliproductssearcher.ui.viewmodel.SearchViewModel

class ProductsListFragment : Fragment(), ProductsReciclerViewAdapter.OnItemClickListener {

    private lateinit var binding: FragmentProductsListBinding
    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading(it) })

        searchViewModel.searchMutableData.observe(viewLifecycleOwner, Observer {

            searchViewModel.actionBarTittle.postValue("${it.paging.total} resultados")
            val adapter = ProductsReciclerViewAdapter(it, this)

            binding.recyclerProducts.setHasFixedSize(true)
            binding.recyclerProducts.layoutManager = LinearLayoutManager(context)
            binding.recyclerProducts.adapter = adapter
        })

        searchViewModel.isEmpty.observe(viewLifecycleOwner, Observer { isEmpty(it) })

    }

    private fun isLoading(isLoading: Boolean){
        if (isLoading){
            binding.recyclerProducts.isVisible = false
            binding.progressCircular.isVisible = true
        }else{
            binding.recyclerProducts.isVisible = true
            binding.progressCircular.isVisible = false
        }
    }

    private fun isEmpty(isEmpty: Boolean){
        if (isEmpty){
            binding.recyclerProducts.isVisible = false
            binding.tvNoData.isVisible = true
        }else{
            binding.recyclerProducts.isVisible = true
            binding.tvNoData.isVisible = false
        }
    }

    override fun onClick(position: Int) {
        searchViewModel.position.postValue(position)
        findNavController().navigate(R.id.action_productsListFragment_to_productViewFragment)
    }

}