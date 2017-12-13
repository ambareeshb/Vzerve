package com.hexeleries.ambareeshb.vzerve.repositories

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import com.hexeleries.ambareeshb.vzerve.Service
import com.hexeleries.ambareeshb.vzerve.api.ApiInterface
import com.hexeleries.ambareeshb.vzerve.db.ServiceDao
import rx.schedulers.Schedulers

/**
 * Created by ambareeshb on 05/12/17.
 * From where the home ui [HomeActivity],[HomeFragment],[HomeViewModel]
 */
class HomeRepo(private val apiInterface: ApiInterface, private val serviceDao: ServiceDao) {

    val serviceLiveData: MediatorLiveData<List<Service>> by lazy {
        refreshLocation()
        getServiceList()
    }

    /**
     * To obtain services around the specified [latitude] and [longitude]
     */
     fun refreshLocation(latitude: Double = 100.0, longitude: Double = 200.0) {//Initially invalid lat and long.
        if (latitude.isValidLatitude() and longitude.isValidLongitude()) {
            apiInterface.getServices(latitude = latitude.toString(), longitude = longitude.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .subscribe({ response -> serviceDao.insertServiceList(response.services) },
                            { error -> error.printStackTrace() })

        }
    }

    /**
     * Get service list from DB.
     */
    private fun getServiceList(): MediatorLiveData<List<Service>> {
        val liveServices = MediatorLiveData<List<Service>>()
        liveServices.addSource(serviceDao.getAllServices(), { liveServices.value = it })
        return liveServices

    }

    private fun Double.isValidLatitude(): Boolean = (this <= 90.0) and (this >= -90.0)// If latitude is valid.
    private fun Double.isValidLongitude(): Boolean = (this <= 180) and (this >= -180.0) // If longitude is valid.
}
