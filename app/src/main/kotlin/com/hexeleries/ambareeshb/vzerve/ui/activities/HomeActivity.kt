package com.hexeleries.ambareeshb.vzerve.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.dagger.component.DaggerActivityComponent
import com.hexeleries.ambareeshb.vzerve.dagger.modules.ActivityModule
import com.hexeleries.ambareeshb.vzerve.utils.FragmentUtils
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentUtils:FragmentUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
        //Load first fragment
        fragmentUtils.beginTransaction()
    }

}
