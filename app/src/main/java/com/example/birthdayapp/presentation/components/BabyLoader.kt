package com.example.birthdayapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.birthdayapp.R

@Composable
fun BabyLoader() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.baby_loading)
    )
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}
