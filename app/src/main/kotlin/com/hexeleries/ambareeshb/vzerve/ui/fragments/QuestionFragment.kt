package com.hexeleries.ambareeshb.vzerve.ui.fragments


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hexeleries.ambareeshb.vzerve.R


/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : DialogFragment() {

override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater?.inflate(R.layout.fragment_question, container, false)

    companion object {
        private val BUNDLE_KEY_ANSWER_LAYOUT = "BUNDLE_ANSWER_TYPE"
        fun newInstance(answerType: Int): DialogFragment {
            return DialogFragment().apply {
                val bundle = Bundle()
                bundle.putInt(BUNDLE_KEY_ANSWER_LAYOUT, answerType)
                arguments = bundle
            }
        }
    }

}
