package com.ncasatti.meliproductssearcher.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ncasatti.meliproductssearcher.R
import com.ncasatti.meliproductssearcher.data.model.SearchModel
import com.squareup.picasso.Picasso

class ProductsReciclerViewAdapter(
    private val search: SearchModel,
    private val listener: OnItemClickListener
): RecyclerView.Adapter<ProductsReciclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int = search.results.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.product_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = search.results[position]
        holder.tvProductName.text = product.title
        val quantitySold = "${product.sold_quantity} vendidos"
        holder.tvSoldQuantity.text = quantitySold
        val price = "$ ${product.price}"
        holder.tvPrice.text = price
        val urlThumbnail = product.thumbnail.replace("http:", "https:")
        Picasso.get().load(urlThumbnail).into(holder.imageViewProducts)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
        val tvProductName: TextView = view.findViewById(R.id.tv_productName)
        val imageViewProducts: ImageView = view.findViewById(R.id.image_view_product)
        val tvPrice: TextView = view.findViewById(R.id.tv_price)
        val tvSoldQuantity: TextView = view.findViewById(R.id.tv_soldQuantity)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onClick(position: Int)
    }
}