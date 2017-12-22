package com.hexeleries.ambareeshb.vzerve.ui.fragments


import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hexeleries.ambareeshb.vzerve.R
import kotlinx.android.synthetic.main.fragment_question.*


/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater?.inflate(R.layout.fragment_question, container, false)

    private val questionViewModel: QuestionsViewModel
        get() = ViewModelProviders.of(this)[QuestionsViewModel::class.java]

    companion object {
        private val BUNDLE_KEY_ANSWER_LAYOUT = "BUNDLE_ANSWER_TYPE"
        private val BUNDLE_KEY_SERVICE_ID = "BUNDLE_SERVICE_ID"
        fun newInstance(answerType: Int, serviceId: Long): QuestionFragment {
            return QuestionFragment().apply {
                val bundle = Bundle()
                bundle.putInt(BUNDLE_KEY_ANSWER_LAYOUT, answerType)
                bundle.putLong(BUNDLE_KEY_SERVICE_ID, serviceId)
                arguments = bundle
            }
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionViewModel.question.observe(this, Observer { answer ->
            questionText.text = answer?.question
            loadingView?.let {
                it.visibility = View.GONE
                questionLayout?.visibility = View.VISIBLE
            }

        })

        loadingView?.let {
            it.visibility = View.VISIBLE
            questionLayout?.visibility = View.GONE
        }
        questionViewModel.firstQuestion(arguments?.getLong(BUNDLE_KEY_SERVICE_ID) ?: 0)

        nextQuestionButton?.setOnClickListener {
            loadingView?.let {
                it.visibility = View.VISIBLE
                questionLayout?.visibility = View.GONE
            }
            questionViewModel.nextQuestion(questionViewModel.question.value?.questionId ?: 0)
        }
    }

}
