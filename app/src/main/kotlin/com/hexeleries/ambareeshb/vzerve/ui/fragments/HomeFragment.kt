package com.hexeleries.ambareeshb.vzerve.ui.fragments


import android.Manifest
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.utils.Constants
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
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>?) {

        Timber.i("Permission granted $perms")
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater?.inflate(R.layout.fragment_home, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        EasyPermissions.requestPermissions(this, Constants.PERMISSION_LOCATION_RATIONALE,
                Constants.PERMISSION_LOCATION_RC, Manifest.permission.ACCESS_FINE_LOCATION)
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
