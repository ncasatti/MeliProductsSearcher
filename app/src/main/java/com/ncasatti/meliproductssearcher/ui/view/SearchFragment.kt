package com.ncasatti.meliproductssearcher.ui.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ncasatti.meliproductssearcher.R
import com.ncasatti.meliproductssearcher.core.mainLogTag
import com.ncasatti.meliproductssearcher.databinding.FragmentSearchBinding
import com.ncasatti.meliproductssearcher.ui.viewmodel.SearchViewModel
import com.squareup.picasso.Picasso

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by activityViewModels()
    private val logTag = "$mainLogTag SearchFragment | "


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchViewModel.actionBarTittle.postValue("")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSearch.setOnClickListener{
            if (isConnectedToInternet()){
                onSearchButtonClick()
            }else{
                val toastMessage = "No se encuentra conexión a internet. Verifique las conexiones"
                Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
            }
        }

        searchViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progressCircular.isVisible = it
        })
    }

    private fun onSearchButtonClick(){
        val query = binding.searchBar.query
        Log.i("$logTag onSearchButtonClick()", "Buscando productos")



        if (query.isNotEmpty() && query.isNotBlank()){
            searchViewModel.searchProductByName(query.toString())

            searchViewModel.searchMutableData.observe(viewLifecycleOwner, Observer {
                findNavController().navigate(R.id.action_searchFragment_to_productsListFragment)
            })
        }else{
            val toastMessage = "Inserte una busqueda para continuar"
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun isConnectedToInternet(): Boolean{
        val manager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val conections = manager.getNetworkCapabilities(manager.activeNetwork)
        if (conections != null){
            if(conections.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            }else if (conections.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                return true
            }
        }
        return false
    }

}