package com.hexeleries.ambareeshb.vzerve.ui.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.api.ApiConstants
import com.hexeleries.ambareeshb.vzerve.ui.AnswerAdapter
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
        recyclerAnswer?.apply {
            layoutManager = LinearLayoutManager(this@QuestionFragment.context,LinearLayoutManager.VERTICAL,false)
            adapter = AnswerAdapter()

        }

        questionViewModel.question.observe(this, Observer { question ->
            questionText.text = question?.question
            loadingView?.let {
                it.visibility = View.GONE
                questionLayout?.visibility = View.VISIBLE
            }

        })
        questionViewModel.answers.observe(this, Observer { answer ->
            if(answer?.response_code == ApiConstants.STATUS_CODE_SUCCESS) {
                (recyclerAnswer?.adapter as AnswerAdapter).answers = answer.answers
            }
            else{
                this@QuestionFragment.dismiss()
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
            questionViewModel.nextQuestion(questionViewModel.answers.value?.answers?.get(0)?.next_question
                    ?: 0)
        }
    }

}
