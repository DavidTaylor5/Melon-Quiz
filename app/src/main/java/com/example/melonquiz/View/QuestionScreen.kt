package com.example.melonquiz.View

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.melonquiz.Model.AnswerType
import com.example.melonquiz.R
import com.example.melonquiz.ui.theme.MelonQuizTheme

//I can stay on the Question Screen until I reach final question -> then I gotta move on...

// now I just gotta pass in the navigation functions?...

@Composable
fun QuestionScreen(
    viewModel: QuizViewModel,
    continueOnClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    // I need ui state at the top
    val uiState by viewModel.uiState.collectAsState()

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize()

    ) {
        QuestionLayout(
            currAnswerType = uiState.answerType,
            currScore = uiState.score,
            questionNumber = uiState.question,
            answerImage = uiState.answerImage,
            answerOptions = uiState.optionList,
            optionOnClick = viewModel::submitAnswer
        )

        if(uiState.answerType != AnswerType.NO_ANSWER) {

            //play the correct or false sound

            val answerSound = if(uiState.answerType == AnswerType.CORRECT) R.raw.cartoon_cowbell else R.raw.metal_twang

            val myPlayer = MediaPlayer.create(  LocalContext.current, answerSound)
            myPlayer.start()

            AnswerPrompt(
                answerType = uiState.answerType,
                continueOnClick = continueOnClick // some nav controller method goes here?
            )
        }
    }
}
//To show the little prompt at the bottom I need to use a Box layout...

@Composable
fun QuestionLayout(
    currAnswerType: AnswerType,
    currScore: Int,
    questionNumber: Int,
    answerImage: Int,
    answerOptions: List<Int>,
    optionOnClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "SCORE: $currScore",
            fontSize = 25.sp
        )
        Spacer(
            modifier = modifier.padding(start = 5.dp, end = 5.dp, top = 50.dp, bottom = 0.dp)
        )
        Text(
            text = "Question #$questionNumber"
        )

        Image(
            painter = painterResource(answerImage),
            contentDescription = "An unknown melon.",
            Modifier
                .clip(RoundedCornerShape(25))
                .size(width = 350.dp, height = 200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(
            modifier = modifier.padding(start = 5.dp, end = 5.dp, top = 50.dp, bottom = 0.dp)
        )

        for(option in answerOptions) {
            Button(
                onClick = { optionOnClick(option) },
                enabled = currAnswerType == AnswerType.NO_ANSWER
            ) {
                Text(stringResource(id = option))
            }
        }

        Spacer(
            modifier = modifier.padding(start = 5.dp, end = 5.dp, top = 50.dp, bottom = 100.dp)
        )
    }
}

@Composable
fun AnswerPrompt(
    answerType: AnswerType,
    continueOnClick: () -> Unit,
    modifier: Modifier = Modifier,) {
    Row(
        Modifier.background(
            if(answerType == AnswerType.CORRECT) Color(137, 229, 125) else Color(219, 93, 83, 255)
        )
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(
                if(answerType == AnswerType.CORRECT) R.drawable.correct else R.drawable.incorrect
            ),
            contentDescription = answerType.name,
            Modifier
                .size(50.dp)
                .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 0.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
              text = stringResource(
                  if(answerType == AnswerType.CORRECT) R.string.correct_answer else R.string.wrong_answer
              )
          )
          Button(
              onClick = continueOnClick
          ) {
              Text("Next Question")
          }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    MelonQuizTheme {
        QuestionScreen(QuizViewModel(), {})
        //AnswerPrompt()
    }
}