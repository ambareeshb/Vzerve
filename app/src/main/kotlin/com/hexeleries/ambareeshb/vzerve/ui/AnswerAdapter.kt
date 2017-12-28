package com.hexeleries.ambareeshb.vzerve.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.api.Answer
import com.hexeleries.ambareeshb.vzerve.api.Selection
import kotlinx.android.synthetic.main.answers_radio.view.*

/**
 * Created by ambareeshb on 24/12/17.
 * Adapter for answers.
 */
class AnswerAdapter : RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {
    companion object {
        val VIEW_TYPE_RADIO = 10001
    }

    var answers: List<Answer>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val selectedAnswer: Answer?
        get() = answers?.firstOrNull { it.selected == Selection.SELECTED } ?: answers?.get(0)

    override fun getItemCount(): Int =
            answers?.size ?: 0


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindView(answers?.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            when (viewType) {
                VIEW_TYPE_RADIO -> ViewHolderRadioAnswer(LayoutInflater.from(parent?.context)
                        .inflate(R.layout.answers_radio, parent, false))
                else -> ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.answers_radio, parent, false))
            }

    override fun getItemViewType(position: Int): Int = VIEW_TYPE_RADIO

    /**
     * If all answers are radio buttons.
     */
    inner class ViewHolderRadioAnswer(view: View) : ViewHolder(view) {

        fun bindRadioView(answerResponse: Answer?) {
            answerResponse?.selected = when (answerResponse?.selected) {
                Selection.TO_SELECT -> Selection.SELECTED
                Selection.SELECTED -> Selection.NOT_SELECTED
                else -> Selection.NOT_SELECTED
            }
            itemView?.radioBtnAnswer?.isChecked = answerResponse?.selected == Selection.SELECTED
            itemView?.setOnClickListener {
                it.post({
                    answerResponse?.selected = Selection.TO_SELECT
                    notifyDataSetChanged()
                })
            }
            itemView.answerText.text = answerResponse?.answer_value
        }
    }

    /**
     * Base class for View holder.
     */
    open inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(answerResponse: Answer?) {
            when (this) {
                is ViewHolderRadioAnswer -> this.bindRadioView(answerResponse)
            }

        }

    }
}