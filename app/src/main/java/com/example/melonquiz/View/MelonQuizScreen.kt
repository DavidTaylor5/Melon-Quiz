package com.example.melonquiz.View

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

/*
* Adding navigation is as simple as four steps
* 1) Set up the routes using an enum class
* 2) Create and Initialize navController and backStackEntry
* 3) add the Nav host and all of its composables
* 4) Pass navigation functions to each of the Routes -> so they know how to navigate
* 5) Optional set up the top app bar to go backwards based on what screen is showing...
*
* */


// 1) Set up the routes using an enum class
enum class QuizScreens {
    START_SCREEN,
    QUESTION_SCREEN,
    REVIEW_SCREEN
}

//Top most screen -> most abstract -> called by activity
@Composable
fun MelonQuizScreen() {

    // 2) Create and initialize navController and backStackEntry
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    //Create ViewModel
    val viewModel: QuizViewModel = QuizViewModel()

    // Just for reading ui?
    val uiState by viewModel.uiState.collectAsState()

    // 3) Add the Nav host and all of its composables
    NavHost(navController = navController,
        startDestination = QuizScreens.START_SCREEN.name,
        modifier = Modifier
    ) {
        composable(route = QuizScreens.START_SCREEN.name) {
            StartScreen(
                startOnClick = {
                    //Reset the Quiz then navigate to first question
                    viewModel.resetQuiz()
                    navController.navigate(QuizScreens.QUESTION_SCREEN.name)
                }
            )
        }
        composable(route = QuizScreens.QUESTION_SCREEN.name) {
            QuestionScreen(
                continueOnClick = {
                    // If I have answered all of the questions go to Review_Screen Else New Question

                    if(uiState.question >= QuizViewModel.NUMBER_OF_QUESTION) {
                        navController.navigate(QuizScreens.REVIEW_SCREEN.name)
                    } else {
                        viewModel.setNextQuestion()
                    }},
                viewModel = viewModel
            )
        }
        composable(route = QuizScreens.REVIEW_SCREEN.name) {
            ReviewScreen(
                finishOnClick = { navController.navigate(QuizScreens.START_SCREEN.name) },
                score = uiState.score
            )
        }
    }
}