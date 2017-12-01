package com.hexeleries.ambareeshb.vzerve.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.dagger.component.DaggerActivityComponent
import com.hexeleries.ambareeshb.vzerve.dagger.modules.ActivityModule
import com.hexeleries.ambareeshb.vzerve.utils.FragmentUtils
import timber.log.Timber
import javax.inject.Inject

class SignUpActivity : AppCompatActivity(), SignUpFragment.SignUpScreens {
    @Inject
    lateinit var fragmentUtils: FragmentUtils

    override fun phoneNumberScreen() {
        Timber.i("Show phone number screen next")
    }

    override fun passwordScreen() {
        Timber.i("Show password screen next")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
        fragmentUtils.replace(R.id.frameContainer, SignUpFragment.newInstance(R.layout.sign_up_email))
                .commit()
    }

}
