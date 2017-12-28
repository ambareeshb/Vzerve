package com.hexeleries.ambareeshb.vzerve.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hexeleries.ambareeshb.vzerve.api.ApiResponse
import com.hexeleries.ambareeshb.vzerve.App
import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.api.ApiConstants
import com.hexeleries.ambareeshb.vzerve.dagger.component.DaggerActivityComponent
import com.hexeleries.ambareeshb.vzerve.dagger.modules.ActivityModule
import com.hexeleries.ambareeshb.vzerve.db.User
import com.hexeleries.ambareeshb.vzerve.ui.fragments.SignInFragment
import com.hexeleries.ambareeshb.vzerve.utils.FragmentUtils
import kotlinx.android.synthetic.main.activity_sign_in.*
import rx.Single
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
        progressLoading?.visibility = View.VISIBLE
        (application as App).applicationComponent.apiInterface()
                .signIn(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ApiResponse>() {
                    override fun onError(e: Throwable?) {
                        Timber.e(e)
                        //Cancel progress bar
                        progressLoading?.visibility = View.GONE
                    }

                    override fun onCompleted() {
                        Timber.i("Completed sign in")
                        progressLoading?.visibility = View.GONE
                    }

                    override fun onNext(response: ApiResponse?) {
                        when (response?.response_code) {
                            ApiConstants.STATUS_CODE_SUCCESS -> {
                                Single.fromCallable {
                                    response.let {
                                        (application as App).userComponent.userDao()
                                                .insertUser(User(email = email, signedIn = true))
                                    }
                                }.subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe({
                                            startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                                            finish()
                                        }, { it.printStackTrace() })
                            }
                            else -> Snackbar.make(rootLayout, response?.response_text ?: "Something went wrong", Snackbar.LENGTH_SHORT).show()

                        }
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
                .addToBackStack(false)
                .commit()
    }
}
