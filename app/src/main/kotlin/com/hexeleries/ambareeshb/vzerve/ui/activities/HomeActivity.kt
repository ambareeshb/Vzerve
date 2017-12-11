package com.hexeleries.ambareeshb.vzerve.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hexeleries.ambareeshb.vzerve.App
import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.dagger.component.DaggerActivityComponent
import com.hexeleries.ambareeshb.vzerve.dagger.modules.ActivityModule
import com.hexeleries.ambareeshb.vzerve.ui.fragments.HomeFragment
import com.hexeleries.ambareeshb.vzerve.utils.FragmentUtils
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*
import rx.Single
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentUtils: FragmentUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
        //Load first fragment
        fragmentUtils.beginTransaction()
                .replace(R.id.frameContainer, HomeFragment.newInstance())
                .commit()
        //Temporary logout.
        toolbarBackButton.setOnClickListener {
            Single.fromCallable{
                (application as App).userComponent.userDao().logout()
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe{
                        startActivity(Intent(this, SignInActivity::class.java))
                        finish()
                    }
        }
    }
}


