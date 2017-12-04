package com.hexeleries.ambareeshb.vzerve.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hexeleries.ambareeshb.vzerve.ApiResponse
import com.hexeleries.ambareeshb.vzerve.App
import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.dagger.component.DaggerActivityComponent
import com.hexeleries.ambareeshb.vzerve.dagger.modules.ActivityModule
import com.hexeleries.ambareeshb.vzerve.ui.SignInFragment
import com.hexeleries.ambareeshb.vzerve.utils.FragmentUtils
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SignInActivity : AppCompatActivity(), SignInFragment.SignIn {
    @Inject
    lateinit var fragmentUtils: FragmentUtils

    /**
     * When user press sign in.
     */
    override fun signIn(email: String, password: String) {
        (application as App).applicationComponent.apiInterface()
                .signIn(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ApiResponse>() {
                    override fun onError(e: Throwable?) {
                        Timber.e(e)
                    }

                    override fun onCompleted() {
                        Timber.i("Completed sign in")
                        startActivity(Intent(this@SignInActivity
                                , HomeActivity::class.java))
                        finish()
                    }

                    override fun onNext(t: ApiResponse?) {
                        Timber.i("Completed sign in")
                    }
                })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
        //Initial fragment
        fragmentUtils
                .beginTransaction()
                .replace(R.id.frameContainer, SignInFragment.newInstance())
                .addToBackStack(true)
                .commit()
    }
}