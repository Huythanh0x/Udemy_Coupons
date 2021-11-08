package com.batdaulaptrinh.udemycoupons.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.batdaulaptrinh.udemycoupons.R
import com.batdaulaptrinh.udemycoupons.databinding.FragmentHomeBinding
import com.batdaulaptrinh.udemycoupons.model.CouponItem
import com.batdaulaptrinh.udemycoupons.ui.adapter.RecyclerCouponAdapter
import com.batdaulaptrinh.udemycoupons.ui.viewmodel.CouponViewModel

class HomeFragment : Fragment() {
    lateinit var couponViewModel: CouponViewModel
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        couponViewModel = ViewModelProvider(this).get(CouponViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!

        binding.recyclerView.adapter = RecyclerCouponAdapter(arrayListOf<CouponItem>())

        couponViewModel.getAllCoupons().observe(viewLifecycleOwner, Observer { coupons ->
            binding.recyclerView.adapter = RecyclerCouponAdapter(coupons as ArrayList<CouponItem>)
        })
    }
}