package com.hexeleries.ambareeshb.vzerve.ui.fragments


import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.location.Location
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.Service
import com.hexeleries.ambareeshb.vzerve.ui.ServiceAdapter
import com.hexeleries.ambareeshb.vzerve.ui.viewmodels.HomeViewModel
import com.hexeleries.ambareeshb.vzerve.utils.Constants
import kotlinx.android.synthetic.main.fragment_home.*
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>?) {
        Timber.i("Permission denied $perms")
        Snackbar.make(homeLayout, "Give location permission Settings -> VZERVE", Snackbar.LENGTH_LONG)
    }

    @SuppressLint("MissingPermission")
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>?) {
        //Start location update
        LocationServices.getFusedLocationProviderClient(activity).lastLocation
                .addOnCompleteListener { task: Task<Location>? ->
                    ViewModelProviders.of(this)[HomeViewModel::class.java]
                            .refreshLocation(task?.result?.latitude ?: 25.80069259999999
                                    , task?.result?.longitude ?: 55.97619940000004)
                }

        Timber.i("Permission granted $perms")

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater?.inflate(R.layout.fragment_home, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycler()
        ViewModelProviders.of(this)[HomeViewModel::class.java].getServices()
                .observe(this, Observer<List<Service>> { services ->
                    progressActivity.visibility = View.GONE
                    (recyclerServices?.adapter as ServiceAdapter).services = services
                })
        EasyPermissions.requestPermissions(this, Constants.PERMISSION_LOCATION_RATIONALE,
                Constants.PERMISSION_LOCATION_RC, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun initRecycler() {
        recyclerServices?.apply {
            layoutManager = LinearLayoutManager(activity,
                    LinearLayoutManager.VERTICAL, false)
            adapter = ServiceAdapter()
        }

    }

    companion object {
        /**
         * @return A new instance of fragment HomeFragment.
         */
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}
