package com.example.melonquiz.View

import androidx.lifecycle.ViewModel
import com.example.melonquiz.Data.QuizRepository
import com.example.melonquiz.Model.AnswerType
import com.example.melonquiz.Model.MelonQuestion
import com.example.melonquiz.Model.QuizState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel: ViewModel() {

    private val SCORE_AMOUNT = 10

    companion object {
        const val NUMBER_OF_QUESTION = 10
    }

    // access to state...
    private val _uiState = MutableStateFlow(QuizState())
    val uiState: StateFlow<QuizState> = _uiState.asStateFlow()


    private val quizQuestions: ArrayList<MelonQuestion> = ArrayList(QuizRepository.melonQuestions)
    private var possibleAnswers: ArrayList<Int> = ArrayList(QuizRepository.melonNames)

    init {
        setNextQuestion()
    }


    fun resetQuiz() {
        _uiState.value = QuizState()
        setNextQuestion()
    }

    fun submitAnswer(answer: Int) {
        var type = if(answer == uiState.value.answer) {
            AnswerType.CORRECT
        } else {
            AnswerType.INCORRECT
        }

        updateQuizState(type)
    }

    //generate a new question...
    private fun getRandomQuestion(): MelonQuestion {

        if(quizQuestions.isEmpty()) {
          return QuizRepository.melonQuestions.random()
        }

        // else I want unique questions

        val newQuestion = quizQuestions.random()

        quizQuestions.remove(newQuestion)

        return newQuestion
    }

    private fun getIncorrectOption(currQuestion: MelonQuestion): Int {

        if(possibleAnswers.contains(currQuestion.melonName)) possibleAnswers.remove(currQuestion.melonName)

        if(possibleAnswers.isEmpty()) {
            return QuizRepository.melonNames.random()
        }

        val newOption = possibleAnswers.random()

        possibleAnswers.remove(newOption)

        return newOption
    }

    private fun resetOptions() {
        possibleAnswers = ArrayList(QuizRepository.melonNames)
    }

    //TODO set up options to include correct answer
    //TODO set up a funciton to shuffle options before passing to questionScreen

    //This will be an intermediate state for the pop up prompt
    private fun updateQuizState(answerType: AnswerType) {
        _uiState.update { currentState ->
            val questionNumber = currentState.question

            val UpdateScore = currentState.score + SCORE_AMOUNT

            currentState.copy(
                question = questionNumber,
                score = if (answerType == AnswerType.CORRECT) UpdateScore else currentState.score,
                answerType = answerType

            )
        }
    }

    private fun shuffleOptions(options: MutableList<Int>): MutableList<Int> {
        val shuffledList: MutableList<Int> = mutableListOf()

        while(options.isNotEmpty()) {
            val randomOption = options.random()
            shuffledList.add(randomOption)
            options.remove(randomOption)
        }

        return shuffledList
    }

    fun setNextQuestion() {
        _uiState.update { currentState ->

            val currentQuestion = getRandomQuestion()

            resetOptions()

            val incorrect1 = getIncorrectOption(currentQuestion)
            val incorrect2 = getIncorrectOption(currentQuestion)
            val incorrect3 = getIncorrectOption(currentQuestion)

            val optionList = shuffleOptions(mutableListOf(incorrect1, incorrect2, incorrect3, currentQuestion.melonName))

            currentState.copy(
                question = currentState.question + 1,
                answerType = AnswerType.NO_ANSWER,
                answer = currentQuestion.melonName,
                answerImage = currentQuestion.melonImage,
                optionList = optionList
            )
        }
    }
}