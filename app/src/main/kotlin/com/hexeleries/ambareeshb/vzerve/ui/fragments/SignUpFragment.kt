package com.hexeleries.ambareeshb.vzerve.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hexeleries.ambareeshb.vzerve.R
import kotlinx.android.synthetic.main.sign_up_email.*
import kotlinx.android.synthetic.main.sign_up_password.*
import kotlinx.android.synthetic.main.sign_up_phone_number.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SignUpFragment.SignUpScreens] interface
 * to handle interaction events.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
    private var listener: SignUpScreens? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =// Inflate the layout for this fragment
            inflater?.inflate(arguments?.getInt(BUNDLE_KEY_LAYOUT) ?: R.layout.sign_up_email, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initToolbar()
        textAlreadyMember?.setOnClickListener { listener?.gotoSignInScreen() }
        nextPhone?.let {
            toolbarBackButton?.visibility = View.GONE
            it.setOnClickListener { listener?.nextFromEmail(textEmail.text.toString()) }
        }

        nextPassword
                ?.let {
                    toolbarBackButton?.visibility = View.VISIBLE
                    it.setOnClickListener {
                        listener?.nextFromPhone(textPhone.text.toString())
                    }
                }
        buttonSubmitSignUp
                ?.apply { setOnClickListener { listener?.submitSignUp(passwordText.text.toString()) } }
    }

    /**
     * Set back action as going back to the previous fragment.
     */
    private fun initToolbar() {
        toolbarBackButton?.setOnClickListener { fragmentManager.popBackStack() }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is SignUpScreens) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement SignUpScreens interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {

        private val BUNDLE_KEY_LAYOUT = "LAYOUT"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SignUpFragment.
         */
        fun newInstance(@LayoutRes layout: Int): SignUpFragment {
            val fragment = SignUpFragment()
            val args = Bundle()
            args.putInt(BUNDLE_KEY_LAYOUT, layout)
            fragment.arguments = args
            return fragment
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface SignUpScreens {
        fun nextFromEmail(email: String)
        fun nextFromPhone(phone: String)
        fun submitSignUp(password: String)
        fun gotoSignInScreen()
    }
}
