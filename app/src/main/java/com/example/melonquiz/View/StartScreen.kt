package com.example.melonquiz.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melonquiz.ui.theme.MelonQuizTheme

@Composable
fun StartScreen(
    startOnClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .background(Color(249, 189, 126))
    ) {
        Spacer(
            modifier = modifier.padding(start = 5.dp, end = 5.dp, top = 100.dp, bottom = 100.dp)
        )
        Text(
            text = "Melon Quiz"
        )
        Spacer(
            modifier = modifier.padding(start = 5.dp, end = 5.dp, top = 100.dp, bottom = 100.dp)
        )
        Button(
            onClick = startOnClick,
        ) {
            Text("Start Quiz")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    MelonQuizTheme {
        StartScreen({})
    }
}