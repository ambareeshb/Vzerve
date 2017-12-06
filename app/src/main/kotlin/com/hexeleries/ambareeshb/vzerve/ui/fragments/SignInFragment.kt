package com.hexeleries.ambareeshb.vzerve.ui.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hexeleries.ambareeshb.vzerve.R
import kotlinx.android.synthetic.main.fragment_sign_in.*


/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {
    private lateinit var listener: SignIn
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =// Inflate the layout for this fragment
            inflater?.inflate(R.layout.fragment_sign_in, container, false)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is SignIn) {
            listener = context
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonSignIn?.setOnClickListener { listener?.signIn(textEmail.text.toString(), textPassword.text.toString()) }
    }

    companion object {
        /**
         * @return A new instance of fragment SignInFragment.
         */
        fun newInstance(): SignInFragment {
            val fragment = SignInFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    interface SignIn {
        fun signIn(email: String, password: String)
    }
}// Required empty public constructor
