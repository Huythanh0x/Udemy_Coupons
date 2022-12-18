package com.batdaulaptrinh.udemycoupons.ui.adapter

import android.annotation.SuppressLint
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
    private val clickListener: (couponItem: CouponItem) -> Unit
) :
    RecyclerView.Adapter<RecyclerCouponAdapter.MyViewHolder>() {

    class MyViewHolder(private val itemBinding: CouponItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(couponItem: CouponItem, clickListener: (couponItem: CouponItem) -> Unit) {
            itemBinding.apply {
                courseNameTxt.text = couponItem.title
                categoryTextView.text = couponItem.category
                authorTxt.text = couponItem.author
                timeLeftTxt.text = TimeLeft.getTimeLeft(couponItem.expiredDate)
                reviewTxt.text = "${couponItem.reviews}✍"
                ratingTxt.text = "${couponItem.rating}⭐"

                Glide.with(itemView.context)
                    .load(couponItem.previewImg)
                    .placeholder(android.R.color.white)
                    .into(itemBinding.imageView)
            }
            itemBinding.root.setOnClickListener {
                clickListener(couponItem)
                Log.i("MY TAG CLICK", "CLICK COUPONS RECYCLERVER")
            }

            Log.d("MY TAG RECYCLER VIEW", couponItem.title)
        }

    }

    fun setList(newListCoupons: List<CouponItem>) {
        listCoupons.clear()
        listCoupons.addAll(newListCoupons)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
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