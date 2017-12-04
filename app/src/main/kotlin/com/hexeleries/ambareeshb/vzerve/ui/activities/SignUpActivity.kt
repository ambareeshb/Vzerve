package com.hexeleries.ambareeshb.vzerve.ui.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.hexeleries.ambareeshb.vzerve.App
import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.dagger.component.DaggerActivityComponent
import com.hexeleries.ambareeshb.vzerve.dagger.modules.ActivityModule
import com.hexeleries.ambareeshb.vzerve.db.User
import com.hexeleries.ambareeshb.vzerve.ui.SignUpFragment
import com.hexeleries.ambareeshb.vzerve.utils.FragmentUtils
import kotlinx.android.synthetic.main.activity_sign_up.*
import rx.Single
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SignUpActivity : AppCompatActivity(), SignUpFragment.SignUpScreens {
    @Inject
    lateinit var fragmentUtils: FragmentUtils
    var user: User = User()

    /**
     * Next button clicked from email screen.
     */
    override fun nextFromEmail(email: String) {
        Timber.i("Show phone number screen next")
        if (!email.matches(Regex(".*.@.*\\.com"))) {
            showError("Email not valid")
            return
        }
        user = User(email = email)
        fragmentUtils.beginTransaction()
                .replace(R.id.frameContainer, SignUpFragment.newInstance(R.layout.sign_up_phone_number))
                .addToBackStack(true)
                .commit()
    }


    /**
     * When next button is clicked from Phone number screen.
     */
    override fun nextFromPhone(phone: String) {
        Timber.i("Show password screen next")
        user = User(email = user.email, phone = phone)
        fragmentUtils.beginTransaction()
                .replace(R.id.frameContainer, SignUpFragment.newInstance(R.layout.sign_up_password))
                .addToBackStack(true)
                .commit()
    }


    /**
     * After tapping submit on password screen.
     */
    override fun submitSignUp(password: String) {

        Single.fromCallable {
            val user = (application as App).userComponent
                    .userDao()
                    .getUser()
            Timber.i("The user cred is ${user.email} ${user.phone}")
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        //Submit sign up API call
        (application as App).applicationComponent.apiInterface().signUp(user.email, user.password, user.phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<User>() {
                    override fun onCompleted() {
                        Timber.i("User sign up successful")
                    }

                    override fun onNext(t: User?) {
                        Timber.i("User sign up successful")
                    }

                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                    }
                })
        Timber.i("Sign up clicked")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        //Dagger Activity component initialise.
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)

        fragmentUtils.beginTransaction()
                .replace(R.id.frameContainer, SignUpFragment.newInstance(R.layout.sign_up_email))
                .commit()
    }


    /**
     * Show error with appropriate message.
     */
    private fun showError(error: String) {
        Snackbar.make(rootLayout, error, Snackbar.LENGTH_SHORT)
                .show()
    }
}
