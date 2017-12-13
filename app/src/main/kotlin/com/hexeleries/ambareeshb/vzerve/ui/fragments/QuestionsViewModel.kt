package com.hexeleries.ambareeshb.vzerve.ui.fragments

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.hexeleries.ambareeshb.vzerve.App
import com.hexeleries.ambareeshb.vzerve.api.AnswerResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by ambareeshb on 13/12/17.
 * Handles quetions related options.
 */
class QuestionsViewModel(private val app: Application) : AndroidViewModel(app) {
    val question: MutableLiveData<AnswerResponse> = MutableLiveData()


    fun firstQuestion(serviceId:Long){
        (app as App).applicationComponent.apiInterface()
                .getFirstQuestion(serviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({question.value = it},{it.printStackTrace()})

    }

    fun nextQuestion(questionId: Long) {
        (app as App).applicationComponent.apiInterface()
                .getNextQuestion(questionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ question.value = it}, {it.printStackTrace()})
    }
}