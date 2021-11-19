package com.batdaulaptrinh.udemycoupons.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.*
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

    @SuppressLint("NotifyDataSetChanged")
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
            "There are ${coupons.size} today".also { binding.numberOfCoupon.text = it }
        })
        homeViewModel.showCouponList.observe(viewLifecycleOwner, { coupons ->
            adapter.setList(coupons)
            adapter.notifyDataSetChanged()
        })
        homeViewModel.lastTimeUpdate.observe(viewLifecycleOwner) { lastTimeUpdate ->
            Log.i("TAG TIME", lastTimeUpdate)
            binding.lastTimeUpdate.text = TimeLeft.getTimeUpdate(lastTimeUpdate)

        }
    }

    @SuppressLint("SetTextI18n")
    fun showCouponDetail(couponItem: CouponItem) {
        val builder = AlertDialog.Builder(requireContext())
        val detailCouponDialogBinding = DetailCouponDialogBinding.inflate(layoutInflater)
        detailCouponDialogBinding.apply {
            courseNameTxt.text = couponItem.title
            categoryTextView.text = couponItem.category
            authorTxt.text = couponItem.author
            val timeLeft = TimeLeft.getTimeLeft(couponItem.endDay)
            timeLeftTxt.text = timeLeft
            reviewTxt.text = "${couponItem.reviews}✍"
            ratingTxt.text = "${couponItem.rating}⭐"
            durationTxt.text = "\uD83D\uDD52${couponItem.duration}"
            studentTxt.text = "${couponItem.students}\uD83E\uDDD1"
            descriptionContentText.text = Html.fromHtml(couponItem.description)
            headlineTxt.text = couponItem.headline
            Glide.with(requireActivity())
                .load(couponItem.previewImg)
                .placeholder(android.R.color.white)
                .into(detailCouponDialogBinding.imageView)
            shareBtn.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    val sendText =
                        "This course is FREE. There is only $timeLeft. Enroll now ${couponItem.couponLink}"
                    putExtra(Intent.EXTRA_TEXT, sendText)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
            enrollBtn.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(couponItem.couponLink))
                startActivity(browserIntent)
            }
        }

        builder.setView(detailCouponDialogBinding.root)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        builder.show()

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            val formattedQuery = "%${query}%"
            homeViewModel.getCouponContainKeyword(formattedQuery)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.i("MY TAG", "TEXT CHANGE $newText")
        val formattedQuery = "%${newText}%"
        homeViewModel.getCouponContainKeyword(formattedQuery)
        return true
    }

}
