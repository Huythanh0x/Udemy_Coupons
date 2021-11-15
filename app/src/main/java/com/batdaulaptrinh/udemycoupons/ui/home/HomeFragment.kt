package com.batdaulaptrinh.udemycoupons.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.batdaulaptrinh.udemycoupons.R
import com.batdaulaptrinh.udemycoupons.data.api.CouponService
import com.batdaulaptrinh.udemycoupons.data.api.RetrofitInstance
import com.batdaulaptrinh.udemycoupons.data.database.CouponDatabase
import com.batdaulaptrinh.udemycoupons.data.repository.CouponRepository
import com.batdaulaptrinh.udemycoupons.databinding.DetailCouponDialogBinding
import com.batdaulaptrinh.udemycoupons.databinding.FragmentHomeBinding
import com.batdaulaptrinh.udemycoupons.model.CouponItem
import com.batdaulaptrinh.udemycoupons.ui.adapter.RecyclerCouponAdapter
import com.batdaulaptrinh.udemycoupons.util.TimeLeft
import com.bumptech.glide.Glide

class HomeFragment : Fragment(), SearchView.OnQueryTextListener,
    android.widget.SearchView.OnQueryTextListener {
    lateinit var homeViewModel: HomeViewModel
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: RecyclerCouponAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val couponDao = CouponDatabase.getDatabase(requireContext()).couponDAO()
        val couponService = RetrofitInstance.getInstance().create(CouponService::class.java)
        val repository = CouponRepository(couponDao, couponService)
        val homeViewModelFactory = HomeViewModelFactory(repository)
        homeViewModel =
            ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        adapter = RecyclerCouponAdapter(arrayListOf()) { couponItem ->
            showCouponDetail(couponItem)
        }
        binding.recyclerView.adapter = adapter
        binding.searchView.setOnQueryTextListener(this)
        homeViewModel.getAllCoupons().observe(viewLifecycleOwner, { coupons ->
            adapter.setList(coupons)
            adapter.notifyDataSetChanged()
        })
        homeViewModel.showCouponList.observe(viewLifecycleOwner, { coupons ->
            adapter.setList(coupons)
            adapter.notifyDataSetChanged()
        })
    }

    @SuppressLint("SetTextI18n")
    fun showCouponDetail(couponItem: CouponItem) {
        val builder = AlertDialog.Builder(requireContext())
        val detailCouponDialogBinding = DetailCouponDialogBinding.inflate(layoutInflater)
        detailCouponDialogBinding.apply {
            courseNameTxt.text = couponItem.Title
            categoryTextView.text = couponItem.Category
            authorTxt.text = couponItem.Author
            timeLeftTxt.text = TimeLeft.getTimeLeft(couponItem.EndTime)
            reviewTxt.text = "${couponItem.Reviews}✍"
            ratingTxt.text = "${couponItem.Rating}⭐"
            Glide.with(requireActivity())
                .load(couponItem.ImageUrl)
                .placeholder(android.R.color.white)
                .into(detailCouponDialogBinding.imageView)
        }

        builder.setView(detailCouponDialogBinding.root)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        builder.show()

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            val couponToShow = homeViewModel.getCouponContainKeyword(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.i("MY TAG", "TEXT CHANGE $newText")

        val formattedQuery = "%${newText}%"
        val couponToShow = homeViewModel.getCouponContainKeyword(formattedQuery)
        return true
    }
}
