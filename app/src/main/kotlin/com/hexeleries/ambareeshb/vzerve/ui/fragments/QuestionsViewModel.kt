package com.hexeleries.ambareeshb.vzerve.ui.fragments

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.hexeleries.ambareeshb.vzerve.App
import com.hexeleries.ambareeshb.vzerve.api.AnswerResponse
import com.hexeleries.ambareeshb.vzerve.api.QuestionResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by ambareeshb on 13/12/17.
 * Handles quetions related options.
 */
class QuestionsViewModel(private val app: Application) : AndroidViewModel(app) {
    val question: MutableLiveData<QuestionResponse> = MutableLiveData()
    val answers: MutableLiveData<AnswerResponse> = MutableLiveData()


    fun firstQuestion(serviceId: Long) {
        (app as App).applicationComponent.apiInterface().apply {
            getFirstQuestion(serviceId)
                    .flatMap { response ->
                        question.postValue(response)
                        return@flatMap this.getAnswers(response.questionId)
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ answers.value = it }, { it.printStackTrace() })
        }
    }

    fun nextQuestion(questionId: Long) {
        (app as App).applicationComponent.apiInterface().apply {
            getNextQuestion(questionId)
                    .flatMap {
                        question.postValue(it)
                        return@flatMap this.getAnswers(it.questionId)
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ answers.value = it }, { it.printStackTrace() })

        }
    }
}