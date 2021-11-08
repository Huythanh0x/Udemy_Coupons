package com.batdaulaptrinh.udemycoupons.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.batdaulaptrinh.udemycoupons.R
import com.batdaulaptrinh.udemycoupons.databinding.CouponItemBinding
import com.batdaulaptrinh.udemycoupons.model.CouponItem
import com.bumptech.glide.Glide

class RecyclerCouponAdapter(private val listCoupons: ArrayList<CouponItem>) :
    RecyclerView.Adapter<RecyclerCouponAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val itemBinding = CouponItemBinding.bind(itemView)
        fun bindView(couponItem: CouponItem) {
            itemBinding.apply {
                courseNameTxt.text = couponItem.Title
                categoryTextView.text = couponItem.Category
                authorTxt.text = couponItem.Author
                reviewTxt.text = couponItem.Reviews.toString()
                Glide.with(itemView.context)
                    .load(couponItem.ImageUrl)
                    .placeholder(android.R.color.white)
                    .into(itemBinding.imageView)
            }


            Log.d("MY TAG RECYCLER VIEW", couponItem.Title)
        }

    }

    fun setList(newListCoupons: List<CouponItem>) {
        listCoupons.clear()
        listCoupons.addAll(newListCoupons)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: CouponItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.coupon_item,
            parent,
            false
        )
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listCoupons[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int = listCoupons.size
}