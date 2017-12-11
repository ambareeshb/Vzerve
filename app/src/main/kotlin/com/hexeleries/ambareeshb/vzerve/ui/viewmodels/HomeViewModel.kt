package com.hexeleries.ambareeshb.vzerve.ui.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import com.hexeleries.ambareeshb.vzerve.App
import com.hexeleries.ambareeshb.vzerve.Service

/**
 * Created by ambareeshb on 05/12/17.
 * ViewModel for Home page.
 */
class HomeViewModel(private val app: Application) : AndroidViewModel(app) {

    /**
     * Refresh location around user.
     */
    fun refreshLocation(latitude: Double, longitude: Double) = (app as App).userComponent.userHomeRepo()
            .refreshLocation(latitude, longitude)

    /**
     * Live data containing services already stored in database.
     */
    fun getServices(): MediatorLiveData<List<Service>> = (app as App).userComponent.userHomeRepo().serviceLiveData


}
