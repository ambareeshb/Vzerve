package com.hexeleries.ambareeshb.vzerve.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.dagger.component.DaggerActivityComponent
import com.hexeleries.ambareeshb.vzerve.dagger.modules.ActivityModule
import com.hexeleries.ambareeshb.vzerve.dagger.modules.FragmentModule
import com.hexeleries.ambareeshb.vzerve.models.User
import com.hexeleries.ambareeshb.vzerve.utils.Constants
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler = Handler()
        //Init activity component.
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this as AppCompatActivity))
                .build()
                .inject(this)
    }

    override fun onResume() {
        super.onResume()
        runnable = signInOrSignUp()
        handler.postDelayed(runnable, 1000)
    }

    /**
     * Check whether user is signed in (from shared preference).
     * return runnable containing appropriate Runnable.
     */
    private fun signInOrSignUp(): Runnable =
            Runnable {
                if (sharedPref.getBoolean(Constants.SHARED_PREF_SIGNED_IN, false)) {
                    //Signed In
                    val user: User = Gson().fromJson(sharedPref.getString(Constants.SHARED_PREF_USERS, ""), User::class.java)
                    startActivity(Intent(this@SplashActivity, SignInActivity::class.java)
                            .putExtra(Constants.SHARED_PREF_USERS, user))
                } else startActivity(Intent(this@SplashActivity, SignUpActivity::class.java))
                finish()
            }


    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}
