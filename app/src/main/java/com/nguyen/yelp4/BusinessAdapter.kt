package com.nguyen.yelp4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.nguyen.yelp4.databinding.ItemBusinessBinding

class BusinessAdapter(val context: Context, val businesses: List<Business>):
    RecyclerView.Adapter<BusinessAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemBusinessBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(business: Business) {
            binding.tvName.text = business.name
            binding.ratingBar.rating = business.rating.toFloat()
            binding.tvReviewCount.text = "${business.review_count} Reviews"
            binding.tvAddress.text = business.location.address1
            binding.tvCategory.text = business.categories[0].title
            binding.tvDistance.text = business.displayDistance()
            binding.tvPrice.text = business.price
            val transform = RequestOptions().transform(CenterCrop(), RoundedCorners(20))
            Glide
                .with(context)
                .load(business.image_url)
                .apply(transform)
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val business = businesses[position]
        holder.bind(business)
    }

    override fun getItemCount() = businesses.size
}
