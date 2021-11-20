package com.batdaulaptrinh.udemycoupons.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batdaulaptrinh.udemycoupons.data.repository.CouponRepository
import com.batdaulaptrinh.udemycoupons.model.APIResponse
import com.batdaulaptrinh.udemycoupons.model.CouponItem
import com.batdaulaptrinh.udemycoupons.util.TimeLeft
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.concurrent.schedule

class HomeViewModel(val repository: CouponRepository) :
    ViewModel() {
    var showCouponList = MutableLiveData<List<CouponItem>>()
    var lastTimeUpdate = MutableLiveData<String>()

    init {
        initNetworkRequest()
    }

    private fun initNetworkRequest() {
        val call = repository.get()
        call.enqueue(object : Callback<APIResponse?> {
            override fun onResponse(call: Call<APIResponse?>, response: Response<APIResponse?>) {
                Log.d("TAG RESPONSE", response.toString())
                response.body()?.last_time_update?.let {
                    lastTimeUpdate.postValue(it)
                    if (TimeLeft.isLessThan1Hour(it)) {
                        response.body()?.results?.let { coupons ->
                            Log.i("VIEW MODEL TAG", coupons.toString())
                            viewModelScope.launch(IO) {
                                repository.deleteAllCoupon()
                                repository.addAllCoupon(coupons)
                            }
                        }
                    }
                }


            }

            override fun onFailure(call: Call<APIResponse?>, t: Throwable) {
                //TODO check internet
                // throw ExceptionInInitializerError()
                Timer().schedule(2000) {
                    initNetworkRequest()
                }
            }
        })

    }

    fun getAllCoupons() = repository.getAllCoupon()
    fun getCouponContainKeyword(formattedQuery: String) {
        Log.i("TAG OUTPUT SEARCH ", showCouponList.value.toString())
        viewModelScope.launch(IO) {
            showCouponList.postValue(repository.getCouponContainKeyword(formattedQuery))
        }
    }

}