package com.hexeleries.ambareeshb.vzerve.repositories

import android.arch.lifecycle.MediatorLiveData
import com.hexeleries.ambareeshb.vzerve.Service
import com.hexeleries.ambareeshb.vzerve.api.ApiInterface
import com.hexeleries.ambareeshb.vzerve.db.ServiceDao

/**
 * Created by ambareeshb on 05/12/17.
 * From where the home ui [HomeActivity],[HomeFragment],[HomeViewModel]
 */
class HomeRepo(val serviceDao: ServiceDao,val apiInterface:ApiInterface) {
    val serviceLiveData: MediatorLiveData<List<Service>> by lazy {
        refresh()
        getServiceList()
    }

    private fun refresh() {
//        apiInterface.getServices()
        //TODO API CALL SERVICE
    }

    /**
     * Get service list from DB.
     */
    private fun getServiceList(): MediatorLiveData<List<Service>> {
        val liveServices = MediatorLiveData<List<Service>>()
        liveServices.addSource(serviceDao.getAllServices(), { liveServices.value = it })
        return liveServices

    }


}
