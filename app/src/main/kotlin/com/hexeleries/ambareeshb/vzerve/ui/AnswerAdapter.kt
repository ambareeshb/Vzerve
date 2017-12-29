package com.hexeleries.ambareeshb.vzerve.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.api.Answer
import com.hexeleries.ambareeshb.vzerve.api.Selection
import kotlinx.android.synthetic.main.answer_check_box.view.*
import kotlinx.android.synthetic.main.answers_radio.view.*

/**
 * Created by ambareeshb on 24/12/17.
 * Adapter for answers.
 */
class AnswerAdapter(private val answerElementType: String) : RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {
    companion object {
        val VIEW_TYPE_RADIO = "radio" to 10002
        val VIEW_TYPE_CHECKBOX = "checkbox" to 10003
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
                VIEW_TYPE_RADIO.second -> ViewHolderRadioAnswer(LayoutInflater.from(parent?.context)
                        .inflate(R.layout.answers_radio, parent, false))
                VIEW_TYPE_CHECKBOX.second -> ViewHolderCheckBox(LayoutInflater.from(parent?.context)
                        .inflate(R.layout.answer_check_box, parent, false))
                else -> ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.answers_radio, parent, false))
            }


    override fun getItemViewType(position: Int): Int = when (answerElementType) {
        VIEW_TYPE_RADIO.first -> VIEW_TYPE_RADIO.second
        VIEW_TYPE_CHECKBOX.first -> VIEW_TYPE_CHECKBOX.second
        else -> VIEW_TYPE_RADIO.second
    }

    /**
     * If all answers are radio buttons.
     */
    inner class ViewHolderRadioAnswer(view: View) : ViewHolder(view) {

        fun bindRadioView(answer: Answer?) {
            answer?.selected = when (answer?.selected) {
                Selection.TO_SELECT -> Selection.SELECTED
                Selection.SELECTED -> Selection.NOT_SELECTED
                else -> Selection.NOT_SELECTED
            }
            itemView?.radioBtnAnswer?.isChecked = answer?.selected == Selection.SELECTED
            itemView?.setOnClickListener {
                it.post({
                    answer?.selected = Selection.TO_SELECT
                    notifyDataSetChanged()
                })
            }
            itemView.answerTextRadio.text = answer?.answer_value
        }
    }

    /**
     * If Answers are checkboxes.
     */
    inner class ViewHolderCheckBox(view: View) : ViewHolder(view) {
        fun bindCheckBoxView(answer: Answer?) {
            answer?.selected = when (answer?.selected) {
                Selection.TO_SELECT,Selection.SELECTED -> Selection.SELECTED
                else -> Selection.NOT_SELECTED
            }
            itemView?.checkboxBtnAnswer?.isChecked = answer?.selected == Selection.SELECTED
            itemView.answerTextCheckBox.text = answer?.answer_value
            itemView?.setOnClickListener {
                answer?.selected = if(answer?.selected == Selection.SELECTED) Selection.NOT_SELECTED else Selection.TO_SELECT
                notifyDataSetChanged()
            }
        }

    }

    /**
     * Base class for View holder.
     */
    open inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(answer: Answer?) {
            when (this) {
                is ViewHolderRadioAnswer -> this.bindRadioView(answer)
                is ViewHolderCheckBox -> this.bindCheckBoxView(answer)
            }

        }

    }
}