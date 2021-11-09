package com.batdaulaptrinh.udemycoupons.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.batdaulaptrinh.udemycoupons.R
import com.batdaulaptrinh.udemycoupons.databinding.CouponItemBinding
import com.batdaulaptrinh.udemycoupons.model.CouponItem
import com.batdaulaptrinh.udemycoupons.util.TimeLeft
import com.bumptech.glide.Glide

class RecyclerCouponAdapter(
    private val listCoupons: ArrayList<CouponItem>,
    val clickListener: (couponItem: CouponItem) -> Unit
) :
    RecyclerView.Adapter<RecyclerCouponAdapter.MyViewHolder>() {

    class MyViewHolder(val itemBinding: CouponItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindView(couponItem: CouponItem, clickListener: (couponItem: CouponItem) -> Unit) {
            itemBinding.apply {
                courseNameTxt.text = couponItem.Title
                categoryTextView.text = couponItem.Category
                authorTxt.text = couponItem.Author
                timeLeftTxt.text = TimeLeft.getTimeLeft(couponItem.EndTime)
                reviewTxt.text = "${couponItem.Reviews.toString()}✍"
                ratingTxt.text = "${couponItem.Rating.toString()}⭐"
                Glide.with(itemView.context)
                    .load(couponItem.ImageUrl)
                    .placeholder(android.R.color.white)
                    .into(itemBinding.imageView)
            }
            itemBinding.root.setOnClickListener {
                clickListener(couponItem)
                Log.i("MY TAG CLICK","CLICK COUPONS RECYCLERVER")
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
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listCoupons[position]
        holder.bindView(item, clickListener)
    }

    override fun getItemCount(): Int = listCoupons.size
}