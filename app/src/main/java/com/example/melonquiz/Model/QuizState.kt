package com.example.melonquiz.Model

import com.example.melonquiz.R

enum class AnswerType {
    NO_ANSWER,
    CORRECT,
    INCORRECT
}

data class QuizState(
    val question: Int = 0,
    val score: Int = 0,
    val answerType: AnswerType = AnswerType.NO_ANSWER,
    val answer: Int = R.string.cantaloupe,
    val answerImage: Int = R.drawable.cantaloupe,
    val optionList: MutableList<Int> = mutableListOf(R.string.honeydew_melon, R.string.honey_globe_melon, R.string.cucamelon_melon)
)