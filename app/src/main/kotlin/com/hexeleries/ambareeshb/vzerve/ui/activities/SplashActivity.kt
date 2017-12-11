package com.hexeleries.ambareeshb.vzerve.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.hexeleries.ambareeshb.vzerve.App
import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.dagger.component.DaggerActivityComponent
import com.hexeleries.ambareeshb.vzerve.dagger.modules.ActivityModule
import com.hexeleries.ambareeshb.vzerve.db.User
import rx.Single
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

class SplashActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler = Handler()
        //Init activity component.
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
    }


    override fun onResume() {
        super.onResume()
        runnable = toSignInOrSignUp()
        handler.postDelayed(runnable, 1000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }


    /**
     *If there is a signed in user go to sign in screen
     * else go to sign up screen.
     */
    private fun toSignInOrSignUp(): Runnable =
            Runnable {
                Single.fromCallable {
                    val user: User? = (application as App).userComponent
                            .userDao()
                            .signedInUser()
                    Timber.d("User name is ${user?.email}")
                    user?.let { startActivity(Intent(this@SplashActivity, HomeActivity::class.java)) }
                            ?: startActivity(Intent(this@SplashActivity, SignUpActivity::class.java))
                    finish()
                }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
            }


}
